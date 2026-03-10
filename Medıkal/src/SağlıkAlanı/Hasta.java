package SağlıkAlanı;
public class Hasta {

    
    private String adi;
    private String soyadi;
    private String cinsiyet;
    private int yas; 
    private String yakini;
    private String sikayet; 
    
    public Hasta(String id, String adi, String soyadi, String cinsiyet, int yas, String yakini, String sikayet) {
        this.adi = id;
        this.adi = adi;
        this.soyadi = soyadi;
        this.cinsiyet = cinsiyet;
        this.yas = yas;
        this.yakini = yakini;
        this.sikayet = sikayet;
    }

    
    public String getId() { return adi; }
    public void setId(String id) { this.adi = id; }

    public String getFullName() { return adi + " " + soyadi; }
    
    public int getAge() { return yas; }
    public void setAge(int yas) { this.yas = yas; }

    public String getComplaint() { return sikayet; }
    public void setComplaint(String sikayet) { this.sikayet = sikayet; }

    public String getYakini() { return yakini; }
    public void setYakini(String yakini) { this.yakini = yakini; }

   
    @Override
    public String toString() {
        return "Hasta Bilgisi [TC/ID: " + adi + ", İsim: " + getFullName() + ", Yaş: " + yas + ", Şikayet: " + sikayet + "]";
    }
}