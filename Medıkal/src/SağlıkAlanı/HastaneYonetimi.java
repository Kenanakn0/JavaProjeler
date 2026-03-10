package SağlıkAlanı;

import java.util.ArrayList;
import java.util.List;

public class HastaneYonetimi {

    private List<Hasta> hastaListesi;
    private List<Randevu> randevuListesi;

    public HastaneYonetimi() {
        this.hastaListesi = new ArrayList<>();
        this.randevuListesi = new ArrayList<>();
    }

    public void hastaEkle(Hasta yeniHasta) {
        hastaListesi.add(yeniHasta);
        System.out.println(yeniHasta.getFullName() + " sisteme eklendi.");
    }

    public void randevuOlustur(Randevu yeniRandevu) {
        randevuListesi.add(yeniRandevu);
        System.out.println("Randevu olusturuldu: " + yeniRandevu.gettarih() + " - Dr. " + yeniRandevu.getDoktorAdi());
    }

    public void hastalariListele() {
        System.out.println("\nKayitli Hastalar");
        
        if (hastaListesi.isEmpty()) {
            System.out.println("Sistemde kayitli hasta bulunamadi.");
            return; 
        }
        
        for (Hasta h : hastaListesi) {
            System.out.println(h.toString());
        }
    }

    public void randevulariListele() {
        System.out.println("\nRandevular");
        
        if (randevuListesi.isEmpty()) {
            System.out.println("Sistemde aktif randevu bulunamadi.");
            return;
        }
        
        for (Randevu r : randevuListesi) {
            System.out.println(r.toString());
            
        }
    }
}