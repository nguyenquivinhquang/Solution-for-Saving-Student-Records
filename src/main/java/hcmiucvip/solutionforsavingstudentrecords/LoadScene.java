package hcmiucvip.solutionforsavingstudentrecords;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LoadScene {
    public void loadStudent(String studenId) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "StudentView.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );
        StudentView controller = loader.getController();
        controller.setStudentId(studenId);
        stage.show();
        controller.init();
    }

    public void loadAdmin() {

    }

    public void loadTeacher(String teacherId) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "TeacherView.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );
        TeacherController controller = loader.getController();
        controller.setTeacherId("hvus");
        stage.show();
        controller.init();
    }

    public void semesterBilling(String studentId) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "SemesterBiilling.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );
        SemesterBillingView controller = loader.getController();
        controller.setStudentId(studentId);
        controller.setStage(stage);
        stage.show();
        controller.init();
    }
    public void studentRegistration(String studentId) throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "CourseRegistration.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );
        CourseRegistration controller = loader.getController();
        controller.setStudentId(studentId);
        controller.setStage(stage);
        stage.show();
        controller.init();
    }
}
