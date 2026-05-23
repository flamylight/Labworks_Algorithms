import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    public static void run(){
        IO.println("–".repeat(10) + "TASK 1" + "–".repeat(10));
        String file1 = "task1_input.txt";

        List<String> text = readFile(file1);

        String regular = "//[a-z]*[F-K]+";

        Pattern pattern = Pattern.compile(regular);

        Matcher matcher;

        for (int i = 0; i < text.size(); i++){
            matcher = pattern.matcher(text.get(i));
            if (matcher.matches()){
                IO.println(text.get(i) + ": true");
            }
            else{
                IO.println(text.get(i) + ": false");
            }
        }
    }

    private static List<String> readFile(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath));
        } catch (IOException ex) {
            System.err.println("Помилка читання файлу: " + ex.getMessage());
            return new ArrayList<>();
        }
    }
}
