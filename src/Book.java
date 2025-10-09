import java.util.Objects;

public class Book implements Comparable<Book> {
    private String title;
    private String author;
    private int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    @Override
    public int compareTo(Book o) {
        int res1 = title.compareTo(o.title);
        if (res1 != 0) return res1;

        int res2 = author.compareTo(o.author);
        if (res2 != 0) return res2;

        return year - o.year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(author, book.author) && year == book.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    };

    @Override
    public String toString() {
        return author + " " + title + " " + year;
    }
}
