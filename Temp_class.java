package Company;

import Company.Company.Company;
import Company.Employee.Age;
import Company.Employee.Sepervisior;
import Company.Employee.Programer;
import Company.Loggers.ConsoleLogger;
import Company.Loggers.LoggerLocal;

import java.math.BigDecimal;
import java.util.Scanner;

import static Company.Employee.Sex.KOBIETA;
import static Company.Employee.Sex.MEZCZYZNA;

public class Temp_class {

    public static void main(String[] args) {
        LoggerLocal kLogger = new ConsoleLogger();
        Scanner scanner = new Scanner(System.in);
        Company company2 = new Company(kLogger);
        if (args.length > 0) {
            company2.employeeList.add(new Sepervisior("Janek", "Kos", MEZCZYZNA, new Age("54"), new BigDecimal(4000), new BigDecimal(10))); // dodany kierownik, należy pamiętać o dodatkowym polu.
            company2.employeeList.add(new Programer("Maria", "Wacko", KOBIETA, new Age("23"), new BigDecimal(2000), "Java"));
            company2.employeeList.add(new Programer("Belatrycze", "Kutang", KOBIETA, new Age("23"), new BigDecimal(2000), "Java"));
            company2.employeeList.add(new Programer("Antoni", "Mikulec", KOBIETA, new Age("23"), new BigDecimal(2000), "Java"));
            company2.employeeList.add(new Programer("Iwona", "Dziad", KOBIETA, new Age("23"), new BigDecimal(2000), "Java"));
            company2.employeeList.add(new Programer("Honorata", "Jeleń", MEZCZYZNA, new Age("23"), new BigDecimal(2000), "Java"));
            company2.employeeList.add(new Programer("Barnaba", "Pasek", MEZCZYZNA, new Age("23"), new BigDecimal(2000), "C++"));
            company2.employeeList.add(new Sepervisior("Marek", "Sawa", MEZCZYZNA, new Age("23"), new BigDecimal(2000), new BigDecimal(10)));
            company2.employeeList.add(new Sepervisior("Henryk", "Kania", MEZCZYZNA, new Age("23"), new BigDecimal(2000), new BigDecimal(10)));
            company2.employeeList.add(new Programer("Henryk", "Kania", MEZCZYZNA, new Age("23"), new BigDecimal(2000), "Java"));
            company2.employeeList.add(new Programer("Iwona", "Kania", KOBIETA, new Age("23"), new BigDecimal(2000), "Java"));
            company2.employeeList.add(new Programer("Antonii", "Mazur", MEZCZYZNA, new Age("23"), new BigDecimal(2000), "C++"));
            company2.employeeList.add(new Programer("Iwona", "Wandas", KOBIETA, new Age("23"), new BigDecimal(2000), "C++"));
            company2.employeeList.add(new Programer("Ewelina", "Mazur", KOBIETA, new Age("23"), new BigDecimal(2000), "C++"));
            company2.employeeList.add(new Programer("Izabella", "Olszańska", KOBIETA, new Age("23"), new BigDecimal(2000), "Java"));
        }

        String textCOnsole =scanner.nextLine();
        System.out.println(textCOnsole.length());
        Age age = new Age(textCOnsole);

//        System.out.println("Podałeś: " + age.getAge());




    }
}
