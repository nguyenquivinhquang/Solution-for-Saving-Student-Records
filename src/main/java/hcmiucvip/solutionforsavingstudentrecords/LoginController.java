package hcmiucvip.solutionforsavingstudentrecords;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.util.Pair;

import static hcmiucvip.solutionforsavingstudentrecords.core.Auth.UserAuthenticator.auth;

public class LoginController implements Initializable {
    public Button loginButton;
    public Button signupButton;
//    public PasswordField passwordField;
    public TextField accountField, passwordField;
    public AnchorPane rootPane;
    public Label loginNoti, passwordIncorrect;

    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    private void loginFail() {

    }
    @FXML
    private void logIn(ActionEvent event) throws IOException {
        Pair<String, String> loadInfor = auth(accountField.getText().toLowerCase(),passwordField.getText());

        if (loadInfor.getKey() == "none") {
            // Login fail
            loginNoti.setText("*Authentication failed!");
            passwordIncorrect.setText("*Username/Password does not match!");
            passwordField.clear();
            return;
        }
        LoadScene loadScene = new LoadScene();
        System.out.println(loadInfor.getValue());
        if (loadInfor.getValue().equals("Student")){
            loadScene.loadStudent(loadInfor.getKey());
            this.stage.close();
        }
//        loadAdmin(event);
    }
    private void loadAdmin(ActionEvent event) throws IOException{
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        Parent adminParent = FXMLLoader.load(getClass().getResource("AdminStudentAdd.fxml"));
        Scene adminScene = new Scene(adminParent);
        Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        adminStage.setScene(adminScene);
        adminStage.setTitle("Admin Panel");
        adminStage.setX(50);
        adminStage.setY(10);
        adminStage.show();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    private void signIn2Admin(ActionEvent event) throws IOException {

    }
    private void loadStudent(String studentId) {

    }


}