package hcmiucvip.solutionforsavingstudentrecords;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ResetPassword {
    public PasswordField newPassField;
    public PasswordField oldPassField;
    public PasswordField confirmPassField;
    public AnchorPane resetPassAnchorPane;

    public ResetPassword() {

    }
    public Stage getParentStage() {
        return (Stage) resetPassAnchorPane.getScene().getWindow();
    }
    public void setClickButton() {
        if (inputOK() == true) {
            // TO do: update password to DB
            getParentStage().close();
        }
    }
    public boolean matchOldPass(String pass) {
        // To do: Get password to DB
        String curPass = "123";
        return curPass.equals(pass);

    }
    public boolean inputOK() {
        if (newPassField.getText().isBlank()) {
            showWarning("New password must not empty");
            return false;
        }
        if (oldPassField.getText().isBlank()) {
            showWarning("Old password must not empty");
            return false;
        }
        if (confirmPassField.getText().isBlank()) {
            showWarning("Confirm new password must not empty");
            return false;
        }
        if (matchOldPass(oldPassField.getText())==false) {
            showWarning("Old  password is not correct");
            return false;
        }
        if (newPassField.getText().equals(confirmPassField.getText()) == false) {
            showWarning("New password and confirm new password does not match ");
            return false;
        }
        return true;
    }
    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        // alert.setHeaderText("Results:");
        alert.setContentText(message);

        alert.showAndWait();
    }
}
