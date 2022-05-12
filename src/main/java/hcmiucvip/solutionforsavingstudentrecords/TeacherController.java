package hcmiucvip.solutionforsavingstudentrecords;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class TeacherController {
    @FXML
    public ChoiceBox teacherChooseCLass;
    String teacherId;
    protected TextField inclassField, midtermField, finalField;

    public void setCourseCloseButtonClick(ActionEvent event) {
    }

    public void setCourseAboutButtonClick(ActionEvent event) {
    }

    public void setTeacherLoadClick(ActionEvent event) {
    }

    public void setTeacherAddGPAClick(ActionEvent event) {
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public void setTeacherCancelClick(ActionEvent event) {
        inclassField.clear();
        midtermField.clear();
        finalField.clear();
    }

    public void resetPassword(ActionEvent event) throws IOException {
        System.out.println("reset password");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ResetPassword.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setTitle("Course Panel");
        stage.show();
    }

    public void setTeacherSelectClick(ActionEvent event) {
    }
}
