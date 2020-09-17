package com.kibi.command.defaults;

import com.kibi.command.ConsoleCommandSender;

public class StopCommand extends ConsoleCommandSender {
    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public String getUsage() {
        return "stop <args:null>";
    }

    @Override
    public String getDescription() {
        return "Stop Kibi services";
    }

    @Override
    public void execute(String[] args) {

    }
}
