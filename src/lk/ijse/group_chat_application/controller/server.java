package lk.ijse.group_chat_application.controller;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class server {


    private final ServerSocket serverSocket;
    private  static ArrayList<ClientHandler>clientHandlers=new ArrayList<>();



    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        Socket accept;

        while(true){
            System.out.println("Weiting for client");
            accept =serverSocket.accept();
            System.out.println("new member coNECTD");
            ClientHandler clientHandler=new ClientHandler(accept,clientHandlers);
            clientHandlers.add(clientHandler);
            //clientHandler.
            Thread thread = new Thread(clientHandler);
            thread.start();
        }

      //  server server = new server(serverSocket);
      //  server.startServer();


    }


/*------------------------------OUT-------------------------------*/
    public  server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {

        try {
            while (!serverSocket.isClosed()) {
                Socket socket = null;

                socket = serverSocket.accept();

                System.out.println("client has connectd");
              //  ClientHandler clientHandler = new ClientHandler(socket);
             //   Thread thread = new Thread(clientHandler);
              //  thread.start();

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


