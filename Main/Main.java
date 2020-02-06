package Company.Main;

import Company.Company.Company;
import Company.Company.CompanyFileRepository;
import Company.Employee.*;
import Company.Loggers.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static Company.Employee.Sex.*;

public class Main {

    public static void main(String[] args) {
        LoggerLocal logger = new MultiLogger(new ConsoleLogger(), new FileLogger());
        LoggerLocal consoleLogger = new ConsoleLogger();

        Company company_1 = new Company(logger);

        CompanyFileRepository companyRepository = new CompanyFileRepository();
        /*companyRepository.save(company_1); // leci wyjątek bo nie wie jak zapisać ten objekt do pliku.
        //zapis dodać gdzieś do menu a odczyt zawsze zaraz po inicjalizacji firmy.
        if (company_1.employeeList.isEmpty()) {
            company_1 = companyRepository.load();
        }*/

        logger.info("-----@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-----");
        logger.info(LocalDateTime.now().toString());
        logger.info("-----@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-----");

        if (company_1.employeeList.isEmpty() && args.length > 0) {
            company_1.employeeList.add(new Sepervisior("Janek", "Kos", MEZCZYZNA, new Age("54"), new BigDecimal(8500), new BigDecimal(10))); // dodany kierownik, należy pamiętać o dodatkowym polu.
            company_1.employeeList.add(new Programer("Maria", "Wacko", KOBIETA, new Age("20"), new BigDecimal(6000), "Java"));
            company_1.employeeList.add(new Programer("Belatrycze", "Kutang", KOBIETA, new Age("45"), new BigDecimal(6000), "C#"));
            company_1.employeeList.add(new Programer("Antoni", "Mikulec", KOBIETA, new Age("27"), new BigDecimal(6000), "C#"));
            company_1.employeeList.add(new Programer("Iwona", "Dziad", KOBIETA, new Age("21"), new BigDecimal(6000), "C#"));
            company_1.employeeList.add(new Programer("Honorata", "Jeleń", MEZCZYZNA, new Age("23"), new BigDecimal(6000), "PYTHON"));
            company_1.employeeList.add(new Programer("Barnaba", "Pasek", MEZCZYZNA, new Age("36"), new BigDecimal(6000), "PYTHON"));
            company_1.employeeList.add(new Sepervisior("Marek", "Sawa", MEZCZYZNA, new Age("30"), new BigDecimal(8500), new BigDecimal(10)));
            company_1.employeeList.add(new Sepervisior("Henryk", "Kania", MEZCZYZNA, new Age("20"), new BigDecimal(8500), new BigDecimal(10)));
            company_1.employeeList.add(new Programer("Henryk", "Kania", MEZCZYZNA, new Age("33"), new BigDecimal(6500), "Java"));
            company_1.employeeList.add(new Programer("Iwona", "Kania", KOBIETA, new Age("31"), new BigDecimal(6000), "Java"));
            company_1.employeeList.add(new Programer("Antonii", "Mazur", MEZCZYZNA, new Age("22"), new BigDecimal(6800), "C++"));
            company_1.employeeList.add(new Programer("Iwona", "Wandas", KOBIETA, new Age("28"), new BigDecimal(6700), "C++"));
            company_1.employeeList.add(new Programer("Ewelina", "Mazur", KOBIETA, new Age("29"), new BigDecimal(6000), "C++"));
            company_1.employeeList.add(new Programer("Izabella", "Olszańska", KOBIETA, new Age("50"), new BigDecimal(6000), "Java"));
        } else if (company_1.employeeList.isEmpty() && args.length == 0) {
            company_1 = companyRepository.load();
        } else {
            logger.error("Lista pracowników jest pusta!!!");
        }

        MenuChoices number;
        boolean exit = false;
        do {
            Scanner scan = new Scanner(System.in);
            consoleLogger.info("Co chcesz zrobić: ");
            consoleLogger.info("1 - Dodaj pracownika.");
            consoleLogger.info("2 - Wypisz listę pracowników.");
            consoleLogger.info("3 - Wypisz średnią wieku pracowników.");
            consoleLogger.info("4 - Usuń pracownika.");
            consoleLogger.info("5 - Wypisz sumę zarobków w firmie.");
            consoleLogger.info("6 - Wypisz sumę zarobków w firmie w zależności od Płci.");
            consoleLogger.info("7 - Wypisz Programistów zajmujących się daną technologią.");
            consoleLogger.info("8 - Zapisz zmiany w liście pracowników.");
            consoleLogger.info("9 - Posortuj pracowników."); // podmenu posortopwać po czym. // imie, Nazwisko, wiek, pensja, stanowisko(?) - stanowisko nie jest polem a jest tylko dopisywane.
            //aby można było po nim sortować, to trzeba było by stworzyć dodatkowo w klasie Employee pole przyjmujące na jakim stanowisku jest zatrudniany - chyba zeby po klasie ?
            consoleLogger.info("100 - Wyjście z programu!");
//            logger.info("100 - Wyczyść konsolę!");// może kiedyś.
            logger.info("Twój wybór: ");
            int numberConsole = scan.nextInt();
            number = MenuChoices.convertNumberToAction(numberConsole);
            logger.info(" " + number.getNumber());

            switch (number) {
                case WRONG_NUMBER: {
                    logger.error("***Podano liczbę poza zakresem***");
                }
                break;
                case ADD_EMPLOYEE: {
                    consoleLogger.info("-------------------------------------");
//                    company_1.employeeList = company_1.addEmployee(AbstractFactoryEmlopyees.enterEmployee());
                    company_1.addEmployee();
//                    dodaję tutaj pracownika już do wstępni inicjalizowanej listy w klasie C003.
                    consoleLogger.info("-------------------------------------");
                }
                break;
                case DISPLAY_EMPLOYEE_LIST: {
                    consoleLogger.info("-------------------------------------");
                    logger.info("Lista pracowników: ");
                    if (company_1.employeeList != null) {
                        company_1.displayDataOfEmployee();
                    } else {
                        logger.error("Lista jest pusta. Dodaj kogoś albo wczytaj z pliku.");
                    }
                    consoleLogger.info("-------------------------------------");
                }
                break;
                case AVERANGE_OF_AGE_OF_EMPLOYEE: {
                    consoleLogger.info("-------------------------------------");
                    System.out.printf("średnia wieku wynosi: %.4f", company_1.averangeAge());
                    consoleLogger.info("");
                    consoleLogger.info("-------------------------------------");
                }
                break;
                case REMOVE_EMPLOYEE: {
                    consoleLogger.info("-------------------------------------");
                    logger.info("usuwanie pracownika");
                    consoleLogger.info("Podaj numer pracownika: ");
                    int index = scan.nextInt();
                    company_1.removeEmployee(company_1.employeeList, index);
                    /*// pętla for jest po to tylko aby sprawdzić czy to zadziałało i porównać sobie z listą pracowników.
                    for (int i = 0; i < company_1.employeeList.size(); i++) {
                        consoleLogger.info(company_1.employeeList.get(i).toString());
                    }*/
                    consoleLogger.info("-------------------------------------");
                }
                break;
                case DISPLAY_SUM_OF_SALLARY: {
                    consoleLogger.info("-------------------------------------");
                    company_1.sumOfSallary(company_1.employeeList);
                    consoleLogger.info("");
                    consoleLogger.info("-------------------------------------");
                }
                break;
                case DISPLAY_SUM_OF_SALLARY_SEX_DEPENDING: {
                    consoleLogger.info("-------------------------------------");
                    Map<Sex, BigDecimal> map_temp = company_1.adjustSalaryAccToSex();
                    for (Sex plec : map_temp.keySet()) {
                        consoleLogger.info(plec.toString() + " zarabiają: " + map_temp.get(plec));
                    }
                    consoleLogger.info("-------------------------------------");
                }
                break;
                case COLLECT_INFORMATION_ABOUT_PROGRAMRS_DEPENDING_OF_TECHNOLOGY: {
                    consoleLogger.info("-------------------------------------");
                    logger.info("Lista Programistów w zależności od technologii w jakiej pracują.");
                    Map<String, List<Programer>> programersTechnologyMap = company_1.programersTechnologyMap();
                    for (String technology : programersTechnologyMap.keySet()) {
                        consoleLogger.info("Technologia: " + technology + "(ilość osób: " + programersTechnologyMap.get(technology).size() + ")");
                        List<Programer> programersList = programersTechnologyMap.get(technology);
                        for (Programer programer : programersList) {
                            consoleLogger.info(programer.toString());
                        }
                    }
                    consoleLogger.info("-------------------------------------");
                }
                break;
                case SAVE_EMPLOYEELIST: {
                    companyRepository.save(company_1); // leci wyjątek bo nie wie jak zapisać ten objekt do pliku.
                }
                break;
                case SORTING: {
                    company_1.ListSort();
                }
                break;
                case EXIT: {
                    consoleLogger.info("-------------------------------------");
                    logger.info("Wychodzę z programu");
                    exit = true;
                }
                default: {
                    logger.info("Dzięki !!!");
                    companyRepository.save(company_1);
                }
            }
        } while (exit == false);
    }
}

/*to do list:
 *[DONE] 1. sortowanie
 *[TO DO] ! 2. wiek wyliczany z podanej daty
 *[DONE] 3. repozytorium - , DZIAŁA - problem był z wiekiem i jego przekazaniem do pliku. nie można było przekazaćze względu na java.until.Scanner. )
 *[DONE] 4. logi aby do pliku była zapisywana akcja i jej rezultat tylko a na konsoli aby się wyświetlało wszystko. - trzeba rozdzielać
 *[DONE] 5. Mapy - przeanalizowane - metoda getOrDefault działa nieźle.
 *[DONE] 6. Obsługa płci - wywala wyjątek przy wartości null - obsłużyć jakoś.
 *[DONE] 7. Obsługa zakresu pensji zasadniczej. przy podaniu wartości innej niż w formacie 4521.02 wywala błąc - obsłużyć.
 *[DONE] 8. Obsługa premii dla kierownika, ta sama sytuacja co dla pensji zasadniczej tutaj zacieśnić zakres do dodatnich int w zakresie 1-100.
 *[DONE] 9. Poprawić obsługę pobierania nazwy pracownika, wyciągnąć na zewnątrz metodę sprawdzającą czy podany znak nie jest null. warunek w innej metodzie, w else i ponownym podaniu sprawdzić czy nie null przez wcześniejszą metodę.
 * */

/*Problemy:
 * 1. Czasem kiedy zatrzymam probram to pojawia się błąd przy próbie odczytu z pliku *.bin. */
