package Company.Employee;

import Company.Loggers.ConsoleLogger;
import Company.Loggers.FileLogger;
import Company.Loggers.MultiLogger;

import java.util.Scanner;

public enum Sex {
    MEZCZYZNA('M'),
    KOBIETA('F');

    private final char sexType;

    private static MultiLogger multiLogger = new MultiLogger(new ConsoleLogger(), new FileLogger());


    private char getSexType() {
        return Character.toLowerCase(sexType);
    }

    Sex(char sexType) {
        this.sexType = sexType;
    }

    public static Sex convertCharSexToEnumVal(char incomeChar) {
        Sex tempSex = null;
        do {
            for (Sex sex : values()) {
                if (Character.toLowerCase(incomeChar) == sex.getSexType()) {
                    tempSex = sex;
                }
            }
            if (tempSex == null) {
                multiLogger.error("Niepoprawny format płci [M/F]: ");
                Scanner scanner = new Scanner(System.in);
                incomeChar = sexHandler(scanner.nextLine());
            }
        } while (tempSex == null);
        return tempSex;
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public static char sexHandler(String sexHandlerImport) {
        Scanner scan = new Scanner(System.in);
        boolean swicher = false;
        char evaluateVal = ' ';
        do {
            try {
                evaluateVal = sexHandlerImport.charAt(0);
                swicher = true;
            } catch (IndexOutOfBoundsException e) {
                multiLogger.error("Nie może być pusta wartość!!!!");
                multiLogger.error("Podaj ponownie nie pustą wartość:");
                sexHandlerImport = scan.nextLine();
            }
        } while (!swicher);

        return evaluateVal;
    }

}

