import java.io.FileWriter;
import java.io.IOException;

public class TestResults implements TestTimers{

    private long startTime;
    private long endTime;
    @Override
    public void start() {
        startTime = System.nanoTime();
    }

    @Override
    public void stop() {
        endTime = System.nanoTime();
    }

    @Override
    public void getResults() {;
        System.out.println("Test sie powiodl, czas w  nanosekundach: " + (endTime - startTime));
    }

    @Override
    public void saveToCSV(String text) {
        try (FileWriter fwriter = new FileWriter("test_results.csv", true)){
            fwriter.write(text + "\n");
            fwriter.write("Czas w nanosekundach: " + (endTime - startTime) + "\n");
            fwriter.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
