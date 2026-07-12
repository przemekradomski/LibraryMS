# LibraryMS

LibraryMS jest to aplikacja webowa służąca do zarządzania biblioteką.
Umożliwia ona dodawanie, edytowanie i usuwanie książek, zarządzanie użytkownikami oraz wypożyczanie i zwracanie książek.
Aplikacja została stworzona przy użyciu biblioteki Spring Boot oraz bazy danych postgreSQL. Interfejs użytkownika został zrealizowany przy użyciu Thymelead.

## 🚀Technologie użyte w aplikacji
- Java
- spring Boot
- Thymeleaf
- PostgreSQL
- HTML/CSS/JavaScript

## 📋 funkcjonalności aplikacji
- Dodawanie, edytowanie i usuwanie książek
- Zarządzanie użytkownikami (dodawanie, edytowanie, usuwanie)
- Wypożyczanie i zwracanie książek
- Wyszukiwanie książek
- Wyświetlanie listy książek oraz użytkowników

## ⚙️ Uruchomienie aplikacji lokalnie
1. Skolunuj repozytorium na swój komputer:

   ```git clone https://www.github.com/przemekradomski/LibraryMS```
2. Utwórz baze danych

3. Uzupełnij dane w application.properties:
   spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteka
   spring.datasource.username=postgres
   spring.datasource.password=TWOJE_HASLO

4. Uruchom serwer

5. Wpisz w przeglądake adres:

   ``localhost:8080``

## 📁 Struktura projektu

```aiexclude
├───.idea
├───main
│   ├───java
│   │   └───pl
│   │       └───example
│   │           └───libraryManagment
│   │               ├───config
│   │               ├───controller
│   │               ├───dto
│   │               ├───entity
│   │               ├───repository
│   │               └───service
│   └───resources
│       ├───static
│       │   ├───css
│       │   └───js
│       └───templates
└───test
    └───java
        └───pl
            └───example
                └───libraryManagment
```

# ❗Uwaga❗
aplikacja jest w fazie alpha oraz jest testowana więc może zawerać błędy
