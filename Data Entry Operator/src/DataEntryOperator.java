public class DataEntryOperator {

    private int ID;
    private String adSoyad;
    private String adres;

    public DataEntryOperator() {

        this.setID(-1);
        this.setAdSoyad("default");
        this.setAdres("default");

    }

    public DataEntryOperator(int ID, String adSoyad, String adres) {

        this.setID(ID);
        this.setAdSoyad(adSoyad);
        this.setAdres(adres);

    }

    @Override
    public String toString() {

        return "ID: " + this.getID() + "Ad ve Soyad: " + this.getAdSoyad() + "Adresi: " + this.getAdres();

    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}

