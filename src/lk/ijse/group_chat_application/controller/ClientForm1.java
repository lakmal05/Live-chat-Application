package lk.ijse.group_chat_application.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientForm1 {
    public TextArea txtTextArea;
    public JFXTextField txtTextField;

public static void main(String args[]) throws IOException {
    Socket so=new Socket("localhost",8000);
    ServerSocket ss =new ServerSocket(2000);
    Socket sk =ss .accept();
    BufferedReader cin = new BufferedReader(new InputStreamReader(sk.getInputStream()));
    PrintStream cout= new PrintStream(sk.getOutputStream());
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in)) ;
    String s;
    Scanner sc = new Scanner(System.in);
    while(true){


            s=cin.readLine();
            if(s.equalsIgnoreCase("bye")){
                cout.println("BYE");

                System.out.println("END");
                break;
            }
            cout.println(s);



        }
    ss.close();
    sk.close();
    cin.close();
    cout.close();
    stdin.close();

    }




    public void sendOnAction(ActionEvent actionEvent) {
    }
}
