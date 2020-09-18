package com.kibi.command.defaults;

import com.kibi.Kibi;
import com.kibi.Utils;
import com.kibi.command.ConsoleCommandSender;
import com.kibi.config.Config;

public class ListCommand extends ConsoleCommandSender {
    @Override
    public String getName() {
        return "list";
    }

    @Override
    public String getUsage() {
        return "list";
    }

    @Override
    public String getDescription() {
        return "Get a list of records from the database";
    }

    @Override
    public void execute(String[] args) {
        Config database = Kibi.getServer().getDataBase();

        getSender().info("| KEY | VALUE | [" + database.getAll().size() + "]");

        database.getAll().forEach((key, value) -> {
            getSender().info("| " + key + " | " + value + " |");
        });

        getSender().info("___________________");
    }
}
