package Ölçüm;

import java.util.HashMap;
import java.util.Map;

public class KanBankasi {
    public static void main(String[] args) {
        
        
        Map<String, Integer> stoklar = new HashMap<>();

        stoklar.put("A+", 50);
        stoklar.put("0-", 10);
        stoklar.put("B+", 30);

        System.out.println("Mevcut 0- stoku: " + stoklar.get("0-"));

       
        String istenenKan = "0-";
        int miktar = 3;

        if (stoklar.containsKey(istenenKan)) {
            int mevcut = stoklar.get(istenenKan);
            if (mevcut >= miktar) {
                stoklar.put(istenenKan, mevcut - miktar);
                System.out.println(miktar + " unite " + istenenKan + " verildi.");
                System.out.println("Kalan stok: " + stoklar.get(istenenKan));
            } else {
                System.out.println("Yeterli stok yok.");
            }
        } else {
            System.out.println("Gecersiz kan grubu.");
        }
    }
}