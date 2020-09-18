package com.kibi.net;

import com.kibi.Kibi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Net extends Thread {
    private int stop_code = 0;
    private ServerSocket socket;

    public void run() {
        try {
            socket = new ServerSocket(Kibi.getServer().getPort());

            while (stop_code == 0) {
                Socket client = socket.accept();

                BufferedReader request = new BufferedReader(new InputStreamReader(client.getInputStream()));

                String listener = request.readLine();

                Thread response = new NetListenerResponse(client, listener);
                response.start();
            }

        } catch (IOException e) {
            Kibi.getLogger().error(e.toString());
        }
    }

    public void destroy() {
        try {
            socket.close();
        } catch (IOException e) {
            Kibi.getLogger().error(e.toString());
        }

        stop_code = 1;
    }
}
