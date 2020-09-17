package com.kibi.command.defaults;

import com.kibi.Kibi;
import com.kibi.command.ConsoleCommandSender;

import java.util.Map;

public class HelpCommand extends ConsoleCommandSender {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getUsage() {
        return "help <args:null>";
    }

    @Override
    public String getDescription() {
        return "Get help list";
    }

    @Override
    public void execute(String[] args) {
        Map<String, ConsoleCommandSender> commands = Kibi.getServer().getCommandMap().getCommands();

        getSender().info("Available commands [" + commands.size() + "]");

        for (ConsoleCommandSender command : commands.values()) {
            getSender().info("Name: " + command.getName() + " || Usage: " + command.getUsage() + " || " + command.getDescription());
        }
    }
}
