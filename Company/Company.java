package Company.Company;

import Company.Employee.AbstractFactoryEmlopyees;
import Company.Employee.Employee;
import Company.Employee.Programer;
import Company.Employee.Sex;
import Company.Loggers.ConsoleLogger;
import Company.Loggers.LoggerLocal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

import static Company.Company.SortCategories.*;
import static Company.Employee.Sex.*;

public class Company implements Serializable {
    //wszytskie obiekty w ytym podczas zapisu do pliku chce serializować. i albo dodajemy interface do tych obiektów.
    public ArrayList<Employee> employeeList = new ArrayList<>(); // tutaj dodaje listę do każdej instancji klasy Firma


    /*public ArrayList addEmployee() {
        ArrayList<Employee> employeeList = new ArrayList<>(); // i teraz możemy dodawać pracowników z fabryki albo kierownikow albo programistow

        return employeeList;
    }*/
    ConsoleLogger consoleLogger = new ConsoleLogger();
    private final LoggerLocal loger;

    public Company(LoggerLocal loger) {
        this.loger = loger;
    }

    public ArrayList addEmployee(Employee employee) {
        employeeList.add(employee);
        loger.info("Zatrudniono pracownika:\t" + employee.toString());
        return employeeList;
    }

    public void addEmployee() {
        Employee empTemp = AbstractFactoryEmlopyees.enterEmployee();
        addEmployee(empTemp);
//        employeeList.add(AbstractFactoryEmlopyees.enterEmployee());
//        loger.info("Zatrudniono pracownika" + employee.toString());

    }

    public double averangeAge() {
        int ageSum = 0;
        double averangeAge;
        for (Employee pracownik : employeeList) {
            ageSum = ageSum + pracownik.getAgeVal();
        }
        averangeAge = (double) ageSum / (double) employeeList.size();
        loger.info("średnia wieku wynosi: " + averangeAge);
        return averangeAge;
    }

    public void displayDataOfEmployee() {
        int positionOnLIst = 1;
        if (employeeList.isEmpty()) {
            loger.error("Panie kurwa lista jest pusta");
        } else {
            for (Employee employee : employeeList) {
                loger.info(positionOnLIst + "\t" + employee.toString());
                positionOnLIst++;
            }
        }
    }// ta funkcja ma nadpisywać docelowo funkcję wypisz. i ma wypisywać dame pracowników jak w Main obecnie

    public ArrayList removeEmployee(ArrayList<Employee> employeeList, int index) {
        if (index <= employeeList.size()) {
            employeeList.remove(index - 1);
            loger.info("Usunięto pracownika");
        } else {
            loger.error("Panie niepoprawny index.");
            loger.error("Nie ma pracownika pod tym indeksem, pracownicy mogą wystąpić pod indeksami od 1 do " + employeeList.size());
        }
        return employeeList;
    }


    public void sumOfSallary(ArrayList<Employee> employeeList) {
        BigDecimal sum = BigDecimal.ZERO;
        if (!employeeList.isEmpty()) {
            for (Employee pracownik : employeeList) {
                sum = sum.add(pracownik.getSalary());
            }
            loger.info("Suma zarobków w firmie wynosi: " + sum);
        } else {
            loger.error("Nie ma listy pracowników !!");
        }
//        System.out.printf(" %.3f", sum);
    }

    private BigDecimal sumOfSallaryAccSex(Sex sex) {
        BigDecimal sum = BigDecimal.ZERO;

        for (Employee pracownik : employeeList) {
            if (pracownik.getSex() == sex) {
                sum = sum.add(pracownik.getSalary());
            }
        }
        return sum;
    }

    public Map<Sex, BigDecimal> adjustSalaryAccToSex() {
        Map<Sex, BigDecimal> map_1 = new TreeMap<>();
        BigDecimal salary = sumOfSallaryAccSex(MEZCZYZNA);
        map_1.put(MEZCZYZNA, salary);
        salary = sumOfSallaryAccSex(KOBIETA);
        map_1.put(KOBIETA, salary);
        return map_1;
    }

    public Map<String, List<Programer>> programersTechnologyMap() {
        Map<String, List<Programer>> tempMap = new HashMap<>();
//        Map<String, List<Programer>> tempMap = technologyMapGenerator();
        for (Employee employee : employeeList) {
            if (employee instanceof Programer) {
                String technology = ((Programer) employee).getTechnology();
                Programer programer = (Programer) employee;
                List<Programer> programerList = tempMap.getOrDefault(technology, new ArrayList<>()); // tutaj podajemy gdzie mamy stworzyć Listę, Tworzymy za każdym razem nowy obiekt.
                programerList.add(programer);//dodajemy do listy tutaj tego pracownika zrzutoewanego na programistę
                //****metoda getOrDegfault pobiera daną wartość przy danym kluczu a jeżeli nie ma to tworzy nową listę, tak jak poniżej. czyli jest krócej
              /*  List<Programer> programerList = tempMap.get(technology); // tutaj podajemy gdzie mamy stworzyć Listę
                if (programerList == null) {
                    programerList = new ArrayList<>();
                    programerList.add(programer);
                } else {
                    programerList.add(programer);//dodajemy do listy tutaj tego pracownika zrzutoewanego na programistę
                }*/
                tempMap.put(technology, programerList);// doadjemy dopiero do mapy

            }
        }

        return tempMap;
    }

    public void ListSort() {
        Scanner scanner = new Scanner(System.in);
        consoleLogger.info("Opcje sortownia:");
        consoleLogger.info("1. Imionami.");
        consoleLogger.info("2. Nazwiskami.");
        consoleLogger.info("3. Wynagrodzeniem zasadniczym.");
        consoleLogger.info("4. Wiekiem.");
        consoleLogger.info("5. Wróc.");
        consoleLogger.info("Twój wybór: ");
        int numberConsole = scanner.nextInt();
        SortCategories number;
        number = sortChoice(numberConsole);
        switch (number) {
            case NAME: {
                Collections.sort(employeeList, Employee.employeeNameComparator);
            }
            break;
            case SURNAME: {
                Collections.sort(employeeList, Employee.employeeSurnameComparator);
            }
            break;
            case SALARY: {
                Collections.sort(employeeList, Employee.employeeSalaryComparator);
            }
            break;
            case AGE: {
                Collections.sort(employeeList, Employee.employeeAgeComparator);
            }
            break;
            case NO_ACTION:{

            }
            break;
            default: {
                loger.error("Nieprawidłowy kod sortownaia. wybrano: " + numberConsole);
            }
        }

    }
}