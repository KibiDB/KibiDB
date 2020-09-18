package com.kibi.net;

import com.kibi.Kibi;
import com.kibi.Logger;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Net extends Thread {
    private int stop_code = 0;
    private ServerSocket socket;

    public void run() {
        Logger logger = Kibi.getLogger();
        int port = Kibi.getServer().getPort();

        while (stop_code == 0) {
            try {
                if (socket == null) {
                    socket = new ServerSocket(port);
                    logger.info("Net open in 0.0.0.0 - " + port);
                }

                Socket client = socket.accept();
                logger.info("Connection accepted " + client.getLocalAddress());

                DataInputStream in = new DataInputStream(client.getInputStream());
                String listener = in.readUTF();
                System.out.println(listener);

                Thread response = new NetListenerResponse(client, listener);
                response.start();

            } catch (IOException e) {
                logger.error(e.toString());
            }
        }
    }

    public void destroy() {
        try {
            socket.close();
        } catch (IOException e) {
            //ignore
        }

        stop_code = 1;
    }
}
