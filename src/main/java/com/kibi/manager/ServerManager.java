package com.kibi.manager;

import com.kibi.command.CommandMap;
import com.kibi.command.defaults.*;
import com.kibi.config.Config;
import com.kibi.net.Net;

public class ServerManager {

    public Config properties;
    public Config database;

    private CommandMap commandMap;
    private Net net;

    public void initialize() {
        commandMap = new CommandMap();
        net = new Net();
        ConfigManager.initialize(this);


        commandMap.register("help", new HelpCommand());
        commandMap.register("stop", new StopCommand());
        commandMap.register("insert", new InsertCommand());
        commandMap.register("get", new GetCommand());
        commandMap.register("set", new SetCommand());
        commandMap.register("remove", new RemoveCommand());
        commandMap.register("clear", new ClearCommand());
        commandMap.register("list", new ListCommand());

        net.start();
        commandMap.start();
    }

    public Config getDataBase() {
        return database;
    }

    public Config getProperties() {
        return properties;
    }

    public Net getNet() {
        return net;
    }

    public int getPropertyInt(String variable) {
        return this.getPropertyInt(variable, null);
    }

    public int getPropertyInt(String variable, Integer defaultValue) {
        return this.properties.exists(variable) ? (!this.properties.get(variable).equals("") ? Integer.parseInt(String.valueOf(this.properties.get(variable))) : defaultValue) : defaultValue;
    }

    public String getPropertyString(String variable) {
        return this.getPropertyString(variable, null);
    }

    public String getPropertyString(String variable, String defaultValue) {
        return this.properties.exists(variable) ? (String) this.properties.get(variable) : defaultValue;
    }

    public int getPort() {
        return this.getPropertyInt("port", 3306);
    }

    public int getMaxConnections() {
        return this.getPropertyInt("max_connections", 0);
    }

    public String getPassword() {
        return this.getPropertyString("password", "");
    }

    public CommandMap getCommandMap() {
        return commandMap;
    }
}
