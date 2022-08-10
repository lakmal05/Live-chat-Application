package lk.ijse.group_chat_application.controller;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {


    private final ServerSocket serverSocket;


    public  server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        server server = new server(serverSocket);
        server.startServer();
    }

    public void startServer() {

        try {
            while (!serverSocket.isClosed()) {
                Socket socket = null;

                socket = serverSocket.accept();

                System.out.println("client has connectd");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void closeServerSocket() {

        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}


