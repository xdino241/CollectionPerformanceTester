# Java Collection Tester

An interactive console application for benchmarking the performance of different Java collection types. Choose a data type, collection size, and collection type, then measure the execution time of basic operations.

---

## Features

- **Data types:** `Integer`, `Double`, `Person`, `MyColor`, `Car`, `Book`
- **Collection sizes:** 100, 500, 1 000, 10 000, or a custom value
- **Collection types:** `ArrayList`, `LinkedList`, `HashSet`, `TreeSet`
- **Tested operations:** add, remove, iterative search, contains check, index access (lists only)
- Execution time measured in **nanoseconds**
- Results automatically saved to `test_results.csv`

---

## Project Structure

```
├── Main.java            # Application entry point, menu, test runner
├── TestResults.java     # Time measurement & CSV export
├── TestTimers.java      # Timer interface
├── CreateElement.java   # Functional interface for element creation
├── CollectionType.java  # Enum of collection types
├── DataType.java        # Enum of data types
├── DataSize.java        # Enum of collection sizes
├── DataTests.java       # Enum of available operations
├── Person.java          # Data model – person
├── Car.java             # Data model – car
├── Book.java            # Data model – book
└── MyColor.java         # Data model – RGB color (extends java.awt.Color)
```

---

## Requirements

- Java 17+

---

## Running

```bash
javac *.java
java Main
```

---

## How to Use

1. Run the program
2. Select a data type (e.g. `INTEGER`, `BOOK`)
3. Select a collection size (e.g. `THOUSAND` = 1 000 elements)
4. Select a collection type (e.g. `ARRAYLIST`, `TREESET`)
5. Select an operation to test
6. Provide input if prompted
7. The program displays the operation time and appends it to `test_results.csv`

---

## Test Results

Results are automatically appended to `test_results.csv` in the following format:

```
adding element
Time in nanoseconds: 12345
```

The file accumulates results across multiple sessions.

---

## Data Models

Each model implements `Comparable` and overrides `equals()` and `hashCode()`, ensuring correct behaviour with `HashSet` and `TreeSet`.

| Class | Fields | Comparison order (`compareTo`) |
|-------|--------|-------------------------------|
| `Person` | `name`, `age` | name → age |
| `Car` | `brand`, `model`, `year` | brand → model → year |
| `Book` | `title`, `author`, `year` | title → author → year |
| `MyColor` | `red`, `green`, `blue` (RGB) | R → G → B |

---

## Notes

- `ITEM_ON_INDEX` only works with `ArrayList` and `LinkedList` — calling it on a `Set` will throw an exception.
- Data is generated randomly on startup, so results will vary between runs.
- `test_results.csv` is appended to on every test run; results accumulate across sessions.
