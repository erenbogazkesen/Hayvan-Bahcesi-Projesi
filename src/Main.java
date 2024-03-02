import java.util.*;

class Hayvan {
    String tur;
    String cinsiyet;

    public Hayvan(String tur, String cinsiyet) {
        this.tur = tur;
        this.cinsiyet = cinsiyet;
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        int satirSayisi = 500;
        int sutunSayisi = 500;

        Hayvan[][] alan = new Hayvan[satirSayisi][sutunSayisi];
        int maxAralik = 500;

        // Alanı oluştur
        populateField(alan, maxAralik);
        countAnimals(alan);
        printField(alan);
        System.out.println(("\n"));
        // etkileşimlerin gerçekleşme kısmı
        performInteractions(alan);

        // etkileşim sonrası alan yerleşimi ve hayvan sayısını yazdır
        printField(alan);
        countAnimals(alan);
    }


    public static void populateField(Hayvan[][] alan, int maxAralik) {
        // Koyunlar
        for (int i = 0; i < 15; i++) {
            int randomSatir = rastgeleSayiUret(maxAralik);
            int randomSutun = rastgeleSayiUret(maxAralik);
            alan[randomSatir][randomSutun] = new Hayvan("Koyun", "Erkek");
        }
        for (int i = 0; i < 15; i++) {
            int randomSatir = rastgeleSayiUret(maxAralik);
            int randomSutun = rastgeleSayiUret(maxAralik);
            alan[randomSatir][randomSutun] = new Hayvan("Koyun", "Dişi");
        }

        // İnekler
        for (int i = 0; i < 5; i++) {
            int randomSatir = rastgeleSayiUret(maxAralik);
            int randomSutun = rastgeleSayiUret(maxAralik);
            alan[randomSatir][randomSutun] = new Hayvan("İnek", "Erkek");
        }
        for (int i = 0; i < 5; i++) {
            int randomSatir = rastgeleSayiUret(maxAralik);
            int randomSutun = rastgeleSayiUret(maxAralik);
            alan[randomSatir][randomSutun] = new Hayvan("İnek", "Dişi");
        }

        // Tavuklar
        for (int i = 0; i < 10; i++) {
            int randomSatir = rastgeleSayiUret(maxAralik);
            int randomSutun = rastgeleSayiUret(maxAralik);
            alan[randomSatir][randomSutun] = new Hayvan("Tavuk", "");
        }
        for (int i = 0; i < 5; i++) {
            int randomSatir = rastgeleSayiUret(maxAralik);
            int randomSutun = rastgeleSayiUret(maxAralik);
            alan[randomSatir][randomSutun] = new Hayvan("Kurt", "Erkek");
        }
        for (int i = 0; i < 5; i++) {
            int randomSatir = rastgeleSayiUret(maxAralik);
            int randomSutun = rastgeleSayiUret(maxAralik);
            alan[randomSatir][randomSutun] = new Hayvan("Kurt", "Dişi");
        }

        // Horozlar
        for (int i = 0; i < 10; i++) {
            int randomSatir = rastgeleSayiUret(maxAralik);
            int randomSutun = rastgeleSayiUret(maxAralik);
            alan[randomSatir][randomSutun] = new Hayvan("Horoz", "");
        }

        // Aslanlar
        for (int i = 0; i < 4; i++) {
            int randomSatir = rastgeleSayiUret(maxAralik);
            int randomSutun = rastgeleSayiUret(maxAralik);
            alan[randomSatir][randomSutun] = new Hayvan("Aslan", "Erkek");
        }
        for (int i = 0; i < 4; i++) {
            int randomSatir = rastgeleSayiUret(maxAralik);
            int randomSutun = rastgeleSayiUret(maxAralik);
            alan[randomSatir][randomSutun] = new Hayvan("Aslan", "Dişi");
        }
    }


    public static void performInteractions(Hayvan[][] alan) {

        int remainingSteps = 1000;
        while (remainingSteps > 0) {
            for (int i = 0; i < alan.length; i++) {
                for (int j = 0; j < alan[i].length; j++) {
                    Hayvan hayvan = alan[i][j];
                    if (hayvan != null) {
                        // hayvanların türüne göre avlanma işlemleri
                        switch (hayvan.tur) {
                            case "Kurt":
                                // Kurtların avlanması (Koyunlar, tavuklar ve horozlar)
                                for (int a = -4; a <= 4; a++) {
                                    for (int b = -4; b <= 4; b++) {
                                        int newI = i + a;
                                        int newJ = j + b;
                                        if (isValidPosition(newI, newJ, alan) && !hayvan.equals(alan[newI][newJ])) {
                                            Hayvan av = alan[newI][newJ];
                                            if (av != null && (av.tur.equals("Koyun") || av.tur.equals("Tavuk") || av.tur.equals("Horoz"))) {
                                                System.out.println(" kurt avlandı");
                                                alan[newI][newJ] = null; // Av yendi
                                            }
                                        }
                                    }
                                }
                                break;
                            case "Aslan":
                                // Aslanların avlanması (İnekler ve koyunlar)
                                for (int a = -5; a <= 5; a++) {
                                    for (int b = -5; b <= 5; b++) {
                                        int newI = i + a;
                                        int newJ = j + b;
                                        if (isValidPosition(newI, newJ, alan) && !hayvan.equals(alan[newI][newJ])) {
                                            Hayvan av = alan[newI][newJ];
                                            if (av != null && (av.tur.equals("İnek") || av.tur.equals("Koyun"))) {
                                                System.out.println("aslan avlandı");
                                                alan[newI][newJ] = null; // Av yendi
                                            }
                                        }
                                    }
                                }
                                break;

                            case "Avcı":
                                // Avcı tüm hayvanları avlayabilir
                                for (int a = -8; a <= 8; a++) {
                                    for (int b = -8; b <= 8; b++) {
                                        int newI = i + a;
                                        int newJ = j + b;
                                        if (isValidPosition(newI, newJ, alan) && !hayvan.equals(alan[newI][newJ])) {
                                            Hayvan av = alan[newI][newJ];
                                            if (av != null && (av.tur.equals("İnek") || av.tur.equals("Koyun") || av.tur.equals("Kurt") || av.tur.equals("Tavuk") || av.tur.equals("Horoz") || av.tur.equals("Aslan"))) {
                                                System.out.println("avcı avlandı");
                                                alan[newI][newJ] = null; // Av yendi
                                            }
                                        }
                                    }
                                }
                                break;


                        }

                    }
                }
            }



        // Random hareket
        Random random = new Random();
        for (int i = 0; i < alan.length; i++) {
            for (int j = 0; j < alan[i].length; j++) {
                Hayvan hayvan = alan[i][j];
                if (hayvan != null) {
                    int hareketMiktari = 1; // Varsayılan hareket miktarı
                    switch (hayvan.tur) {
                        case "Koyun":
                            hareketMiktari = 2;
                            break;
                        case "Kurt":
                            hareketMiktari = 3;
                            break;
                        case "İnek":
                            hareketMiktari = 2;
                            break;
                        case "Tavuk":
                            hareketMiktari = 1;
                            break;
                        case "Horoz":
                            hareketMiktari = 1;
                            break;
                        case "Aslan":
                            hareketMiktari = 4;
                            break;
                        case "Avcı":
                            hareketMiktari = 1;
                            break;

                    }
                    remainingSteps -= hareketMiktari;
                    // Rastgele yeni pozisyon hesapla
                    int newX = i + random.nextInt(hareketMiktari * 2) - hareketMiktari;
                    int newY = j + random.nextInt(hareketMiktari * 2) - hareketMiktari;
                    // Yeni pozisyonu kontrol et ve taşı
                    if (isValidPosition(newX, newY, alan) && alan[newX][newY] == null) {
                        alan[newX][newY] = hayvan;
                        alan[i][j] = null;
                    }
                }
            }

        }

        // Çiftleşme işlemi
        List<Hayvan> yeniHayvanlar = new ArrayList<>();
        for (int i = 0; i < alan.length; i++) {
            for (int j = 0; j < alan[i].length; j++) {
                Hayvan hayvan = alan[i][j];
                if (hayvan != null) {
                    // yakında uygun bir canlı var mı kontrolünün sağlanması
                    for (int a = -3; a <= 3; a++) {
                        for (int b = -3; b <= 3; b++) {
                            int newI = i + a;
                            int newJ = j + b;
                            if (isValidPosition(newI, newJ, alan) && !hayvan.equals(alan[newI][newJ])) {
                                Hayvan mate = alan[newI][newJ];
                                if (mate != null && mate.tur.equals(hayvan.tur) && !mate.cinsiyet.equals(hayvan.cinsiyet)) {
                                    // yeni canlının doğumu
                                    int newX = i + (int) (Math.random() * 3) - 1; // Random position within 1 cell
                                    int newY = j + (int) (Math.random() * 3) - 1;
                                    if (isValidPosition(newX, newY, alan) && alan[newX][newY] == null) {
                                        String yeniCinsiyet = (Math.random() < 0.5) ? "Erkek" : "Dişi";
                                        yeniHayvanlar.add(new Hayvan(hayvan.tur, yeniCinsiyet));
                                        System.out.println("Yeni bir " + hayvan.tur + " " + yeniCinsiyet + " doğdu!");
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }


        // Yeni doğan yavruların alana yerleşiminin rastgele gerçekleşmesi için yapılan işlemler
        for (Hayvan yeniHayvan : yeniHayvanlar) {
            int randomSatir = rastgeleSayiUret(alan.length);
            int randomSutun = rastgeleSayiUret(alan[0].length);
            if (alan[randomSatir][randomSutun] == null) {
                alan[randomSatir][randomSutun] = yeniHayvan;
            }
        }
    }
    }


    public static void printField(Hayvan[][] alan) {
        // Alan üzerindeki hayvanları yazdırır
        for (int i = 0; i < alan.length; i++) {
            for (int j = 0; j < alan[i].length; j++) {
                Hayvan hayvan = alan[i][j];
                if (hayvan != null) {
                    System.out.println("Alan[" + i + "][" + j + "] - Tür: " + hayvan.tur + ", Cinsiyet: " + hayvan.cinsiyet);
                }
            }
        }
    }

    public static int rastgeleSayiUret(int maxAralik) {
        Random random = new Random();
        return random.nextInt(maxAralik);
    }

    public static boolean isValidPosition(int i, int j, Hayvan[][] alan) {
        return i >= 0 && i < alan.length && j >= 0 && j < alan[i].length;
    }
    public static void countAnimals(Hayvan[][] alan) {
        // Tür bazında hayvan sayısını saklamak için bir HashMap oluşturuyoruz
        Map<String, Integer> animalCounts = new HashMap<>();

        // Alan üzerindeki hayvanları sayıyoruz
        for (int i = 0; i < alan.length; i++) {
            for (int j = 0; j < alan[i].length; j++) {
                Hayvan hayvan = alan[i][j];
                if (hayvan != null) {
                    // Hayvanın türünü alıyoruz
                    String tur = hayvan.tur;
                    // Türüne göre HashMap'teki değeri arttırıyoruz toplam sayıyı bulmak için 
                    animalCounts.put(tur, animalCounts.getOrDefault(tur, 0) + 1);
                }
            }
        }

        // Sonuçları yazdırıyoruz
        System.out.println("Hayvan Sayıları:");
        for (Map.Entry<String, Integer> entry : animalCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
