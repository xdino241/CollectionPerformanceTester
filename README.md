# Java Collection Tester

Interaktywna aplikacja konsolowa do testowania wydajności różnych typów kolekcji w Javie. Pozwala wybrać typ danych, rozmiar zbioru oraz rodzaj kolekcji, a następnie mierzyć czas wykonania podstawowych operacji.

---

## Funkcjonalności

- Wybór typu danych: `Integer`, `Double`, `Person`, `MyColor`, `Car`, `Book`
- Wybór rozmiaru danych: 100, 500, 1000, 10 000 lub własna wartość
- Wybór typu kolekcji: `ArrayList`, `LinkedList`, `HashSet`, `TreeSet`
- Testowanie operacji: dodawanie, usuwanie, wyszukiwanie iteracyjne, sprawdzanie istnienia (`contains`), odczyt po indeksie (tylko listy)
- Pomiar czasu operacji w nanosekundach
- Automatyczny zapis wyników do pliku `test_results.csv`

---

## Struktura projektu
├── Main.java             # Główna logika aplikacji, menu, testy

├── TestResults.java      # Implementacja pomiaru czasu i zapisu CSV

├── TestTimers.java       # Interfejs timera

├── CreateElement.java    # Interfejs funkcyjny do tworzenia elementów

├── CollectionType.java   # Enum typów kolekcji

├── DataType.java         # Enum typów danych

├── DataSize.java         # Enum rozmiarów danych

├── DataTests.java        # Enum dostępnych testów

├── Person.java           # Model danych – osoba

├── Car.java              # Model danych – samochód

├── Book.java             # Model danych – książka

└── MyColor.java          # Model danych – kolor RGB (rozszerza java.awt.Color)

---

## Uruchomienie

Wymagania: Java 17+

```bash
javac *.java
java Main
```

---

## Jak używać

1. Uruchom program
2. Wybierz typ danych (np. `INTEGER`, `BOOK`)
3. Wybierz rozmiar kolekcji (np. `THOUSAND` = 1000 elementów)
4. Wybierz typ kolekcji (np. `ARRAYLIST`, `TREESET`)
5. Wybierz operację do przetestowania
6. Podaj dane wejściowe jeśli wymagane
7. Program wyświetli czas operacji i zapisze go do `test_results.csv`

---

## Wyniki testów

Wyniki zapisywane są automatycznie do pliku `test_results.csv` w formacie:
dodawanie elementu

Czas w nanosekundach: 12345

---

## Modele danych

Każdy model implementuje `Comparable` oraz nadpisuje `equals()` i `hashCode()`, co zapewnia poprawne działanie z `HashSet` i `TreeSet`.

| Klasa     | Pola                         | Porównywanie (compareTo) |
|-----------|------------------------------|--------------------------|
| `Person`  | `name`, `age`                | nazwa → wiek             |
| `Car`     | `brand`, `model`, `year`     | marka → model → rok      |
| `Book`    | `title`, `author`, `year`    | tytuł → autor → rok      |
| `MyColor` | `red`, `green`, `blue` (RGB) | R → G → B                |

---

## Uwagi

- Operacja `ITEM_ON_INDEX` działa tylko dla `ArrayList` i `LinkedList`. Na `Set` spowoduje wyjątek.
- Dane generowane są losowo przy starcie, każde uruchomienie daje inne wyniki.
- Plik `test_results.csv` jest dopisywany przy każdym teście, wyniki kumulują się między sesjami.
