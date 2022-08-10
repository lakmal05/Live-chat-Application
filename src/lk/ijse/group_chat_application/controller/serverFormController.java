package lk.ijse.group_chat_application.controller;

import com.sun.security.ntlm.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class serverFormController {


     int PORT;
     ServerSocket serverSocket=null;
     Socket client=null;
     ExecutorService pool =null;
     int clientcount=0;



     public static void main (String args[]) throws IOException {
       serverFormController serverobj=new serverFormController(8000);
       serverobj.startServer();

     }

     serverFormController(int prot){
         this.PORT=prot;
         pool= Executors.newFixedThreadPool(5);

     }

     public void startServer() throws IOException {
         serverSocket= new ServerSocket(8000) ;
         System.out.println("server boot");

         while(true){
             client= serverSocket.accept();
             clientcount++;
             ServerThread runnable = new ServerThread(client, clientcount, this);
             pool.execute(runnable);
         }

     }






}


 class ServerThread implements  Runnable{
    serverFormController server=null;
    Socket client;
    BufferedReader cin;
    PrintStream cout;
    Scanner sc=new Scanner(System.in);
    int id;
    String s;


    ServerThread(Socket client,int count,serverFormController server) throws IOException {
        this.client=client;
        this.server=server;
        this.id=count;

        cin=new BufferedReader(new InputStreamReader(client .getInputStream()));
        cout=new PrintStream(client.getOutputStream());

    }

    @Override
    public void run(){
        int x=1;
        try{
            while (true){
                s=cin.readLine();
                if(s.equalsIgnoreCase("bye")){
                    cout.println("BYE");
                    x=0;
                    System.out.println("END");
                    break;
                }
                cout.println(s);



            }

            cin.close();
            client.close();
            cout.close();
            if(x==0){
                System.out.println("end");
                System.exit(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error"+e);
        }


    }


}
