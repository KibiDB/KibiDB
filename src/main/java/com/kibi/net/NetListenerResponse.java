package com.kibi.net;

import java.net.Socket;

public class NetListenerResponse extends Thread {

    private Socket client;
    private String request;

    public NetListenerResponse(Socket client, String request) {
        this.client = client;
        this.request = request;
    }

    public void run() {
    }
}
