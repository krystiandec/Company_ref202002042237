package Company.Loggers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiLogger implements LoggerLocal {

    private List<LoggerLocal> localLoggersList;

    private MultiLogger(List<LoggerLocal> localLoggersList) {
        this.localLoggersList = localLoggersList; // i ten konstruktor dopiero przypisuje ,loggery do listy wewnętrznej.
        // oznacza to, że podczas Tworzenia instancji tej klasy od razu tworzy się ten konstruktor który inicjalizuje ArrayList
// można też srtobić konstruktor który będzie przyjmował listę i tę przytjętą listę będzie przypisywał to zainicjalizowanej Listy
// szybkie tworzenie listy :
//        Arrays.asList(new FileLogger(), new ConsoleLogger()); - i to przypisujemy do naszej
//takie coś powyżej na zewnątrz i przysłać tutaj i przypisać do tej listy.
    }

    public MultiLogger(LoggerLocal... localLoggersList) {
// przysyłamy argumenty - w postaci taqblicy
//        wywołanie z zewnątrz: MultiLogger(new FileLogger, new ConsoleLogger)
//        i takie coś przysłane diopiero musimy przypisać do listy.
/* /////////////////////////////////////////
// jedna z metod dodania elementów do listy
        this.localLoggersList = new ArrayList<>();
        for (LoggerLocal logs : loggerLocals) {
            this.localLoggersList.add(logs);
        }*/
        this(Arrays.asList(localLoggersList)); // i to leci do konstruktora wyżej przez słowo kluczowe this.
        // tutaj można wykonać przelotkę aby wykonał się konstruktor z klasy tej i wysłać do niego listę.
//        Listę otrzymamy z przysłanej tablicy i za pomocą Arrays.asList po kolei dodać do tablicy i wysłać do konstruktora, który może być już prywatny.

    }

    @Override
    public void info(String text) {
//        System.out.println(localLoggersList);
        for (LoggerLocal loggerLocal : localLoggersList) {
            loggerLocal.info(text);
        }
   }

    @Override
    public void error(String text) {
        for (LoggerLocal loggerLocal : localLoggersList) {
            loggerLocal.error(text);
        }

    }


}
