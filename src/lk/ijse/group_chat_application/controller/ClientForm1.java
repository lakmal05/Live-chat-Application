package lk.ijse.group_chat_application.controller;

import com.jfoenix.controls.JFXTextField;
import com.sun.security.ntlm.Client;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientForm1 {
    public TextArea txtTextArea;
    public JFXTextField txtTextField;

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;


public static void main(String[] args) throws IOException {
    Scanner sc= new Scanner(System.in);
    System.out.println("enter name");
String username = sc.nextLine();
Socket socket = new Socket("localhost",1234);
ClientForm1 clientForm1= new ClientForm1(socket,username);
clientForm1.listenForMessage();
clientForm1.sendMessage();

}


    public ClientForm1(Socket socket, String username){
        try{
            this.socket =socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
        }catch (IOException e){
            closeEverything(socket,bufferedWriter,bufferedReader);


        }

    }

    public void sendMessage(){
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner sc= new Scanner(System.in);
            while(socket.isConnected()){
                String messageToSend = sc.nextLine();
                bufferedWriter.write(username +messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch (IOException e){
           closeEverything(socket,bufferedWriter,bufferedReader);
        }
    }

public void listenForMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
           String msgFormGroupChat;
           while(socket.isConnected()){
               try{
                   msgFormGroupChat =bufferedReader.readLine();
                   System.out.println(msgFormGroupChat);
               }catch (IOException e){
                   closeEverything(socket,bufferedWriter,bufferedReader);
               }
           }
            }
        }).start();
}

public void closeEverything(Socket socket ,BufferedWriter bufferedWriter ,BufferedReader bufferedReader){
    try{
        if(bufferedReader !=null){
            bufferedReader.close();
        }if(bufferedWriter !=null){
            bufferedWriter.close();
        }if(socket !=null){
            socket.close();
        }
    }catch (IOException e) {

    }}

        public void sendOnAction (ActionEvent actionEvent){
        }
    }
