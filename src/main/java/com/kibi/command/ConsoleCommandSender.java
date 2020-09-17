package com.kibi.command;

import com.kibi.Kibi;
import com.kibi.Logger;

public  abstract class ConsoleCommandSender {
    public abstract String getName();

    public abstract String getUsage();

    public abstract String getDescription();

    public Logger getSender() {
        return Kibi.getLogger();
    }

    public abstract void execute(String[] args);
}
