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
    ServerSocket ss =new ServerSocket(8000);
    Socket sk =ss .accept();
    BufferedReader cin = new BufferedReader(new InputStreamReader(sk.getInputStream()));
    PrintStream cout= new PrintStream(sk.getOutputStream());
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in)) ;
    String s;
    Scanner sc = new Scanner(System.in);
    while(true){


    }


}

    public void sendOnAction(ActionEvent actionEvent) {
    }
}
