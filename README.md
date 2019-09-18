# AdvertisementScreen
## Autorzy

- Andrzej Dybowski
- Stanisław Fiuta
- Damian Wiatrzyk
- Marcin Siwiński

## Opis projektu

### Cel projektu
Celem powyższego projektu jest stworzenie systemu do wyświetlania reklam oraz wiadomości.
Projekt ten został napisany w Javie (część odpowiedzialna za wyświetlanie reklam)
oraz w PHP (część odpowiedzialna za web interface).
Projekt, jak już wspomniano, dzieli się na 2 części:
- web interface,
- aplikacja wyświetlająca reklamy i wiadomości.

### Dlaczego wybrano taki temat
Temat tego projektu pozwala on rozwinąć wiedzę autorów na temat łączenia dwóch odrębnych aplikacji
w jeden system. Temat ten również zawiera się w technologiach, w które są już znane członkom zespołu,
więc nie istniał problem jej poznawania oraz poświęcania czasu na naukę.

Dodatkowo ugruntował on wiedzę autorów z poniższych technologi oraz rozwiną umiejętność łączenia dwóch
odrębnych aplikacji w cały system.

## Podział prac

- aplikacja wyświetlająca reklamy i wiadomości:
    - przygotowanie widoków oraz wyświetlania reklam i wiadomości: Stanisław Fiuta
    - przygotowanie obsługi plików oraz komunikacji z webinterface: Damian Wiatrzyk
- web interface:
    - przygotowanie obsług akcji użytkownika: Andrzej Dybowski
    - przygotowanie komunikacji z aplikacją wyświetlającą reklamy: Marcin Siwiński

## Funkcjonalności

### Wyświetlanie reklam
Reklamy przechowywane w bazie danych wyświetlaja się cyklicznie na ekranie bez udziału
użytkownika; cykliczność zapewnia konfiguracja, ustawiana przez użytkownika w panelu administracyjnym;
każda reklama ma określony czas na wyświetlenie; można wyróżnić kikla typów reklam do wyświetlania: obraz, film,
strona WWW, animacja; wyświetlanie reklam ma się odbywać na pełnym ekranie monitora.

### Wyświetlanie wiadomości
Wiadomości przechowywane w bazie danych wyświetlają się cyklicznie na pasku, u dołu ekranu;
kolejność i czas wyświetlania się wiadomości są zapisane w konfiguracji; konfiguracja ustawiana jest w panelu administracyjnym.

### Dodanie reklamy
Funkcjonalność zapewniająca dodanie reklamy każdego typu (film, animacja, obraz, strona WWW) do bazy
danych oraz ustawienia kolejności jej wyświetlania lub czasu przez jaki będzie widoczna na ekranie.

### Dodanie wiadomości
Funkcjonalność zapewniająca dodanie wiadomości do bazy danych oraz jej konfiguracja - czas wyświetlania
oraz kolejność w kolejce.

### Edycja reklamy
Funkcjonalność zapewniająca edycję dodanej już reklamy (jej typ oraz źródło) oraz jej konfiguracji.

### Edycja wiadomości
Funkcjonalność zapewniająca edycję istniejącej już wiadomości oraz jej konfigurację.

### Usunięcie reklamy
Funkcjonalność zapewniająca usunięcię istniejącej reklamy oraz jej źródeł (tylko w przypadku, gdy źródło jest przechowywane lokalnie na serwerze).

### Usunięcie wiadomości
Funkcjonalność zapewniająca usunięcie istniejącej wiadomości z bazy danych.

### Podgląd aktualnego stanu wyświetlanego na ekranie
Funkcjonalność zapewniająca podgląd jakie aktualnie są wyświetlane reklamy oraz wiadomości.

## Wybrane technologie

### Java: JavaFX, Gson
Wybranie języka Java daje możliwość uruchomienia aplikacji nie tylko na Raspberry PI
 ale też na innych urządzeniach mających zainstalowaną maszynę wirtualną Javy. Kolejnym czynnikiem, który skłonił
 do wyboru tego języka jest dostępność bibliotek oraz ich aktualność (a więc też i utrzymanie bezpieczeństwa samego
 systemu). JavaFX

### PHP
Ten język jest bardzo dobrze opisany oraz posiada dużą bazę frameworków - które są cyklicznie rozwijane
i naprawiane z błędów. Interpreter tego języka łatwo instaluje się na systemach wchodzących w skład rodziny Linuxa
(w popularnych dystrybucjach jest on dostępny jako paczka w package manager - więc nie należy kompilować go ze źródeł
oraz łatwo podnieść do wyższej wersji).

### Nginx
Jest to jeden z najpopularniejszych serwerów WWW dostępnych na platformę Raspberry PI (Raspbian).
Dzięki temu, że do obsługi połączeń przychodzących wykorzystuje wątki zamiast procesów (w porównaniu np z Apache),
daje możliwość obługi większej ilości zapytań HTTP wykorzystując przy tym mniej zasobów serwera. Łatwo też się
skaluje. Jest też dostępny jako paczka w Raspbianie - więc nie trzeba kompilować tego programu z kodu źródłowego.

## Architektura

System składa się z 2 głównych modułów oraz bazy danych w postacji łącznika. Dane na temat aktualnego stanu przesyłane
są za pomocą gniazd unixowych.

### Aplikacja wyświetlająca reklamy

![Adv Screen app](/docs/img/adv_screen.png?raw=true)

Aplikacja wyświetlająca reklamy składa się na 2 części:
- `ConfigController` - odpowiedzialną za ładowanie konfiguracji z bazy danych oraz wczytywaniem danych,
- `ViewController` - odpowiedzialną za wyświetlanie reklam oraz wiadomości.

Dzięki takiej architekurze można oddzielić warstwę ładującą dane od samego wyświetlania.

#### `ConfigController`

Posiada niezbędne metody do ładowania danych z bazy danych.
Korzysta on z Biblioteki `Gson`, zapewniającą ładowanie danych konfiguracyjnych.
Zawiera w sobie również metody ładujące dane samej reklamy (tj. plik obrazu/filmu lub stronę) ze źródeł lokalnych lub
zdalnych.
Po załadowaniu konfiguracji oraz danych, dane są ładowane w odpowiedniej kolejności do kolejki `ViewController`a.

#### `ViewController`

Jest odpowiedzialny za wyświetalnie reklam oraz wiadomości. Wykorzystuje w tym celu bibliotekę `JavaFx`.
Na żądanie jest też wstanie wygenerować aktualny podgląd aplikcaji.

### Panel administracyjny

![Admin Panel](/docs/img/web_interface.png?raw=true)

Jest odpowiedzialny za implementację funkcjonalności dodawania/usuwania oraz edytowania reklam i wiadomości.
Dane są zapisywane w bazie danych.
Panel został zaprojektowany w wzorcu architektonicznym MVC dzięki, któremu komunikacja z bazą danych oraz widoki
są oddzielone od siebie. Daje to przejrzystość kodu oraz łatwość w implementacji.

### Baza danych

![Database](/docs/img/database_json.png?raw=true)

Ze względu na małą ilość tabel (2 tabele: reklamy oraz wiadomości) zdecydowano, aby bazą danych były pliki JSON.
Lista plików:
- `advertisement.json` - plik, w którym przechowywana jest konfiguracja reklam;
- `news.json` - plik, w którym przechowywana jest konfiguracja wiadomości.

#### Lokalne pliki

Na potrzeby aplikacji pliki obrazów oraz filmów są przechowywane lokalnie odpowiednio w katalogach `video` oraz `img`.

## Problemy na które się natknęliśmy

Początkowo planowano napisać aplikację w samej Javie z wykorzystaniem MySQL. Jednak ze względów na ogranieczenia platformy
Raspberry PI porzucono ten pomysł na rzecz bardziej wydanjnieszych i szybszych rozwiązań - Panel Administracyjny został
napisany w PHP, jako serwer WWW wykorzystano Nginx, bazę danych zastąpiono plikami JSON.

## Instrukcja użytkowania aplikacji

Ze względu na to, że użytkownik ma wszystkie akcje w aplikacji są wykonywane tylko w Panelu Administratora, instrukcja
będzie dotyczyć tylko interfejsu użytkownika.

### Panel Administracyjny

- Dodawanie reklamy
    1) Należy klikną w przycisk `Dodaj reklamę`
    2) Uzupełnić odpowiednio:
        - typ reklamy
        - jej źródło - w przypadku: ładowania z Internetu adres **URI** / pliku: **załadować plik na serwer**
        - czas trwania
        - pozycja w kolejce
    3) Zatwierdzić operację przyciskiem `Dodaj`
- Usuwanie reklamy:
    1) Wybrać z listy intersującą nas reklamę
    2) Kliknąć w przycisk `Usuń`
    3) Potwierdzić operację przyciskiem `Ok`
- Edycja reklamy
    1) Wybrać interesującą nas reklamę
    2) Kliknąć w przycisk `Edytuj`
    3) Dokonać zmian pól wypisanych w *Dodawanie reklamy ii.*
    4) Zatwierdzić zmiany przyciskiem `Zapisz`
- Dodawanie wiadomości
  1) Należy klikną w przycisk `Dodaj wiadomość`
  2) Uzupełnić odpowiednio:
      - treść wiadomości
      - czas trwania
      - pozycja w kolejce
  3) Zatwierdzić operację przyciskiem `Dodaj`
- Usuwanie wiadomości:
  1) Wybrać z listy intersującą nas wiadomość
  2) Kliknąć w przycisk `Usuń`
  3) Potwierdzić operację przyciskiem `Ok`
- Edycja wiadomości
  1) Wybrać interesującą nas wiadomość
  2) Kliknąć w przycisk `Edytuj`
  3) Dokonać zmian pól wypisanych w *Dodawanie wiadomości ii.*
  4) Zatwierdzić zmiany przyciskiem `Zapisz`
