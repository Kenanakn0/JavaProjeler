package Ölçüm;

import java.util.PriorityQueue;

class Hasta implements Comparable<Hasta> {
    String ad;
    int oncelik; 
    String sikayet;

    public Hasta(String ad, int oncelik, String sikayet) {
        this.ad = ad;
        this.oncelik = oncelik;
        this.sikayet = sikayet;
    }

   
    @Override
    public int compareTo(Hasta diger) {
        return Integer.compare(this.oncelik, diger.oncelik);
    }

    @Override
    public String toString() {
        return ad + " - Oncelik: " + oncelik + " (" + sikayet + ")";
    }
}

public class AcilServis {
    public static void main(String[] args) {
        PriorityQueue<Hasta> kuyruk = new PriorityQueue<>();

        kuyruk.add(new Hasta("Ahmet Y.", 3, "Bas agrisi"));
        kuyruk.add(new Hasta("Zeynep K.", 1, "Kalp krizi suphesi"));
        kuyruk.add(new Hasta("Mehmet D.", 2, "Derin kesik"));

        System.out.println("--- Acil Servis Sirasi ---");
        
        while (!kuyruk.isEmpty()) {
            System.out.println("Muayene edilen: " + kuyruk.poll());
        }
    }
}