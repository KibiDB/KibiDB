package com.kibi.command.defaults;

import com.kibi.Kibi;
import com.kibi.command.ConsoleCommandSender;
import com.kibi.config.Config;
import com.kibi.net.Responses;

public class InsertCommand extends ConsoleCommandSender {
    @Override
    public String getName() {
        return "insert";
    }

    @Override
    public String getUsage() {
        return "insert <str:key> <str:value>";
    }

    @Override
    public String getDescription() {
        return "Insert a new record (if it exists it is overwritten)";
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            getSender().warning(getUsage());

            return;
        }

        if (args.length > 2) {
            getSender().warning("Value can only contain one element, try to separate the values with ':' example 'value1:value2'");

            return;
        }

        Config database = Kibi.getServer().getDataBase();
        String key = args[0];
        String value = args[1];

        database.set(key, value);

        getSender().info(Responses.INSERT_OK);
    }
}
