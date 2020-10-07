package lombok.log;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog(topic="CounterLog")
public class LogExampleCommonsLog {

    public static void main(String... args) {
        log.error("Calling the 'CounterLog' with a message");
    }
}
