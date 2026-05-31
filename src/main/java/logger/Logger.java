package logger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static Logger instance;

    private static final String RESET = "\u001B[0m";
    private static final String AMARILLO = "\u001B[33m";
    private static final String VERDE = "\u001B[32m";
    private static final String GRIS = "\u001B[37m";
    private static final String ROJO = "\u001B[31m";

    private Logger() {
        System.out.println("--- Logger inicializado por primera vez ---");
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    private String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void logWarning(String msg) {
        printLog(AMARILLO, "[WARN]", msg);
    }

    public void logDebug(String msg) {
        printLog(VERDE, "[DEBUG]", msg);
    }

    public void logInfo(String msg) {
        printLog(GRIS, "[INFO]", msg);
    }

    public void logError(String msg) {
        printLog(ROJO, "[ERROR]", msg);
    }

    private void printLog(String color, String prefix, String msg) {
        System.out.println(color + prefix + " [" + getTimestamp() + "] " + msg + RESET);
    }
}
