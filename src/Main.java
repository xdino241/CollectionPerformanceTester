import java.util.*;
import java.util.stream.IntStream;

public
    class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz typ danych");
        DataType selectedType = checkSelected(DataType.class, scanner);

        System.out.println("Wybierz rozmiar danych");
        DataSize selectedSize = checkSelected(DataSize.class, scanner);
        int setSize = (selectedSize == DataSize.CUSTOM) ? customSize() : selectedSize.getSize();

        System.out.println("Wybierz typ kolekcji");
        CollectionType selectedCollectionType = checkSelected(CollectionType.class, scanner);

        Collection<?> testData = createData(selectedType, setSize, selectedCollectionType);
        System.out.println("Wybierz testy, ktore chcesz wykonac");
        makeTests(testData, selectedType, scanner);
    }

    public static <T extends Enum<T>> String enString(Class<T> enumType) {
        while(true) {
                int index = 1;
                StringBuilder result = new StringBuilder();
                for (T enm : enumType.getEnumConstants()) {
                    result.append(index).append(". ").append(enm.name()).append("\n");
                    index++;
                }
                result.append("Wybierz opcje: ");
                return result.toString();
            }
        }

    public static <T extends Enum<T>> T checkSelected(Class<T> enType, Scanner sc) {
        while(true) {
            try {
                System.out.println(enString(enType));
                int input = Integer.parseInt(sc.nextLine());
                T[] values = enType.getEnumConstants();

                if (input > 0 && input <= values.length) {
                    return values[input - 1];
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int customSize() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            try {
                System.out.print("Podaj ilosc elementow: ");
                int size = Integer.parseInt(sc.nextLine());
                if(size < 0){
                    System.out.println("Wartosc nie moze byc ujemna");
                }else{
                    return size;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static <T> Collection<T> createData(DataType type, int size, CollectionType colType) {
        Collection<T> data = switch (colType) {
            case ARRAYLIST -> new ArrayList<>(size);
            case LINKEDLIST -> new LinkedList<>();
            case HASHSET -> new HashSet<>(size);
            case TREESET -> new TreeSet<>();
        };
        IntStream.range(0, size)
                .mapToObj(i -> switch (type) {
                    case INTEGER -> (T) Integer.valueOf((int) (Math.random() * 256));
                    case DOUBLE -> (T) Double.valueOf(Math.random() * 256);
                    case PERSON -> (T) new Person("Osoba" + (int)(Math.random()*100)+1, (int) ((Math.random()*100)+1));
                    case MY_COLOR -> (T) new MyColor(
                            (int) (Math.random() * 256),
                            (int) (Math.random() * 256),
                            (int) (Math.random() * 256));
                    case CAR -> (T) new Car("Marka" + (int)(Math.random()*100)+1, "Model" + (int)((Math.random()*100)+1), 1925 + (int) ((Math.random()*100)+1));
                    case BOOK -> (T) new Book("Tytul" + (int)((Math.random()*100)+1), "Autor" + (int)((Math.random()*100)+1), 1925 + (int) ((Math.random()*100)+1));
                })
                .forEach(data::add);
        return data;
    }

    public static <T> void makeTests(Collection<T> collection, DataType type, Scanner sc) {
        while(true) {
            try {
                DataTests test = checkSelected(DataTests.class, sc);
                switch (test) {
                    case ADD_ITEM -> addElement(collection, makeElement(type, sc));
                    case REMOVE_ITEM -> removeElement(collection, makeElement(type, sc));
                    case CHECK_EXISTENCE -> checkExistence(collection, makeElement(type, sc));
                    case SEARCH_ITEM -> searchItem(collection, makeElement(type, sc));
                    case ITEM_ON_INDEX -> {
                        System.out.println("Podaj indeks:");
                        Scanner scanner = new Scanner(System.in);
                        int index = scanner.nextInt();
                        scanner.nextLine();
                        itemOnIndex((List<T>) collection, index);
                    }
                    case END_PROGRAM -> System.exit(0);
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static <T> T makeElement(DataType type, Scanner sc) {
        CreateElement<T> cElement;
        switch (type) {
            case INTEGER -> cElement = (scan) -> {
                System.out.println("Podaj liczbe: ");
                return (T) Integer.valueOf(scan.nextLine());
            };
            case DOUBLE -> cElement = (scan) -> {
                System.out.println("Podaj liczbe: ");
                return (T) Double.valueOf(scan.nextLine());
            };
            case PERSON -> cElement = (scan) -> {
                System.out.println("Podaj imie: ");
                String name = scan.nextLine();
                System.out.println("Podaj wiek: ");
                int age = Integer.parseInt(scan.nextLine());
                return (T) new Person(name, age);
            };
            case BOOK -> cElement = (scan) -> {
                System.out.println("Podaj tytul: ");
                String title = scan.nextLine();
                System.out.println("Podaj autora: ");
                String author = scan.nextLine();
                System.out.println("Podaj rok: ");
                int year = Integer.parseInt(scan.nextLine());
                return (T) new Book(title, author, year);
            };
            case CAR -> cElement = (scan) -> {
                System.out.println("Podaj marke: ");
                String brand = scan.nextLine();
                System.out.println("Podaj model: ");
                String model = scan.nextLine();
                System.out.println("Podaj rok: ");
                int year = Integer.parseInt(scan.nextLine());
                return (T) new Car(brand, model, year);
            };
            case MY_COLOR -> cElement = (scan) -> {
                System.out.println("Podaj R: ");
                int red = Integer.parseInt(scan.nextLine());
                System.out.println("Podaj G: ");
                int green = Integer.parseInt(scan.nextLine());
                System.out.println("Podaj B: ");
                int blue = Integer.parseInt(scan.nextLine());
                return (T) new MyColor(red, green, blue);
            };
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
        return cElement.createElement(sc);
    }

    public static <T> void addElement(Collection<T> collection, T item) {
        TestResults tst = new TestResults();
        tst.start();
        collection.add(item);
        tst.stop();
        tst.getResults();
        tst.saveToCSV("dodawanie elementu");
    }

    private static <T> void removeElement(Collection<T> collection, T item) {
        TestResults tst = new TestResults();
        tst.start();
        if(collection.contains(item)){
            collection.remove(item);
            tst.stop();
            tst.getResults();
            tst.saveToCSV("usuwanie elementu");
        }
        else{
            System.out.println("Test sie nie powiodl, nie istnieje taki element w Kolekcji");
        }
    }

    private static <T> void checkExistence(Collection<T> collection, T item) {
        TestResults tst = new TestResults();
        tst.start();
        if (collection.contains(item)) {
            tst.stop();
            tst.getResults();
            tst.saveToCSV("sprawdzanie istnienia elementu w kolekcji");
        } else {
            System.out.println("Kolejka nie posiada danej wartosci");
            tst.stop();
        }
    }

    public static <T> void searchItem(Collection<T> collection, T item) {
        TestResults tst = new TestResults();
        boolean found = false;
        tst.start();
        for (T value : collection) {
            if(value.equals(item)) {
                found = true;
            }
        }
        if(!found) {
            System.out.println("Test sie nie powiodl, kolekcja nie posiada danej wartosci");
            tst.stop();
        }
        else{
            tst.stop();
            tst.getResults();
            tst.saveToCSV("wyszukiwanie elementu");
        }
    }

    public static <T> void itemOnIndex(List<T> list, int index) {
        TestResults tst = new TestResults();
        tst.start();
        if(index >= 0 && index < list.size()) {
            T item = list.get(index);
            System.out.println("Na indexie: " + index + " znajduje sie element: " + item );
            tst.stop();
            tst.getResults();
            tst.saveToCSV("odczyt po indeksie");
        }
        else {
            System.out.println("Test sie nie powiodl, index wykracza poza granice");
            tst.stop();
        }
    }
}
