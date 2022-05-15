package hcmiucvip.solutionforsavingstudentrecords;

import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    Stage stage;
    LoadScene loadScene = new LoadScene();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void loadStudent() throws IOException {
        loadScene.adminStudentAdd();
        stage.close();
    }
    public void loadTeacher() throws IOException {
        loadScene.adminTeacherAdd();
        stage.close();
    }
    public void loadAddCourse() throws IOException {
        loadScene.adminCourseAdd();
        stage.close();
    }
    public void loadAddClass() throws IOException {
        System.out.println("add class");
        loadScene.adminClassAdd();
        stage.close();
    }
    public void loadAddTuition() throws IOException {
        loadScene.tuitionFee();
        stage.close();
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
