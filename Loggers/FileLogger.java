package Company.Loggers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements LoggerLocal {

    private final File file;

    public FileLogger() {
        this.file = new File("logi.txt");
    }

    @Override
    public void info(String text) {
        String x = "[INFO_FILE]\t" + text;
        obsluga(x);
    }

    @Override
    public void error(String text) {
        String x = "[ERROR_FILE]\t" + text;
        obsluga(x);
    }

    public void obsluga(String tekst) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.append(tekst);
            fileWriter.append("\n");
            fileWriter.close(); //
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
