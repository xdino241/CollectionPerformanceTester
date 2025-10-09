import java.awt.*;
import java.util.Objects;

public class MyColor extends Color implements Comparable<MyColor>{

    private int sum;

    public MyColor(int r, int g, int b) {
        super(r, g, b);
        this.sum = r + g + b;
    }

    public int getSum() {
        return sum;
    }

    @Override
    public String toString() {
        return "("
                + super.getRed() + ", "
                + super.getGreen() + ", "
                + super.getBlue() + ")";
    }

    @Override
    public int compareTo(MyColor o) {
        int res1 = getRed() - o.getRed();
        if(res1 != 0) return res1;
        int res2 = getGreen() - o.getGreen();
        if(res2 != 0) return res2;
        return getBlue() - o.getBlue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyColor mycolor = (MyColor) o;
        return Objects.equals(getRed(), ((MyColor) o).getRed()) && Objects.equals(getBlue(), ((MyColor) o).getBlue()) && Objects.equals(getGreen(), ((MyColor) o).getGreen());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRed(), getBlue(), getGreen());
    }

}