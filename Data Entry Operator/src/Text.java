import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import static java.lang.String.valueOf;

public class Text {

    private DataEntryOperator yazar;
    private String content;
    private int kelimeSayisi = 0;
    private int hataliKelimeSayisi = 0;
    private int duzeltilenKelimeSayisi = 0;
    private double algBasariOrani = 0;

    private double dogruYazimOrani = 0;

    public Text() {

        this.setYazar(new DataEntryOperator());
        this.setContent(null);

    }

    public Text(DataEntryOperator yazar, String content) {

        this.setYazar(yazar);
        this.setContent(content);

    }

    @Override
    public String toString() {

        return getYazar() + "\n" + getContent();

    }


    public DataEntryOperator getYazar() {
        return yazar;
    }

    public void setYazar(DataEntryOperator yazar) {
        this.yazar = yazar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public boolean fakeSwitch(char[] kontroledilenler, char[] sozlukElemanı) {
        for (int j = 0; j < kontroledilenler.length - 1; j++) {
            if ((int) kontroledilenler[j] != (int) sozlukElemanı[j]) {

                if ((int) kontroledilenler[j] == (int) sozlukElemanı[j + 1] && (int) kontroledilenler[j + 1] == (int) sozlukElemanı[j]) {
                    if (j == kontroledilenler.length - 2) {
                        return true;

                    } else {
                        for (int k = j + 2; k < kontroledilenler.length; k++) {
                            if ((int) sozlukElemanı[k] != (int) kontroledilenler[k]) {
                                break;
                            } else if (k == kontroledilenler.length - 1) {
                                return true;

                            }
                        }
                    }
                } else {
                    return false;
                }


            }

        }
        return false;
    }


    public boolean CloseButton(char[] kontroledilenler, char[] sozlukElemanı) {

        for (int j = 0; j < kontroledilenler.length; j++) {
            if ((int) kontroledilenler[j] != (int) sozlukElemanı[j]) {
                String[] klavye = {"qwertyuıop", "asdfghjkli", "zxcvbnm"};

                boolean yakınTusMu = false;
                for (String k : klavye) {
                    if (k.contains(valueOf(kontroledilenler[j]))) {
                        int tmpIndex = k.indexOf(valueOf(kontroledilenler[j]));

                        if (tmpIndex == 0) {
                            if (k.charAt(tmpIndex + 1) == sozlukElemanı[j]) {
                                yakınTusMu = true;
                                break;
                            }
                        } else {
                            if (tmpIndex == k.length() - 1) {
                                if (k.charAt(tmpIndex - 1) == sozlukElemanı[j]) {
                                    yakınTusMu = true;
                                    break;
                                }
                            } else {
                                if (k.charAt(tmpIndex - 1) == sozlukElemanı[j] || k.charAt(tmpIndex + 1) == sozlukElemanı[j]) {
                                    yakınTusMu = true;
                                    break;
                                }
                            }
                        }

                    }
                }

                if (yakınTusMu) {
                    if (j == kontroledilenler.length - 1) {
                        return true;

                    }
                    for (int k = j + 1; k < kontroledilenler.length; k++) {
                        if ((int) kontroledilenler[k] != (int) sozlukElemanı[k]) {
                            return false;
                        } else {
                            if (k == kontroledilenler.length - 1) {
                                return true;
                            }
                        }
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }


    public boolean muchButton(char[] kontroledilenler, char[] sozlukElemanı) {
        for (int i = 0; i < kontroledilenler.length; i++) {


            if (i == kontroledilenler.length - 2) {
                if ((int) kontroledilenler[i] == (int) kontroledilenler[i + 1]) {
                    return true;
                }
            }
            if ((int) kontroledilenler[i] != (int) sozlukElemanı[i]) {
                if (i == 0) {
                    return false;
                }
                if ((int) kontroledilenler[i] == (int) kontroledilenler[i - 1]) {
                    for (int j = i + 1; j < kontroledilenler.length; j++) {
                        if ((int) kontroledilenler[j] != (int) sozlukElemanı[j - 1]) {
                            return false;
                        }
                        if (j == kontroledilenler.length - 1) {
                            return true;
                        }
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }


    public int[] hataYakala() throws FileNotFoundException {
        Scanner sozluk = new Scanner(new FileInputStream("path name"));
        ArrayList<String> sozlukStr = new ArrayList<>();

        boolean bulundu = true;
        while (sozluk.hasNextLine()) {
            sozlukStr.add(sozluk.nextLine());
        }
        StringTokenizer parcala = new StringTokenizer(getContent(), " ");

        while (parcala.hasMoreTokens()) {
            this.setKelimeSayisi(this.getKelimeSayisi() + 1);
            String kelime = parcala.nextToken();
            bulundu = false;
            String atlanacaklar = ".,;-_!'^+%&()=?<>";

            for (char i : atlanacaklar.toCharArray()){
                if (kelime.contains(valueOf(i))){
                    kelime = kelime.replaceAll(valueOf(i),"");
                }
            }
            kelime = kelime.toLowerCase();
            if (kelime .length() >2 && !sozlukStr.contains(kelime)) {
                this.setHataliKelimeSayisi(this.getHataliKelimeSayisi() + 1);
                char[] kontroledilenler = kelime.toCharArray();
                char[] sozlukElemanı;
                for (String i : sozlukStr) {
                    sozlukElemanı = i.toCharArray();
                    if (!bulundu) {
                        if (kelime.length() == i.length()) {
                            if (fakeSwitch(kontroledilenler, sozlukElemanı)) {
                                bulundu = true;
                            }
                            if (!bulundu) {
                                if (CloseButton(kontroledilenler, sozlukElemanı)) {
                                    bulundu = true;
                                }
                            }
                        } else {
                            if (kelime.length() == i.length() + 1) {

                                if (muchButton(kontroledilenler, sozlukElemanı)) {
                                    bulundu = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }


            if (bulundu) {
                this.setDuzeltilenKelimeSayisi(this.getDuzeltilenKelimeSayisi() + 1);
            }
        }


        setDogruYazimOrani();
        setAlgBasariOrani();
        int dogruYazilmisKelimeSayisi = this.kelimeSayisi - this.hataliKelimeSayisi;
        int duzeltilemeyenKelimeSayısı = this.hataliKelimeSayisi-this.duzeltilenKelimeSayisi;
        int[] staticsArray = {this.getKelimeSayisi(), dogruYazilmisKelimeSayisi,duzeltilenKelimeSayisi,duzeltilemeyenKelimeSayısı,(int)dogruYazimOrani,(int)algBasariOrani};
        return staticsArray;
    }


    public void setAlgBasariOrani() {
        this.algBasariOrani = ((double)this.getDuzeltilenKelimeSayisi() / (double)this.getHataliKelimeSayisi()) *100;
    }

    public int getKelimeSayisi() {
        return kelimeSayisi;
    }

    public void setKelimeSayisi(int kelimeSayisi) {
        this.kelimeSayisi = kelimeSayisi;
    }

    public int getHataliKelimeSayisi() {
        return hataliKelimeSayisi;
    }

    public void setHataliKelimeSayisi(int hataliKelimeSayisi) {
        this.hataliKelimeSayisi = hataliKelimeSayisi;
    }

    public int getDuzeltilenKelimeSayisi() {
        return duzeltilenKelimeSayisi;
    }

    public void setDuzeltilenKelimeSayisi(int duzeltilenKelimeSayisi) {
        this.duzeltilenKelimeSayisi = duzeltilenKelimeSayisi;
    }

    public double getAlgBasariOrani() {
        return algBasariOrani;
    }

    public void setDogruYazimOrani() {
        this.dogruYazimOrani = ((double)(this.kelimeSayisi - this.hataliKelimeSayisi) / (double)this.kelimeSayisi)*100;
    }

    public double getDogruYazimOrani() {
        return dogruYazimOrani;
    }
}














