package com.kibi.net;

import com.kibi.Kibi;
import com.kibi.config.Config;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class NetListenerResponse extends Thread {

    private final Socket client;
    private final String[] request;

    public NetListenerResponse(Socket client, String request) {
        this.client = client;
        this.request = request.split(" ");
    }

    public void run() {
        //format of request [password] [request_type] [key] [value:optional]
        try {
            PrintWriter writer = new PrintWriter(client.getOutputStream());

            //check password
            if (!request[0].equals(Kibi.getServer().getPassword())) {
                writer.println(Responses.INCORRECT_PASSWORD);
                client.close();
                writer.close();

                return;
            }

            String petition = request[1].toUpperCase();
            Config database = Kibi.getServer().getDataBase();
            String key = request.length >= 3 ? request[3] : null;
            String value = request.length >= 4 ? request[3] : null;

            switch (petition) {
                case Requests.INSERT:
                    if (key == null || value == null) {
                        writer.println(Responses.INVALID_QUERY);
                    } else {
                        database.set(key, value);
                        writer.println(Responses.INSERT_OK);
                    }
                    break;
                case Requests.GET:
                    if (key == null) {
                        writer.println(Responses.INVALID_QUERY);
                    } else {

                        if (!database.exists(key)) {
                            writer.println(Responses.GET_NULL);
                        } else {
                            writer.println(database.getString(key));
                        }

                    }
                    break;
                case Requests.SET:
                    if (key == null || value == null) {
                        writer.println(Responses.INVALID_QUERY);
                    } else {

                        if (!database.exists(key)) {
                            writer.println(Responses.SET_NULL);
                        } else {
                            writer.println(Responses.SET_OK);
                        }

                    }
                    break;
                case Requests.REMOVE:
                    if (key == null) {
                        writer.println(Responses.INVALID_QUERY);
                    } else {

                        if (!database.exists(key)) {
                            writer.println(Responses.REMOVE_NULL);
                        } else {
                            writer.println(Responses.REMOVE_OK);
                        }

                    }
                    break;
                case Requests.CLEAR:
                    database.getAll().forEach((data_key, data_value) -> {
                        database.remove(data_key);
                    });

                    writer.println(Responses.CLEAR_OK);
                    break;
            }

            client.close();
            writer.close();
        } catch (IOException e) {
            Kibi.getLogger().error(e.toString());
        }
    }
}
