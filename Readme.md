# GENEL İÇERİK VE NOTLAR

## İçerik 
     docker run --name postgreDocker -e POSTGRES_PASSWORD=root -p 9999:5432 -d postgres


## Microservice ya da Projenin jar olarak export edilmesi
     1-Öncelikle ilgili microservice2in gradle dosyası üzerinde build gradle yapmanız gerek.
     2-Sonrasında aynı konumdan buildDependents yapmanız gereklidir.
     Detaylar:
        *Bu işlemlerden sonra ilgili microserice'in build klasörü oluşacak
          bu klasörün altında libs klasörünün altında bir proje ismi+verison'u
          olan bir jar klasörü olduğunu fark edeceksiniz.
          Bu jar dosyası çalıştırılabilir bir jar dosyasıdır.

## Docker Image Oluşturma 
    Öncelikle,projenizin üzerinde çalışağacağı bir container'a ihtiyaç var.
    Bu nedenle bizim sistemimize uygun olan bir dockerImage seçmeniz gerekiyor.
    * bize uygun olan javaJDK17 olacaktır. (amazoncorretto:17)
    * kendi jar dosyanızı bu image üzerine kopyalıyorsunuz.
    * OPSİYONEL !! her image genellikle eski paketleri içerir.Bu nedenle update edilmesi tavsiye edilir.
    * Jar dosyanızı çalıştırırsınız.(belli sistem environment larını belirlemeniz gerekebilir.)
    NOT: Dockerfile oluşturmanın ve kullanmanın 2 yolu var.
        1-Dockerfile microservice içine oluşturulur ve çalışıtırılı.
        2-Dockerfile projenin root klasörüne oluşturulur ve çalıştırılır. Burada ise tek dosya üzerinden 
          çalıştığı için güncelleme yapmak kolay olur. Ancak her microservice için ayrıca klasör konumunu 
          belirtmek gerekiyor.

##  Docker Image Oluşturma (Dockerfile)
    1-docker build -t <image_name> . : dockerfile üzerinde gerekli yapılandırmalara bakarak
    image oluşturur,ancak bizim yapımızda ARG olduğu için bu şekilde çalışmaz.
    2-docker build --build-arg JAR_FILE=<jar_file_name> -t <image_name> . : bu şekilde çalışır.

### Auth-Microservice Build İşlemi  (09.01.2023 01:01)
      Terminale yaz;
     docker build --build-arg JAR_FILE=auth-microservice/build/libs/auth-microservice-v.0.0.1.jar -t java4/authservice:v002 .
     docker run -p 9090:9090 -d java4/authservice:v002
     docker run -p 9090:9090 -d -e AUTH_PORT=9090 java4/authservice:v002

### Config-Server Build İşlemi  (09.01.2023 01:01)
      Terminale yaz;
     docker build --build-arg JAR_FILE=config-server/build/libs/config-server-v.0.0.1.jar -t java4/configserver:v002 .
     docker run -p 8888:8888 -d java4/configserver:v002
## Kaşılaşılan Sorunlar
    - Microservis içinde eğer Environment Variable (Ortam Değişkenleri) kullanıyorsanız, docker run yaparken mutlaka
    bu parametreleri belirtmeniz gereklidir. Örn: -e AUTH_PORT=9090 şeklinde ortam değişkeni olarak ekle
    - localhost olarak bırakılan tüm konumlar,container içinde aranacağı için hataya neden olmaktadır.
      Burada yapmanız gereken şey, ulaşmak istediğiniz ip adresini tam olarak yazmaktır
       Örneğin: localhost:192.168.1.25:5432
                localhost:192.168.1.25:8888   ip'yi terminalden bulmak için;ipconfig yazman lazım

    PostgreSql'in güvenlik önlemi olarak localhost dışında tüm erişimlere kendisini kapatmasıdır.Bu nedenle
    localhost yerinde ip adresi kullandığınızda erişimi reddetmektedir.
      Bu nedenle; C:\Program Files\PostgreSQL\14\data giderek pg_hba.config de  "host    all   all 0.0.0.0/0   md5"