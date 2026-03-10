package Sağlık;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Indeks {
	
	public static void main(String[] args) {
		Scanner k = new Scanner(System.in);
		        
		        System.out.println("++++ Saglik Asistani Sistemine Hosgeldiniz +++++");
		        
		        try {
		            System.out.print("Lutfen kilonuzu giriniz (kg - orn: 75): ");
		            double kilo = k.nextDouble();
		            
		            System.out.print("Lutfen boyunuzu giriniz (metre - orn: 1,75): ");
		            double boy = k.nextDouble();
		            
		            double bmi = kilo / (boy * boy);
		            System.out.printf("\nVucut Kitle Indeksiniz (BMI): %.2f\n", bmi);
		            
		            System.out.print("Saglik Durumunuz: ");
		            if (bmi < 18.5) {
		                System.out.println("Zayif - Saglikli kilo almaniz onerilir.");
		            } else if (bmi >= 18.5 && bmi < 24.9) {
		                System.out.println("Normal - Ideal kilonuzdasiniz.");
		            } else if (bmi >= 25 && bmi < 29.9) {
		                System.out.println("Fazla Kilolu - Egzersiz ve diyet onerilir.");
		            } else {
		                System.out.println("Obez - Bir uzmana danismaniz tavsiye edilir.");
		            }
		            
		            double gunlukSuIhtiyaci = kilo * 0.035;
		            System.out.printf("Gunluk tuketmeniz gereken minimum su miktari: %.2f Litre\n", gunlukSuIhtiyaci);
		            
		        } catch (InputMismatchException e) {
		            System.out.println("\nHata: Lutfen sadece sayisal degerler giriniz!");
		            System.out.println("Not: Ondalikli sayilar icin nokta(.) yerine virgul(,) kullanmayi deneyin.");
		        } finally {
		            k.close();
		        }
		    }
		
		
		
	}


