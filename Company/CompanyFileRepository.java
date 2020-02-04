package Company.Company;

import Company.Loggers.ConsoleLogger;
import Company.Loggers.FileLogger;
import Company.Loggers.MultiLogger;

import java.io.*;

public class CompanyFileRepository implements CompanyRepository {
    MultiLogger multiLogger = new MultiLogger(new ConsoleLogger(), new FileLogger());
    private final static String FILE_NAME = "Company.bin";

    @Override
    public void save(Company company) {
        try {
            ObjectOutputStream outputObjectStream;
            OutputStream outputStream = new FileOutputStream(FILE_NAME);// ctrl+Shift+U - zmiana na upperCase
            // CRTRL+SHIFT+BACKSPACE  - wrcamy do miejsca gdzie pisaliśmy ostatnią linijkę.
            outputObjectStream = new ObjectOutputStream(outputStream);
            outputObjectStream.writeObject(company); // Trzeba przekkonwertować jakoś na format który da się zapisać, bo w tym momencie nie wie jak zapisać. Serializacja bin - do zapisu na dysku.
            outputObjectStream.close();
            multiLogger.info("***Zapisano do pliku***");
        } catch (IOException e) {
            e.printStackTrace();
            multiLogger.error("Panie coś jest nie tak z zapisem tego pliku. złapano błąd: " + e);
        }
    }

    @Override
    public Company load() {
        Company company = null;
        try {
            ObjectInputStream inputObjectStream;
            InputStream inputStream = new FileInputStream(FILE_NAME);
            inputObjectStream = new ObjectInputStream(inputStream);
            Object object = inputObjectStream.readObject();
            if (object instanceof Company) {
                company = (Company) object;
            }
            inputObjectStream.close(); // problem polega na tym, że jak się program przerwie to nie da się później zaczytać danych.
        } catch (IOException e) {
            multiLogger.error(e.toString());
        } catch (ClassNotFoundException e) {
            multiLogger.error(e.toString());
        } catch (NullPointerException ex) {
            multiLogger.error(ex.toString());
        } catch (RuntimeException rex) {
            multiLogger.error(rex.toString());
        }
        return company;
    }
}
