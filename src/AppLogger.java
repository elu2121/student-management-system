import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AppLogger {

    private static final String LOG_FILE = "app.log";

    public static void log(String message) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            pw.println(LocalDateTime.now() + " - " + message);
        } catch (IOException e) {
            System.out.println("⚠ Logging failed");
        }
    }
}
