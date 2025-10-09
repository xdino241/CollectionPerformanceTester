import java.util.Objects;

public class Car implements Comparable<Car> {
    private String brand;
    private String model;
    private int year;

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    @Override
    public String toString() {
        return this.brand + " " + this.model + " " + this.year;
    }

    @Override
    public int compareTo(Car o) {
        int res1 = brand.compareTo(o.brand);
        if (res1 != 0) return res1;

        int res2 = model.compareTo(o.model);
        if (res2 != 0) return res2;

        return year - o.year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && year == car.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, year);
    }
}
