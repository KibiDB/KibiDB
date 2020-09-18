package com.kibi.command.defaults;

import com.kibi.Kibi;
import com.kibi.command.ConsoleCommandSender;
import com.kibi.config.Config;
import com.kibi.net.Responses;

public class RemoveCommand extends ConsoleCommandSender {
    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public String getUsage() {
        return "remove <str:key>";
    }

    @Override
    public String getDescription() {
        return "Delete record from database";
    }

    @Override
    public void execute(String[] args) {
        if (!(args.length >= 1)) {
            getSender().warning(getUsage());

            return;
        }

        Config database = Kibi.getServer().getDataBase();
        String key = args[0];

        if (!database.exists(key)) {
            getSender().info(Responses.REMOVE_NULL);

            return;
        }

        database.remove(key);

        getSender().info(Responses.REMOVE_OK);
    }
}
