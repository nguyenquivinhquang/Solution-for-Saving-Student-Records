package hcmiucvip.solutionforsavingstudentrecords;

import hcmiucvip.solutionforsavingstudentrecords.core.CourseInformation;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseRegistration implements Initializable {
    public TableView<CourseInformation> tableCourseChosen, tableCoursePossible;
    public TableColumn<CourseInformation, String> courseIdChose,
            courseTitleChose,
            teacherIDChose,
            courseCreditChose,
            courseSectionChose;

    public TableColumn<CourseInformation, String> courseIdPossible,
            courseTitlePossible,
            teacherIdPossible,
            courseCreditPossible,
            courseSectionPossible;

    public void setCourseCloseButtonClick(ActionEvent event) {
    }

    public void setCourseAboutButtonClick(ActionEvent event) {
    }

    public void setRegistrationDeleteClick(ActionEvent event) {
    }

    public void setRegistrationTakeClick(ActionEvent event) {
    }

    public void setRegistrationDoneClick(ActionEvent event) {
    }

    public void setRegistrationRefreshClick(ActionEvent event) {
    }

    public void setRegistrationSearchClick(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
