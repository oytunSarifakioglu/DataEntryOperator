import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File deneme = new File("path name");
        File[] dosyalar = deneme.listFiles();
        FilenameFilter filtername = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (dir.getName().contains(name)) {
                    return true;
                }
                return false;
            }
        };
        int[][] statics = new int[dosyalar.length][6];
        Text[] kütüphane = new Text[dosyalar.length];
        int sayac = 0;
        for (File i : dosyalar) {

            if (filtername.accept(i, ".txt")) {
                Scanner okuma = new Scanner(new FileInputStream("path name" + i.getName()));
                String DataOprData = okuma.nextLine();
                StringTokenizer looktotokens = new StringTokenizer(DataOprData, ",");
                DataEntryOperator yazar = new DataEntryOperator(Integer.parseInt(looktotokens.nextToken()), looktotokens.nextToken(), looktotokens.nextToken());
                String metin = "";
                while (okuma.hasNextLine()) {
                    metin += okuma.nextLine();
                    if (okuma.hasNextLine()) {
                        metin += System.lineSeparator();
                    }
                }
                Text bilgi = new Text(yazar, metin);
                statics[sayac] = bilgi.hataYakala();
                kütüphane[sayac] = bilgi;
                sayac++;
            }
        }


        int toplamKelimeSayisi = 0;
        int toplamDogruKelimeSayisi = 0;
        int toplamDuzeltilenKelimeSayisi = 0;

        double muhDogruYazimOrani = 0;
        int muhendisSayisi = 0;

        int tipSayisi = 0;
        double toplamTipHataliOran = 0;

        double toplamAlgoritmaBasariOrani = 0;
        int counter = 0;
        for (Text i : kütüphane) {
            if (i.getYazar().getAdres().equals("MuhendislikFakultesi")) {
                muhendisSayisi++;
                muhDogruYazimOrani += statics[counter][4];
            }
            if (i.getYazar().getAdres().equals("TipFakultesi")) {
                tipSayisi++;
                toplamTipHataliOran += (double) (statics[counter][2] + statics[counter][3]) / (double) statics[counter][0];
            }

            toplamAlgoritmaBasariOrani += statics[counter][5];
            toplamKelimeSayisi += statics[counter][0];
            toplamDogruKelimeSayisi += (statics[counter][0] - (statics[counter][2] + statics[counter][3]));
            toplamDuzeltilenKelimeSayisi += statics[counter][2];
            counter++;
        }


        System.out.println("Toplam Kelime Sayısı: " + toplamKelimeSayisi);
        System.out.println("Toplam Doğru Kelime Sayısı: " + toplamDogruKelimeSayisi);
        System.out.println("Toplam Düzeltilen Kelime Sayısı: " + toplamDuzeltilenKelimeSayisi);
        System.out.println("mühendis doğru yazım oranı : " + muhDogruYazimOrani);
        System.out.println("mühendis saysı : " + muhendisSayisi);
        System.out.println("\nMühendislik Fakültesi Çalışanlarının Ortalama Doğru Yazım Oranı: " + muhDogruYazimOrani / muhendisSayisi);
        System.out.println("Tip Fakültesi Çalışanlarının Ortalama Yanlış Yazım Oranı: " + toplamTipHataliOran / tipSayisi * 100);
        System.out.println("\n Tüm Çalışanlar için Algoritma Başarı Oranı Ortalaması: " + (toplamAlgoritmaBasariOrani / (double) kütüphane.length));


    }

}

