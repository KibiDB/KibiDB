package com.kibi.command.defaults;

import com.kibi.Kibi;
import com.kibi.command.ConsoleCommandSender;
import com.kibi.manager.ConfigManager;

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
        Kibi.getLogger().info("Stopping Kibi Services...");

        ConfigManager.unload();

        Kibi.getServer().getNet().destroy();

        Kibi.getServer().getCommandMap().destroy();
    }
}
