# Akbar Rest App

## Opis Projektu

Akbar Rest App to aplikacja RESTowa napisana w Javie, służąca do zarządzania danymi szkół oraz personelem, stworzona na podstawie realnych wymagań firmy zajmującej się fotografią szkolną. Aplikacja oferuje funkcjonalności CRUD (Create, Read, Update, Delete) dla różnych encji takich jak szkoły, sprzedawcy, fotografowie i inne. Pozwala dodawać dane przez plik .csv pobrany ze stony ministerstwa (https://rspo.gov.pl/zaawansowana), które są przez aplikacje szczegółowo walidowane wraz z poźniejszym raportem z walidacji. 

## Funkcjonalności

- **Zarządzanie Szkołami**: CRUD dla szkół
- **Zarządzanie Personelem**: CRUD dla sprzedawców i fotografów
- **Walidacja Danych**: Walidacja danych wejściowych zarówno w formie json jak i .csv
- **Audyt**: Śledzenie i zapisywanie zmian w encji School

## Technologia

- **Język**: Java
- **Framework**: Spring Boot
- **Baza Danych**:  MySQL
- **Mapowanie Obiektów**: ModelMapper
- **Walidacja**: Hibernate Validator
- **Testy**: JUnit5, Mockito

## Instalacja i Uruchomienie
   1. git clone https://github.com/WojciechBarwinski/Akbar_Rest_App.git
   2. cd Akbar_Rest_App
   3. mvn clean install
   4. mvn spring-boot:run
   5. http://localhost:8080/swagger-ui/index.html

      komenda tworząca kontener dla projektu :
      - docker run --name mysql-akbar-app-db -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=akbar_app_db -p 3308:3306 mysql:latest
     
      ![image](https://github.com/user-attachments/assets/5b98eaf0-d138-4028-897e-825a83e0113c)
      ![image](https://github.com/user-attachments/assets/c09110e1-d603-43c2-babd-a1c137c1a16d)


