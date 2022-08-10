package lk.ijse.group_chat_application.controller;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler>clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public ClientHandler(Socket socket){
        try{
           this.socket = socket;
          this.bufferedWriter= new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
          this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          this.clientUsername =bufferedReader.readLine();
          clientHandlers.add(this);
          broadcastMassage(clientUsername+"joind");
        }catch (IOException e){
            closeEverythig(socket,bufferedReader,bufferedWriter);
        }
    }


    @Override
    public void run() {
 String massageFormClient;

 while(socket.isConnected()){
     try{
         massageFormClient=bufferedReader.readLine();
         broadcastMassage(messageFormClient);
     }catch(IOException e){
         cloaseEverything(socket,bufferedReader,bufferedWriter);
         break;
     }
 }

    }

    public void broadcastMassage(String massageToSend){
        for(ClientHandler clientHandler : clientHandlers){
            try{
                if(!clientHandler.clientUsername.equals(clientUsername)){
                    clientHandler.bufferedWriter.write(massageToSend);
                   clientHandler.bufferedWriter.newLine();
                 clientHandler.bufferedWriter.flush();
                }
            }catch (IOException e){
                closeEverything(socket,bufferedReader,bufferedWriter);
            }
        }
    }

public void removeClientHandler(){
        
}

}
