package hcmiucvip.solutionforsavingstudentrecords;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Windows extends Application {
    public static Stage windowStage;
    @Override
    public void start(Stage stage) throws IOException {
        windowStage = stage;
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("AdminTeacherAdd.fxml"));
        Scene scene = new Scene(fxmlLoader);
        stage.setTitle("Student records database!");
        stage.setScene(scene);
        stage.show();
    }

    public static void start(String[] args) {
        launch();
    }
}