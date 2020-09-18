package com.kibi.manager;

import com.kibi.command.CommandMap;
import com.kibi.command.defaults.*;
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
        commandMap.register("insert", new InsertCommand());
        commandMap.register("get", new GetCommand());
        commandMap.register("set", new SetCommand());
        commandMap.register("remove", new RemoveCommand());
        commandMap.register("clear", new ClearCommand());

        commandMap.start();
    }

    public Config getDataBase() {
        return database;
    }

    public Config getProperties() {
        return properties;
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

    public CommandMap getCommandMap() {
        return commandMap;
    }
}
