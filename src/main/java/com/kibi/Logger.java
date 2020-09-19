package com.kibi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RESET = "\u001B[0m";

    public void info(String message) {
        send("info", ANSI_RESET + message);
    }

    public void warning(String message) {
        send("warning", ANSI_YELLOW + message);
    }

    public void error(String message) {
        send("error", ANSI_RED + message);
    }

    private void send(String prefix, String message) {
        if (message.length() <= 0) message = "Invalid language: Logger.send(message=?)";

        DateFormat dateFormat = new SimpleDateFormat("h:mm a");
        Date date = new Date();

        System.out.println(ANSI_PURPLE + "["+dateFormat.format(date) +  "]" + ANSI_CYAN + "[Kibi]" + ANSI_RESET + "[" + prefix.toUpperCase() + "]: " + message + ANSI_RESET + "\n");
    }
}
