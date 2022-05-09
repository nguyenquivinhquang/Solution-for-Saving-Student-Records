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
//        windowStage = stage;
//        CourseRegistration course = new CourseRegistration();
//        course.setStudentId("12");
//        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("AdminStudentAdd.fxml"));
//        Scene scene = new Scene(fxmlLoader);
//
//        stage.setTitle("Student records database!");
//        stage.setScene(scene);
//        stage.show();
//        temp("II");
        temp("IU");
    }
    public void temp(String mssv) throws IOException {
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
        controller.setStudentId(mssv);
        stage.show();
        controller.init();
//        controller.initialize();

    }

    public static void start(String[] args) {
        launch();
    }
}