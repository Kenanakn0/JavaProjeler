# Hastane ve Randevu Yönetim Sistemi (Java OOP Simülasyonu)

Bu proje, Nesne Yönelimli Programlama (OOP) prensipleri kullanılarak Java dilinde geliştirilmiş temel bir hastane otomasyonu simülasyonudur. Sağlık teknolojileri ve bilişim sektörüne duyduğum ilgi doğrultusunda, verilerin birbiriyle ilişkisel olarak nasıl yönetilebileceğini pratik etmek amacıyla hazırlanmıştır.

## Proje Amacı
Projenin temel amacı; Kapsülleme (Encapsulation), Sınıf İlişkileri (Composition) ve Dinamik Veri Yapıları (ArrayList) gibi temel Java/OOP yeteneklerini temiz kod (Clean Code) standartlarına uygun olarak sergilemektir.

## Temel Özellikler
* Yeni hasta kaydı oluşturma ve sistemde saklama.
* Kayıtlı hastalar üzerinden departman ve doktor bazlı randevu planlama.
* Sistemdeki aktif hastaları ve randevuları formatlı bir şekilde listeleme.
* String/Int veri tipleri yerine, sınıfların (Hasta ve Randevu) birbirini referans almasıyla veri tutarlılığını sağlama.

## Proje Mimarisi (Sınıflar)
Sistem 4 temel sınıftan oluşmaktadır:

* **Hasta.java:** Hastanın kişisel bilgilerini (TC, ad, soyad, yaş, şikayet) private değişkenler ve getter/setter metotlarıyla (Encapsulation) tutan veri modelidir.
* **Randevu.java:** Randevu detaylarını içerir. Randevunun kime ait olduğu bilgisini String olarak tutmak yerine doğrudan `Hasta` nesnesini barındırır (Composition/Association).
* **HastaneYonetimi.java:** Projenin iş mantığı (Business Logic) katmanıdır. Gelen verileri `ArrayList` yapıları kullanarak bellekte dinamik olarak saklar ve listeler.
* **Main.java:** Uygulamanın başlatıldığı ve test senaryolarının işletildiği ana sınıftır.

## Kullanılan Teknolojiler
* Java (JDK)
* Eclipse IDE

## Nasıl Çalıştırılır?
Projeyi bilgisayarınıza klonladıktan sonra, bir Java IDE'si (Eclipse, IntelliJ veya VS Code) ile açıp `Main.java` dosyasını derleyerek (Run) konsol üzerinden sistemin akışını ve çıktılarını gözlemleyebilirsiniz.
