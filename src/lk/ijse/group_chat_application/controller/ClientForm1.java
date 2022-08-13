package lk.ijse.group_chat_application.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientForm1 extends Thread {

    public JFXTextField txtTextField;
    public VBox vBox;
    public ImageView imgSendImages;
    public Label lblUser;

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;
    private PrintWriter printWriter;













    public ClientForm1(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.username = username;
        } catch (IOException e) {
            closeEverything(socket, bufferedWriter, bufferedReader);


        }

    }

    public void initialize() throws IOException {

      String userName= LoginFrom.userName;
        lblUser.setText(userName);

 try{
     socket = new Socket("localhost",1234);
     bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
     printWriter = new PrintWriter(socket.getOutputStream(),true);
     this.start();

 }catch (IOException e){
     e.printStackTrace();
 }
/*----------Out*-----------------------------*/
        Scanner sc = new Scanner(System.in);
        System.out.println("enter name");
        String username = sc.nextLine();
        Socket socket = new Socket("localhost", 1234);
       ClientForm1 clientForm1 = new ClientForm1(socket, username);
        clientForm1.listenForMessage();
       clientForm1.sendMessage();

    }

    public void sendMessage() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner sc = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = sc.nextLine();
                bufferedWriter.write(username + messageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedWriter, bufferedReader);
        }
    }

    public void listenForMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFormGroupChat;
                while (socket.isConnected()) {
                    try {
                        msgFormGroupChat = bufferedReader.readLine();
                        System.out.println(msgFormGroupChat);
                    } catch (IOException e) {
                        closeEverything(socket, bufferedWriter, bufferedReader);
                    }
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {

        }
    }

    public void sendOnAction(ActionEvent actionEvent) {
        String massage = txtTextField.getText();
        printWriter.println(lblUser.getText() + ": " + massage);
        txtTextField.clear();
        printWriter.flush();
        if(massage.equalsIgnoreCase("exit")) {
            Stage stage = (Stage) txtTextField.getScene().getWindow();
            stage.close();
        }

    }


 public FileChooser chooser;
    public File path;
    public void sendImgClicked(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        chooser = new FileChooser();
        chooser.setTitle("Open Image");
        this.path = chooser.showOpenDialog(stage);
        printWriter.println(lblUser.getText() + " " + "img" + path.getPath());
        printWriter.flush();



    }
}
