package com.kibi.manager;

import com.kibi.Kibi;
import com.kibi.config.Config;
import com.kibi.config.ConfigSection;

import java.io.File;
import java.util.Base64;
import java.util.UUID;

public class ConfigManager {

    public static void initialize(ServerManager manager) {

        File baseDataDirectory = new File(Kibi.getBaseDataPath());

        if (!baseDataDirectory.exists()) {
            baseDataDirectory.mkdirs();

            Config basedata = new Config(Kibi.getBaseDataPath() + "basedata.dat", Config.YAML);
            basedata.save();
        }

        if (!new File(Kibi.getDataPath() + "kibi.properties").exists()) {
            Config properties = new Config(Kibi.getDataPath() + "kibi.properties", Config.PROPERTIES, new ConfigSection() {{
                put("ip", "0.0.0.0");
                put("port", 3306);
                put("max_connectios", "0"); //0 = unlimited, if you change the number it will limit the connections
                put("password", Base64.getEncoder().encodeToString(UUID.randomUUID().toString().replace("-", "").getBytes()).substring(3, 13));
            }});

            properties.save();
        }

        manager.properties = new Config(Kibi.getDataPath() + "kibi.properties", Config.PROPERTIES);
        manager.database = new Config(Kibi.getBaseDataPath() + "basedata.dat", Config.YAML);
    }
}
