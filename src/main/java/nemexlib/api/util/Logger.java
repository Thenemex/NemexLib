package nemexlib.api.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

/**
 * Class used for logging message to forge logs
 * <p>In all methods, messages will be concatenated one next to each other with no spaces ; and will call toString() for any Object met.</p>
 * <p>All levels are present, be aware that logging on a fatal level will likely stop the game.</p>
 */
@SuppressWarnings("unused")
public class Logger {

    protected final org.apache.logging.log4j.Logger logger;
    protected final String prefix;

    /**
     * Constructor for the Logger
     * @param modID modID from your mod
     */
    public Logger(String modID) {
        logger = LogManager.getLogger(modID);
        prefix = "[" + modID + "]";
    }

    /**
     * Logs a trace
     * @param messages The messages to log
     */
    public void trace(Object ... messages) {
        log(Level.TRACE, messages);
    }
    /**
     * Logs a debug
     * @param messages The messages to log
     */
    public void debug(Object ... messages) {
        log(Level.DEBUG, messages);
    }
    /**
     * Logs an information
     * @param messages The messages to log
     */
    public void info(Object ... messages) {
        log(Level.INFO, messages);
    }
    /**
     * Logs a warning
     * @param messages The messages to log
     */
    public void warn(Object ... messages) {
        log(Level.WARN, messages);
    }
    /**
     * Logs an error
     * @param messages The messages to log
     */
    public void error(Object ... messages) {
        log(Level.ERROR, messages);
    }
    /**
     * Logs a fatal (will likely crash the game)
     * @param messages The messages to log
     */
    public void fatal(Object ... messages) {
        log(Level.FATAL, messages);
    }

    /**
     * Default method used for concatenate all objets without casting them directly to String
     * @param level The level of logging
     * @param messages The messages to log
     */
    protected void log(Level level, Object ... messages) {
        String message = "";
        for (Object o : messages)
            message = (o != null) ? message.concat(o.toString()) : "null";
        logger.log(level, prefix.concat(" : ").concat(message));
    }
}
