package com.kibi.manager;

import com.kibi.Kibi;
import com.kibi.Logger;
import com.kibi.command.CommandMap;
import com.kibi.command.defaults.*;
import com.kibi.config.Config;
import com.kibi.net.Net;

import java.util.concurrent.TimeUnit;

public class ServerManager {

    public Config properties;
    public Config database;
    public Config whitelist;

    private CommandMap commandMap;
    private Net net;

    public void initialize() {
        Logger logger = Kibi.getLogger();

        logger.info("Loading Kibi v" + Kibi.getKibiVersion() + "...");
        final long startTime = System.currentTimeMillis();

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
        commandMap.register("whitelist", new WhitelistCommand());

        net.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            logger.error(e.toString());
        }

        commandMap.start();

        final long endTime = System.currentTimeMillis();
        logger.info("Kibi was loaded in (" + TimeUnit.MILLISECONDS.toSeconds(endTime - startTime) + ") seconds...");
    }

    public Config getDataBase() {
        return database;
    }

    public Config getProperties() {
        return properties;
    }

    public Config getWhitelist() {
        return whitelist;
    }

    public Net getNet() {
        return net;
    }

    private int getPropertyInt(String variable, Integer defaultValue) {
        return this.properties.exists(variable) ? (!this.properties.get(variable).equals("") ? Integer.parseInt(String.valueOf(this.properties.get(variable))) : defaultValue) : defaultValue;
    }

    private String getPropertyString(String variable, String defaultValue) {
        return this.properties.exists(variable) ? (String) this.properties.get(variable) : defaultValue;
    }

    private boolean getPropertyBoolean(String variable, Object defaultValue) {
        Object value = this.properties.exists(variable) ? this.properties.get(variable) : defaultValue;

        if (value instanceof Boolean) {
            return (Boolean) value;
        }

        switch (String.valueOf(value)) {
            case "on":
            case "true":
            case "1":
            case "yes":

                return true;
        }

        return false;
    }

    public int getPort() {
        return this.getPropertyInt("port", 3306);
    }

    public boolean hasWhitelist() {
        return this.getPropertyBoolean("whitelist", false);
    }

    public boolean getAuthentication() {
        return this.getPropertyBoolean("authentication", true);
    }

    public String getPassword() {
        return this.getPropertyString("password", "");
    }

    public CommandMap getCommandMap() {
        return commandMap;
    }
}
