import java.util.Scanner;

@FunctionalInterface
public interface CreateElement<T> {
    T createElement(Scanner sc);
}
