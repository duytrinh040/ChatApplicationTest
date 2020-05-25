package com.muc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ChatClient {
    private final String serverName;
    private final int serverPort;
    private Socket socket;
    private OutputStream serverOut;
    private InputStream serverIn;

    public ChatClient(String serverName, int serverPort){
        this.serverName = serverName;
        this.serverPort = serverPort;
    }
    public static void main(String[] args) throws IOException {
        ChatClient client = new ChatClient("localhost", 8818);
        if(!client.connect()){
            System.err.println("Connect Failed\n");
        }
        else{
            System.out.printf("Connect Successful\n");
            client.login("admin", "admin");
        }

    }

    private void login(String username, String password) throws IOException {
        String cmd = "login " + username + " " + password + "\n";
        serverOut.write(cmd.getBytes());
    }

    private boolean connect() {
        try {
            this.socket = new Socket(serverName, serverPort);
            System.out.printf("Client port is " + socket.getLocalPort() + " ");
            this.serverOut  = socket.getOutputStream();
            this.serverIn =  socket.getInputStream();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
