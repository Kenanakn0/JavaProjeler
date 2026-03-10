# Sağlık Asistanı - BMI ve Su İhtiyacı Analiz Aracı

Kullanıcıdan alınan fiziksel verilere (boy, kilo) göre Vücut Kitle İndeksi (BMI) hesaplaması yapan ve günlük su ihtiyacını belirleyen bir Java konsol uygulamasıdır. 

## Proje Kazanımları
* **Kullanıcı Etkileşimi:** `Scanner` sınıfı kullanılarak dinamik veri girişi sağlandı.
* **Hata Yönetimi (Exception Handling):** Kullanıcının hatalı veri tipi girmesi durumunda programın çökmesini engellemek için `try-catch` blokları kullanıldı (`InputMismatchException`).
* **Algoritmik Kontrol:** `if-else` yapılarıyla veriler analiz edilerek anlamlı çıktılar üretildi.
* **Kaynak Yönetimi:** Bellek sızıntılarını (memory leak) önlemek için `finally` bloğunda kaynaklar güvenli bir şekilde kapatıldı.

## Kullanılan Teknolojiler
* Java (JDK)
