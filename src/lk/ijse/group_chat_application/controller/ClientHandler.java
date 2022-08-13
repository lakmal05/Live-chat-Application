package lk.ijse.group_chat_application.controller;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {

    public static ArrayList<ClientHandler>clientHandlers;
   // public static ArrayList<ClientHandler>clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    private PrintWriter printWriter;

    public ClientHandler(Socket socket,ArrayList<ClientHandler>clientHandlers){
        try{
           this.socket = socket;
           this.clientHandlers=clientHandlers;
           this.bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //  this.bufferedWriter= new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
          this.printWriter=new PrintWriter(socket.getOutputStream(),true);
          /*-----------------------out---------------------*/
     /*     this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          this.clientUsername =bufferedReader.readLine();
          clientHandlers.add(this);
          broadcastMassage(clientUsername+"joind");*/

        }catch (IOException e){
            e.printStackTrace();
           // closeEverything(socket,bufferedReader,bufferedWriter);
        }
    }



    public void run() {

        try{
            String massage;
            while ((massage = bufferedReader.readLine()) != null) {
                if(massage.equalsIgnoreCase("exit")) {
                    break;
                }

                for(ClientHandler c : clientHandlers) {
                    c.printWriter.println(massage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            try {
                bufferedReader.close();
                printWriter.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



   /*---------------------OUT--------------------*/

/*
 String massageFormClient;

 while(socket.isConnected()){
     try{
         massageFormClient=bufferedReader.readLine();
         broadcastMassage(massageFormClient);
     }catch(IOException e){
         closeEverything(socket,bufferedReader,bufferedWriter);
         break;
     }
 }*/

    }

  /*  public void broadcastMassage(String massageToSend){
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
    }*/
/*
public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMassage("Server"+clientUsername+"left");

}
public void closeEverything(Socket socket, BufferedReader bufferedReader , BufferedWriter bufferedWriter){
        removeClientHandler();
        try{
            if(bufferedReader !=null){
                bufferedReader.close();
            }if(bufferedWriter !=null){
                bufferedWriter.close();
            }if(socket !=null){
                socket.close();
            }
        }catch (IOException e){

        }
}*/
}
