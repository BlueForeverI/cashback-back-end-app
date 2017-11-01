package com.cashback.api.util;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CashbackLogger {
    private Logger logger;

    public CashbackLogger() {
        logger = Logger.getLogger("com.cashback.api");
    }

    /**
     * Logs a generic exception
     *
     * @param exception the exception data to log
     */
    public void logException(Exception exception) {
        String stackTrace = String.join("\n",
                Arrays.stream(exception.getStackTrace())
                    .map(t -> t.toString())
                    .collect(Collectors.toList()));

        logger.log(Level.SEVERE, String.format("%s - %s; Stack Trace: %s\n", exception.toString(),
                exception.getMessage(), stackTrace));
    }

    /**
     * Logs a warning message
     *
     * @param warning the warning to log
     */
    public void logWarning(String warning) {
        logger.log(Level.WARNING, warning);
    }

    /**
     * Logs an info message
     *
     * @param info the message to log
     */
    public void logInfo(String info) {
        logger.log(Level.INFO, info);
    }
}
