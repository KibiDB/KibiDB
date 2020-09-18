package com.kibi.command.defaults;

import com.kibi.Kibi;
import com.kibi.command.ConsoleCommandSender;
import com.kibi.config.Config;
import com.kibi.net.Responses;

public class ClearCommand extends ConsoleCommandSender {
    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getUsage() {
        return "clear <args:null>";
    }

    @Override
    public String getDescription() {
        return "Delete all records from the database";
    }

    @Override
    public void execute(String[] args) {
        Config database = Kibi.getServer().getDataBase();

        database.getAll().forEach((key, value) -> {
            database.remove(key);
        });

        getSender().info(Responses.CLEAR_OK);
    }
}
