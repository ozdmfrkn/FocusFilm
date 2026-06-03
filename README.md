# 🎬 FocusFilm - Sinema ve Dijital İçerik Yönetim Sistemi

FocusFilm, film kataloglarının dijital ortamda saklanmasını, yönetilmesini ve dinamik olarak listelenmesini sağlayan, Yönetim Bilişim Sistemleri standartlarına uygun olarak uçtan uca (Full-Stack) kurgulanmış bir web uygulamasıdır. Proje, Netflix/IMDB benzeri karanlık ve sinematik bir kullanıcı arayüzü ile desteklenmiştir.

## 🛠 Kullanılan Teknolojiler

* **Java & Spring Boot:** Projenin ana çatısı, RESTful API mimarisi ve gömülü sunucu yönetimi.
* **Spring Data JPA & Hibernate:** Veritabanı işlemleri (Full CRUD), ORM yönetimi ve otomatik sorgu üretimi.
* **H2 In-Memory Database:** Verilerin yerel testler için hafızada geçici olarak tutulduğu gömülü veritabanı.
* **Spring Security:** Rol bazlı yetkilendirme (RBAC), API güvenliği, Custom Form Login ve Session yönetimi.
* **Spring Validation:** Dışarıdan gelen HTTP isteklerindeki verilerin (DTO) anotasyonlarla doğrulanması ve iş kurallarının işletilmesi.
* **Önyüz (Frontend):** HTML5, CSS3 (Dark/Cinematic UI), JavaScript (Vanilla, Fetch API).

## 📐 Mimari Yapı ve Proje İsterlerinin Karşılanması

Projede Katmanlı Mimari (Layered Architecture) prensiplerine ve Separation of Concerns (Sorumlulukların Ayrılması) kurallarına eksiksiz uyulmuştur:

* **Entity:** Veritabanı tabloları (`Movie`) nesne tabanlı olarak kurgulandı.
* **Repository:** JpaRepository kullanılarak tam CRUD mimarisi sağlandı. Özel sorgular için **Derived Query Methods** (örn: `findByKategori`) altyapısı hazırlandı.
* **Service:** İş mantığı ve veri manipülasyon kuralları bu katmanda soyutlandı.
* **Controller:** Dışarıdan gelen HTTP istekleri (GET, POST, PUT, DELETE) karşılanarak güvenli API uç noktaları oluşturuldu.
* **DTO & Validation:** Sisteme film eklenirken ve güncellenirken `MovieCreateDTO` kalkanı kullanıldı. İş kuralları gereği çıkış yılı minimum 1888 (`@Min(1888)`) ve IMDB puanı 1.0 ile 10.0 arasında (`@Min(1)`, `@Max(10)`) olacak şekilde kısıtlandırıldı.
* **Exception Handling:** Global hata yönetimi kurgulandı; sistemde olmayan filmler üzerinde işlem yapılmaya çalışıldığında 500 hatası yerine, `@ResponseStatus(HttpStatus.NOT_FOUND)` ile entegre çalışan özel `MovieNotFoundException` (404) fırlatıldı.
* **Spring Security (RBAC):** Ekleme (POST), güncelleme (PUT) ve silme (DELETE) gibi kritik veri manipülasyonları sadece `ADMIN` rolüne kısıtlanırken; listeleme işlemi (GET) hem `ADMIN` hem de `USER` (İzleyici) rollerine açılmıştır.

## ⚙️ Kurulum ve Çalıştırma Talimatları

Projeyi kendi ortamınızda çalıştırmak için şu adımları izleyebilirsiniz:

1. Bu depoyu (repository) bilgisayarınıza indirin (`git clone`).
2. Projeyi **IntelliJ IDEA** veya tercih ettiğiniz bir IDE üzerinden açın.
3. Maven bağımlılıklarının (Dependencies) inmesini bekleyin.
4. `src/main/resources/static` klasörü içerisine `arkaplann.png` adında sinematik bir arkaplan görseli eklediğinizden emin olun.
5. `FocusFilmApplication.java` dosyasını çalıştırın (Run).
6. Uygulama `8080` portunda başarıyla ayağa kalkacaktır.

### 💻 Uygulama Arayüzünün Kullanımı (Tarayıcı)

Sistem, yetkilendirme ile entegre çalışan özel bir önyüze sahiptir:
1. Tarayıcınızdan `http://localhost:8080` adresine gidin.
2. Karşınıza çıkacak olan karanlık temalı giriş ekranında aşağıdaki test kullanıcılarıyla oturum açabilirsiniz:
    * **Admin Yetkisi (Tam CRUD - Ekle/Düzenle/Sil):** Kullanıcı Adı: `admin` | Şifre: `1234`
    * **İzleyici Yetkisi (Sadece Listeleme):** Kullanıcı Adı: `izleyici` | Şifre: `1234`
3. Arayüzde `admin` girişi yapıldığında film kartları üzerinde **Düzenle** ve **Sil** butonları aktif ve işlevseldir. Form dinamik olarak Ekleme/Güncelleme modları arasında geçiş yapar. `izleyici` kullanıcısı bu işlemleri yapmaya çalıştığında sistem HTTP 403 (Forbidden) uyarısı vermektedir.

### 🧪 Postman ile API Testleri

Sistemin REST API uç noktalarını Postman üzerinden test etmek için, isteklerinize **Authorization -> Basic Auth** kısmından `admin` kullanıcı bilgilerini eklemeniz gerekmektedir.

**1. Yeni Film Ekleme**
* **Metot:** POST
* **URL:** `http://localhost:8080/api/movies`
* **Body (JSON):**
    ```json
    {
      "filmAdi": "Interstellar",
      "cikisYili": 2014,
      "kategori": "Bilim Kurgu",
      "yonetmen": "Christopher Nolan",
      "imdbPuani": 8.7
    }
    ```

**2. Tüm Kataloğu Listeleme**
* **Metot:** GET
* **URL:** `http://localhost:8080/api/movies`

**3. Film Güncelleme**
* **Metot:** PUT
* **URL:** `http://localhost:8080/api/movies/1` *(1: Güncellenecek ID)*
* **Body (JSON):**
    ```json
    {
      "filmAdi": "Interstellar (Director's Cut)",
      "cikisYili": 2014,
      "kategori": "Bilim Kurgu",
      "yonetmen": "Christopher Nolan",
      "imdbPuani": 9.1
    }
    ```

**4. Filmi Sistemden Silme**
* **Metot:** DELETE
* **URL:** `http://localhost:8080/api/movies/1` *(1: Silinecek ID)*

---
*Not: Proje kodlarında hocamızın isterleri doğrultusunda hiçbir açıklama/yorum satırına yer verilmemiş olup, sistemin tüm mimari işleyişi Yönetim Bilişim Sistemleri proje sunumu esnasında sözlü olarak detaylandırılacaktır.*