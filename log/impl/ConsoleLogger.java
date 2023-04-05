package log.impl;

import java.time.LocalDateTime;

import log.ILogger;

public class ConsoleLogger implements ILogger {

    @Override
    public void log(String text) {
        System.err.println(LocalDateTime.now() + ": " + text);
    }

   
    
}
