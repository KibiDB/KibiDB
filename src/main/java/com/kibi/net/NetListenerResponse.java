package com.kibi.net;

import com.kibi.Kibi;
import com.kibi.config.Config;

import java.io.DataOutputStream;
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
            DataOutputStream writer = new DataOutputStream(client.getOutputStream());

            //check password
            if (!request[0].equals(Kibi.getServer().getPassword())) {
                writer.writeUTF(Responses.INCORRECT_PASSWORD);
                client.close();
                writer.close();

                return;
            }

            String petition = request[1].toUpperCase();
            Config database = Kibi.getServer().getDataBase();
            String key = request.length >= 3 ? request[2] : null;
            String value = request.length >= 4 ? request[3] : null;

            switch (petition) {
                case Requests.INSERT:
                    if (key == null || value == null) {
                        writer.writeUTF(Responses.INVALID_QUERY);
                    } else {
                        writer.writeUTF(Responses.INSERT_OK);
                        database.set(key, value);
                    }
                    break;
                case Requests.GET:
                    if (key == null) {
                        writer.writeUTF(Responses.INVALID_QUERY);
                    } else {

                        if (!database.exists(key)) {
                            writer.writeUTF(Responses.GET_NULL);
                        } else {
                            writer.writeUTF(database.getString(key));
                        }

                    }
                    break;
                case Requests.SET:
                    if (key == null || value == null) {
                        writer.writeUTF(Responses.INVALID_QUERY);
                    } else {

                        if (!database.exists(key)) {
                            writer.writeUTF(Responses.SET_NULL);
                        } else {
                            writer.writeUTF(Responses.SET_OK);
                            database.set(key, value);
                        }

                    }
                    break;
                case Requests.REMOVE:
                    if (key == null) {
                        writer.writeUTF(Responses.INVALID_QUERY);
                    } else {

                        if (!database.exists(key)) {
                            writer.writeUTF(Responses.REMOVE_NULL);
                        } else {
                            writer.writeUTF(Responses.REMOVE_OK);
                            database.remove(key);
                        }

                    }
                    break;
                case Requests.CLEAR:
                    writer.writeUTF(Responses.CLEAR_OK);

                    database.getAll().forEach((data_key, data_value) -> {
                        database.remove(data_key);
                    });
                    break;
            }

            client.close();
            writer.close();
        } catch (IOException e) {
            Kibi.getLogger().error(e.toString());
        }
    }
}
