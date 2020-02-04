package Company.Employee;

import Company.Loggers.ConsoleLogger;
import Company.Loggers.FileLogger;
import Company.Loggers.MultiLogger;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import static Company.Employee.Sex.sexHandler;

public abstract class AbstractFactoryEmlopyees extends Employee {


    public AbstractFactoryEmlopyees(String firstName, String surname, Sex sex, Age age, BigDecimal employeeSalary) {
        super(firstName, surname, sex, age, employeeSalary);
    }

    private static MultiLogger multiLogger = new MultiLogger(new ConsoleLogger(), new FileLogger());

    @Override
    public BigDecimal getSalary() {
        return super.getEmployeeSalary();
    }

    public static Employee enterEmployee() {
        String imie;
        String nazwisko;
        char sexCOnsole;
        Sex sex;
        char category;
        String categoryConsoel;
        int ageVal;
        Age age;
        BigDecimal pensja;
        Scanner scan = new Scanner(System.in);

        System.out.print("Programista czy kierownik ?  [P/K] : ");
        categoryConsoel = scan.nextLine();
        category = typeOfEmployeeEvaluate(categoryConsoel);
        System.out.print("Podaj imię: ");
        imie = scan.nextLine();
        System.out.print("Podaj nazwisko: ");
        nazwisko = scan.nextLine();
        System.out.print("Podaj płeć [Male/Female] : ");
        sexCOnsole = sexHandler(scan.nextLine());
        sex = Sex.convertCharSexToEnumVal(sexCOnsole);
        System.out.print("Podaj wiek: ");
        age = new Age(scan.nextLine());
        System.out.print("Podaj zarobki: ");
        pensja = new BigDecimal(scan.nextLine()); // w ten sposób można obejść dawanie przecinka.podczas wpusywania pracownika z palca. można dawać kropkę.

/*        if (category == 'k') {
            System.out.print("Jaka premia do wypłaty:");
            BigDecimal premia = new BigDecimal(scan.nextLine());
            return new Sepervisior(imie, nazwisko, sex, age, pensja, premia);
        } else {
            System.out.print("Jaka technologia? :");
            String technologia = scan.nextLine();
            return new Programer(imie, nazwisko, sex, age, pensja, technologia);
        }*/
        switch (category) {
            case 'k': {
                System.out.print("Jaka premia do wypłaty:");
                BigDecimal premia = new BigDecimal(scan.nextLine());
                return new Sepervisior(imie, nazwisko, sex, age, pensja, premia);
            }
            default: {
                System.out.print("Jaka technologia? :");
                String technologia = scan.nextLine();
                return new Programer(imie, nazwisko, sex, age, pensja, technologia);
            }

        }
    }

    private static char typeOfEmployeeEvaluate(String category) {
        Scanner scan = new Scanner(System.in);
        char returningCategory;
        try {
            returningCategory = Character.toLowerCase(category.charAt(0));
        } catch (Exception e) {
            int i = 0;
            returningCategory = (char) i;
        }
        boolean outOfLoop = false;
        do {
            int tempInt = returningCategory;
            if (tempInt == 107) {
                outOfLoop = true;
            } else if (tempInt == 112) {
                outOfLoop = true;
            } else {
                System.out.println("Nieznany typ pracownika, podaj ponownie [P- programista; K-Kierownik]: ");
                try {
                    returningCategory = Character.toLowerCase(scan.nextLine().charAt(0));
                } catch (Exception e) {
                    int i = 0;
                    returningCategory = (char) i;
                }
            }
        } while (!outOfLoop);
        return returningCategory;
    }




    /*private String evaluateOfImportValue(String imp) {
        Scanner scan = new Scanner(System.in);
        String checkedValue = null;
        boolean swicher = false;
        do {
            for (int i = 0; i <= imp.length(); i++) {
                if (imp.charAt(i) <= 48 || 57 <= imp.charAt(i) || imp.charAt(i) != 46) {
                    Employee.employeeMultiLogger.error("Panie podałeś wartość pensji zasadniczej: " + imp + ". Popraw");
                    Employee.employeeMultiLogger.error("Podaj nową wartość (PAMIĘTAJ !! SEPARATOREM JEST \".\" !!):");
                    imp = scan.nextLine();
                    break;
                } else {
                    checkedValue = imp;
                }
            }
        } while (checkedValue == null);
        return checkedValue;
    }*/
}

