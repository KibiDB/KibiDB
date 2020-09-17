package com.kibi.manager;

import com.kibi.config.Config;

public class ServerManager {

    public Config properties;
    public Config database;

    public void initialize() {
        ConfigManager.initialize(this);
    }
}
