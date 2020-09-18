package com.kibi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public void info(String message) {
        send("info", message);
    }

    public void warning(String message) {
        send("warning", message);
    }

    public void critical(String message) {
        send("critical", message);
    }

    public void error(String message) {
        send("error", message);
    }

    private void send(String prefix, String message) {
        if (message.length() <= 0) message = "Invalid language: Logger.send(message=?)";

        DateFormat dateFormat = new SimpleDateFormat("h:mm a");
        Date date = new Date();

        System.out.println("["+dateFormat.format(date)+"][Kibi][" + prefix.toUpperCase() + "]: " +message + "\n");
    }
}
