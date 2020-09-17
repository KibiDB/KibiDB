package com.kibi.manager;

import com.kibi.command.CommandMap;
import com.kibi.command.defaults.HelpCommand;
import com.kibi.command.defaults.StopCommand;
import com.kibi.config.Config;

public class ServerManager {

    public Config properties;
    public Config database;

    private CommandMap commandMap;

    public void initialize() {
        commandMap = new CommandMap();
        ConfigManager.initialize(this);


        commandMap.register("help", new HelpCommand());
        commandMap.register("stop", new StopCommand());

        commandMap.start();
    }

    public String getIp() {
        return properties.getString("ip");
    }

    public int getPort() {
        return properties.getInt("port");
    }

    public int getMaxConnections() {
        return properties.getInt("max_connections");
    }

    public String getPassword() {
        return properties.getString("password");
    }

    public Config getDatabase() {
        return database;
    }

    public CommandMap getCommandMap() {
        return commandMap;
    }
}
