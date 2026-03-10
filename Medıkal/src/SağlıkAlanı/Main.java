package SağlıkAlanı;

public class Main {

    public static void main(String[] args) {
        
       
        HastaneYonetimi yonetim = new HastaneYonetimi();

       
        Hasta hasta1 = new Hasta("56411654456", "Ahmet", "Yilmaz", "Erkek", 45, "Ayse Yilmaz", "Siddetli bas agrisi");
        Hasta hasta2 = new Hasta("65466544566", "Zeynep", "Kaya", "Kadin", 28, "Ali Kaya", "Mide bulantisi");

        yonetim.hastaEkle(hasta1);
        yonetim.hastaEkle(hasta2);

        
        yonetim.hastalariListele();

        Randevu randevu1 = new Randevu("RND-1", hasta1, "Mehmet Oz", "15.03.2026 10:30", "Noroloji");
        Randevu randevu2 = new Randevu("RND-2", hasta2, "Selin Demir", "16.03.2026 14:00", "Dahiliye");

        yonetim.randevuOlustur(randevu1);
        yonetim.randevuOlustur(randevu2);

     
        yonetim.randevulariListele();
    }
}