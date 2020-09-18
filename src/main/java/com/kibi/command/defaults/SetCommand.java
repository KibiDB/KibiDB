package com.kibi.command.defaults;

import com.kibi.Kibi;
import com.kibi.command.ConsoleCommandSender;
import com.kibi.config.Config;
import com.kibi.net.Responses;

public class SetCommand extends ConsoleCommandSender {
    @Override
    public String getName() {
        return "set";
    }

    @Override
    public String getUsage() {
        return "set <str:key> <str:value>";
    }

    @Override
    public String getDescription() {
        return "Change the value of a key";
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

        if (!database.exists(key)) {
            getSender().info(Responses.SET_NULL);

            return;
        }

        database.set(key, value);

        getSender().info(Responses.SET_OK);
    }
}
