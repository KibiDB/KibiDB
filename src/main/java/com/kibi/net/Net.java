package com.kibi.net;

import com.kibi.Kibi;
import com.kibi.Logger;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

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

                Thread response = new NetListenerResponse(client, listener);
                response.start();

            } catch (SocketException e) {
                logger.warning(e.toString());

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException exception) {
                    logger.warning(exception.toString());
                }

                System.exit(0);
            } catch (IOException e) {
                logger.warning(e.toString());
            }
        }
    }

    public void destroy() {
        try {
            socket.close();
        } catch (IOException e) {
            Kibi.getLogger().warning(e.toString());
        }

        stop_code = 1;
    }
}
