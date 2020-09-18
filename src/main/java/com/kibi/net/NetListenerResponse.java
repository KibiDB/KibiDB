package com.kibi.net;

import com.kibi.Kibi;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class NetListenerResponse extends Thread {

    private Socket client;
    private String[] request;

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
                writer.close();
                client.close();

                return;
            }

            String petition = request[1].toUpperCase();

            switch (petition) {
                case Requests.INSERT:
                    break;
                case Requests.GET:
                    break;
                case Requests.SET:
                    break;
                case Requests.REMOVE:
                    break;
                case Requests.CLEAR:
                    break;
            }

            writer.close();
            client.close();
        } catch (IOException e) {
            Kibi.getLogger().error(e.toString());
        }
    }
}
