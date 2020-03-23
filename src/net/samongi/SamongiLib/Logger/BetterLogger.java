package net.samongi.SamongiLib.Logger;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class BetterLogger {

    private final Logger m_logger;
    private Level m_level;

    public BetterLogger(JavaPlugin plugin) {
        m_logger = plugin.getLogger();
        m_level = Level.INFO;
    }

    public BetterLogger(Logger logger) {
        m_logger = logger;
        m_level = Level.INFO;
    }

    public Level getLevel() {
        return this.m_level;
    }
    public void setLevel(Level m_level) {
        this.m_level = m_level;
    }
    public Logger getInnerLogger() {
        return this.m_logger;
    }
    private String prefix(Level level, String message) {
        return "[" + level.getName() + "] " + message;
    }
    private boolean shouldLog(Level level) {
        return level.intValue() >= m_level.intValue();
    }

    public void log(Level level, String message, Object param1) {
        if (!shouldLog(level))
            return;
        this.m_logger.log(Level.INFO, this.prefix(level, message), param1);
    }

    public void log(Level level, String message, Object... params) {
        if (!shouldLog(level))
            return;
        this.m_logger.log(Level.INFO, this.prefix(level, message), params);
    }

    public void log(Level level, String message) {
        if (!shouldLog(level))
            return;
        this.m_logger.log(Level.INFO, this.prefix(level, message));
    }

    public void info(String message) {
        this.log(Level.INFO, message);
    }
    public void info(String message, Object param1) {
        this.log(Level.INFO, message, param1);
    }
    public void info(String message, Object... params) {
        this.log(Level.INFO, message, params);
    }

    public void severe(String message) {
        this.log(Level.SEVERE, message);
    }
    public void severe(String message, Object param1) {
        this.log(Level.SEVERE, message, param1);
    }
    public void severe(String message, Object... params) {
        this.log(Level.SEVERE, message, params);
    }
    public void severe(String message, Exception err) {
        this.severe(message);
        if (err != null)
            this.severe(err.getMessage());
    }

    public void warning(String message) {
        this.log(Level.WARNING, message);
    }
    public void warning(String message, Object param1) {
        this.log(Level.WARNING, message, param1);
    }
    public void warning(String message, Object... params) {
        this.log(Level.WARNING, message, params);
    }
    public void warning(String message, Exception err) {
        this.warning(message);
        if (err != null)
            this.warning(err.getMessage());
    }

    public void fine(String message) {
        this.log(Level.FINE, message);
    }
    public void fine(String message, Object param1) {
        this.log(Level.FINE, message, param1);
    }
    public void fine(String message, Object... params) {
        this.log(Level.FINE, message, params);
    }

    public void finer(String message) {
        this.log(Level.FINER, message);
    }
    public void finer(String message, Object param1) {
        this.log(Level.FINER, message, param1);
    }
    public void finer(String message, Object... params) {
        this.log(Level.FINER, message, params);
    }

    public void finest(String message) {
        this.log(Level.FINEST, message);
    }
    public void finest(String message, Object param1) {
        this.log(Level.FINEST, message, param1);
    }
    public void finest(String message, Object... params) {
        this.log(Level.FINEST, message, params);
    }
}
