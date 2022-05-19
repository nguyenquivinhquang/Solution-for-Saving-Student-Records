package hcmiucvip.solutionforsavingstudentrecords;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Windows extends Application {
    public static Stage windowStage;
    @Override
    public void start(Stage stage) throws IOException {
//        adminDebug(stage);
//        temp("II");
//        temp("ITDSIU19017");
//        teacherView();
//        tuitionFee();
//        semesterBilling();
        Login();
        LoadScene loadScene = new LoadScene();
//        loadScene.adminClassAdd();
//    loadScene.adminTeacherAdd();
//        loadScene.adminClassAdd();
//        loadScene.adminStudentAdd();
//        loadScene.loadTeacherView("ltson");
//        loadScene.tuitionFee();
//        loadScene.loadAdmin();

    }


    public void adminDebug(Stage stage) throws IOException {
        windowStage = stage;
        CourseRegistration course = new CourseRegistration();
        course.setStudentId("12");
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("AdminClassAdd.fxml"));
        Scene scene = new Scene(fxmlLoader);
        stage.setTitle("Student records database!");
        stage.setScene(scene);
        stage.show();
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
    public void semesterBilling() throws IOException {
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
        controller.setStudentId("ITDSIU19017");
        controller.setStage(stage);
        stage.show();
        controller.init();
    }
    public void temp(String mssv) throws IOException {
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
        controller.setStudentId(mssv);
        stage.show();
        controller.init();
//        controller.initialize();

    }
    void teacherView() throws IOException {
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
    void Login() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "LoginForm.fxml"
                )
        );
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(
                new Scene(loader.load())
        );
        LoginController controller = loader.getController();

        stage.show();
        controller.setStage(stage);
    }
    public static void start(String[] args) {
        launch();
    }
}