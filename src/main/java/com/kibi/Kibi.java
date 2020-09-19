package com.kibi;

import com.kibi.manager.ServerManager;

public class Kibi {
    private final static String DATA_PATH = System.getProperty("user.dir") + "/";
    private final static String BASE_DATA_PATH = System.getProperty("user.dir") + "/basedata/";

    private final static String KIBI_VERSION = "1.0";

    private static ServerManager server;
    private static Logger logger;

    public static void main(String[] args) {
        server = new ServerManager();
        logger = new Logger();

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

    public static Logger getLogger() {
        return logger;
    }
}
