package Company.Employee;

import Company.Loggers.ConsoleLogger;
import Company.Loggers.FileLogger;
import Company.Loggers.MultiLogger;

import java.io.Console;
import java.io.FileWriter;
import java.util.Scanner;

public class Age {
    private static MultiLogger multiLogger = new MultiLogger(new ConsoleLogger(), new FileLogger());
    private static ConsoleLogger consoleLogger = new ConsoleLogger();

    private Scanner scan = new Scanner(System.in);
    private int age;

    public Age(String ageConsole) {
        setAge(ageConsole);
    }

    int getAge() {
        return age;
    }

    private void setAge(String ageConsole) {
        /* 1. sprawdz dlugosc ciagu znakow musi byc rowny 2.
         * 2. Sprawdz czy przysane znaki to cyfry.
         * 3. sprawdz czy przyslane znaki mieszcza sie w zakresie 14-65
         * 4. zwroc wiek.*/
        ageConsole = evaluateAgeConsoleFormat(ageConsole);
        this.age = ageConsoleEvaluationAndAscribe(ageConsole);
    }

    private String evaluateAgeConsoleFormat(String ageConsole) {
        boolean doAgeConsoleHasCurrectFormat = false;
        do {
            if (ageConsoleLengthControl(ageConsole) && ageConsoleCharCheck(ageConsole)) {
                doAgeConsoleHasCurrectFormat = true;
            } else {
                consoleLogger.info("Podano: \"" + ageConsole + "\"");
                consoleLogger.info("Podaj ponownie: ");
                ageConsole = scan.nextLine();
            }
        } while (!doAgeConsoleHasCurrectFormat);
        return ageConsole;
    }

    private boolean ageConsoleLengthControl(String ageConsole) {
        boolean outOfLoop = false;

        if (ageConsole.length() > 2) {
            consoleLogger.info("Podany ciag znakow jest za DLUGI.");
        } else if (ageConsole.length() < 2) {
            consoleLogger.info("Podany ciag znakow jest za KROTKI.");
        } else outOfLoop = true;
        return outOfLoop;
    }

    private boolean ageConsoleCharCheck(String ageConsole) {
        char char_1 = ageConsole.charAt(0);
        char char_2 = ageConsole.charAt(1);
        boolean outOfLoop = false;
        if (char_1 <=48 || char_1 > 58){
            System.out.println("Pierwszy znak to nie cyfra lub zero ( 0 )");
        } else if (char_2 < 48 || char_2 > 58) {
            System.out.println("Drugi znak znak to nie cyfra");
        } else outOfLoop = true;
        return outOfLoop;
    }

    private int ageConsoleEvaluationAndAscribe(String ageConsole) {
        boolean isCurrectRange = false;

        int ageConsoleVal = ageConsoneChangeType(ageConsole);
        do {
            if (evaluationOfAgeConsoleRange(ageConsoleVal)) {
                isCurrectRange = true;
            } else {
                System.out.println("Wartość podanego wieku poza zakresem. Podaj nową wartość:");
                String newValueOfAgeConsole = scan.nextLine();
                evaluateAgeConsoleFormat(newValueOfAgeConsole);
                ageConsoleVal = ageConsoneChangeType(newValueOfAgeConsole);
            }
        } while (!isCurrectRange);

        return ageConsoleVal;
    }

    private int ageConsoneChangeType(String ageConsoleVal) {
        char char_1 = ageConsoleVal.charAt(0);
        char char_2 = ageConsoleVal.charAt(1);
        int sumOfTwoChars = (((int) char_1 - 48) * 10) + ((int) char_2 - 48);
        return sumOfTwoChars;
    }

    private boolean evaluationOfAgeConsoleRange(int ageConsoleVal) {
        boolean evaluationOfAgeConsoleVal = false;
        if (ageConsoleVal <= 15) {
            multiLogger.error("Zatrudniasz małolata?");
            multiLogger.error("Nie możesz zatrudnić małolata");
            return evaluationOfAgeConsoleVal;
        } else if (ageConsoleVal > 65) {
            multiLogger.error("Zatrudniasz emeryta?");
            multiLogger.error("Nie możesz zatrudnić Emeryta");
//            System.out.println("Na pewno dobry wiek został podany? [Y/N]"); // tutaj będę chciał ustawić
//            możliwość zatrudnienia ale obsługę znaków Y i N muszę zrobić aby nic innego nie dało sie wybrać
            //więc póki co nie da się zatrudnić emeryta xD
            return evaluationOfAgeConsoleVal;
        } else {
            evaluationOfAgeConsoleVal = true;
            return evaluationOfAgeConsoleVal;
        }

    }
}
