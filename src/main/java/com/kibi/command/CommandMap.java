package com.kibi.command;

import com.kibi.Kibi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandMap extends Thread {
    private static Map<String, ConsoleCommandSender> map = new HashMap<>();
    private int stop_code = 0;

    public void run() {
        while (stop_code == 0) {
            System.out.println("<kibi:command>");

            String entry = (new Scanner(System.in)).nextLine();
            String commandPrefix = entry.split(" ")[0];

            if (!exits(commandPrefix)) {
                Kibi.getLogger().info("Unknown command use (help) to get support list");
            } else {
                String[] args = entry.replace(commandPrefix + " ", "").split(" ");
                map.get(commandPrefix).execute(args);
            }
        }
    }

    public boolean exits(String command) {
        return map.containsKey(command);
    }

    public Map<String, ConsoleCommandSender> getCommands() {
        return map;
    }

    public void register(String prefix, ConsoleCommandSender command) {
        if (exits(prefix))
            Kibi.getLogger().warning("The command exists and cannot be overwritten");
        else
            map.put(prefix, command);
    }

    public void destroy() {
        stop_code = 1;
    }
}
