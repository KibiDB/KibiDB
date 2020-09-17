package com.kibi;

import com.kibi.manager.ServerManager;

public class Kibi {
    public final static String DATA_PATH = System.getProperty("user.dir") + "/";
    public final static String BASE_DATA_PATH = System.getProperty("user.dir") + "/basedata/";

    public final static String KIBI_VERSION = "1.0";

    private static ServerManager server;

    public static void main(String[] args) {
        server = new ServerManager();

        server.initialize();
    }

    public static String getDataPath() {
        return DATA_PATH;
    }

    public static String getBaseDataPath() {
        return BASE_DATA_PATH;
    }

    public static String getKibiVersion() {
        return KIBI_VERSION;
    }

    public static ServerManager getServer() {
        return server;
    }
}
