package lk.ijse.group_chat_application.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFrom {


    public JFXTextField txtUserName;

    public static String userName;




    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        userName =txtUserName.getText();
        Stage stage = (Stage) txtUserName.getScene().getWindow();
        stage.close();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("/lk/ijse/group_chat_application/view/clientForm_1.fxml"))));
        stage1.setTitle("Online Chat ");
        stage1.setResizable(false);
        stage1.centerOnScreen();
        stage1.show();


    }
}
