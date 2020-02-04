package Company.Loggers;

import java.io.Serializable;

public interface LoggerLocal extends Serializable {
    void info(String text);
    void error(String text);
//    default - metoda z tym opisem może mieć ciało w interface.
}
