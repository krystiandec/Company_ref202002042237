package Company.Loggers;

public class ConsoleLogger implements LoggerLocal {

    @Override
    public void info(String text) {
        System.out.println(text);
//        System.out.println("[INFO]\t"+text);
    }

    @Override
    public void error(String text) {
        System.out.println("[ERROR]\t"+text);
    }
}
