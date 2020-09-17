package com.kibi.command.defaults;

import com.kibi.Kibi;
import com.kibi.command.ConsoleCommandSender;
import com.kibi.config.Config;
import com.kibi.net.Responses;

public class GetCommand extends ConsoleCommandSender {
    @Override
    public String getName() {
        return "get";
    }

    @Override
    public String getUsage() {
        return "get <str:key>";
    }

    @Override
    public String getDescription() {
        return "Get value of a record from key";
    }

    @Override
    public void execute(String[] args) {
        if (!(args.length >= 1)) {
            getSender().info(getUsage());

            return;
        }

        Config database = Kibi.getServer().getDataBase();
        String key = args[0];

        if (!database.exists(key)) {
            getSender().info(Responses.GET_NULL);

            return;
        }

        getSender().info(database.getString(key));
    }
}
