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

    public void loadAdmin() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "AdminHome.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );
        AdminController controller = loader.getController();
        controller.setStage(stage);
        stage.show();

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
    public void loadScholar() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "AdminSchoolarAdd.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );
        AdminScholarshipAdd controller = loader.getController();
        controller.setStage(stage);
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
    public void adminClassAdd() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "AdminClassAdd.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );
        AdminClassAddController controller = loader.getController();
//        controller.setStudentId(studentId);
//        controller.setStage(stage);
        stage.show();
//        controller.init();
    }
    public void adminTeacherAdd() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "AdminTeacherAdd.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );
        AdminTeacherAddController controller = loader.getController();
        stage.show();
    }
    public void adminCourseAdd() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "AdminCourseAdd.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );
        AdminCourseAddController controller = loader.getController();
        stage.show();
    }
    public void adminStudentAdd() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "AdminStudentAdd.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );
        AdminStudentAddController controller = loader.getController();
        stage.show();
    }

    public void loadTeacherView(String teacherId) throws IOException {
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
        controller.setTeacherId(teacherId);
        stage.show();
        controller.init();
    }
    public void tuitionFee() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "TuitionFee.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );
        TuitionFeeView controller = loader.getController();

        stage.show();
        controller.init();
//        controller.initialize();
    }

}
