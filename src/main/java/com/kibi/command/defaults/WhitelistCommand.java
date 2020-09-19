package com.kibi.command.defaults;

import com.kibi.Kibi;
import com.kibi.command.ConsoleCommandSender;
import com.kibi.config.Config;

public class WhitelistCommand extends ConsoleCommandSender {
    @Override
    public String getName() {
        return "whitelist";
    }

    @Override
    public String getUsage() {
        return "whitelist <str:arg> <str:value>";
    }

    @Override
    public String getDescription() {
        return "Add trusted ip addresses";
    }

    @Override
    public void execute(String[] args) {
        if (!(args.length >= 2)) {
            getSender().info(getUsage());

            return;
        }

        Config whitelist = Kibi.getServer().getWhitelist();
        String ip = args[1];

        switch (args[0]) {
            case "add":
                if (!whitelist.exists(ip)) {
                    whitelist.set(ip, true);
                    getSender().info("The ip (" + ip + ") was added to the whitelist");

                    return;
                }

                getSender().info("The ip already exists in the whitelist");
                break;
            case "remove":
                if (whitelist.exists(ip)) {
                    whitelist.remove(ip);
                    getSender().info("The ip (" + ip + ") was removed to the whitelist");

                    return;
                }

                getSender().info("The ip not exists in the whitelist");
                break;
            default:
                getSender().info("Invalid arguments, usage add or remove");
                break;
        }
    }
}
