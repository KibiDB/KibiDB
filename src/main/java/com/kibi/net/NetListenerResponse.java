package com.kibi.net;

import com.kibi.Kibi;
import com.kibi.config.Config;
import com.kibi.manager.ServerManager;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetListenerResponse extends Thread {

    private final Socket client;
    private final String[] request;
    private DataOutputStream writer;
    private final Net net;

    public NetListenerResponse(Net net, Socket client, String request) {
        this.client = client;
        this.request = request.split(" ");
        this.net = net;
    }

    public void run() {
        //format of request [password] [request_type] [key] [value:optional]
        try {
            writer = new DataOutputStream(client.getOutputStream());
            ServerManager server = Kibi.getServer();

            //check password
            if (server.getAuthentication() && !request[0].equals(server.getPassword())) {
                writer.writeUTF(Responses.INCORRECT_PASSWORD);
                this.destroy();

                return;
            }

            if (server.hasWhitelist() && !server.getWhitelist().exists(client.getLocalAddress().getHostAddress())) {
                writer.writeUTF(Responses.IN_WHITELIST);
                this.destroy();

                return;
            }

            String petition = request[1].toUpperCase();
            Config database = Kibi.getServer().getDataBase();

            String key = request.length >= 3 ? request[2] : null;
            String value = request.length >= 4 ? request[3] : null;

            //responses
            switch (petition) {
                case Requests.INSERT:
                    if (key == null || value == null) {
                        writer.writeUTF(Responses.INVALID_QUERY);

                        break;
                    }

                    writer.writeUTF(Responses.INSERT_OK);
                    database.set(key, value);
                    break;
                case Requests.GET:
                    if (key == null) {
                        writer.writeUTF(Responses.INVALID_QUERY);

                        break;
                    }

                    if (!database.exists(key)) {
                        writer.writeUTF(Responses.GET_NULL);

                        break;
                    }

                    writer.writeUTF(database.getString(key));
                    break;
                case Requests.SET:
                    if (key == null || value == null) {
                        writer.writeUTF(Responses.INVALID_QUERY);

                        break;
                    }

                    if (!database.exists(key)) {
                        writer.writeUTF(Responses.SET_NULL);

                        break;
                    }

                    writer.writeUTF(Responses.SET_OK);
                    database.set(key, value);
                    break;
                case Requests.REMOVE:
                    if (key == null) {
                        writer.writeUTF(Responses.INVALID_QUERY);

                        break;
                    }

                    if (!database.exists(key)) {
                        writer.writeUTF(Responses.REMOVE_NULL);

                        break;
                    }

                    writer.writeUTF(Responses.REMOVE_OK);
                    database.remove(key);
                    break;
                case Requests.CLEAR:
                    writer.writeUTF(Responses.CLEAR_OK);

                    database.getAll().forEach((data_key, data_value) -> {
                        database.remove(data_key);
                    });
                    break;
                default:
                    writer.writeUTF(Responses.INVALID_QUERY);
                    break;
            }

           this.destroy();
        } catch (IOException e) {
            net.channels.remove(client);
            Kibi.getLogger().warning(e.toString());
        }
    }

    public void destroy() {
        try {
            net.channels.remove(client);
            writer.close();
            client.close();
        } catch (IOException e) {
            Kibi.getLogger().warning(e.toString());
        }
    }
}
