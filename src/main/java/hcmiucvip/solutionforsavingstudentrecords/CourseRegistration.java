package hcmiucvip.solutionforsavingstudentrecords;

import hcmiucvip.solutionforsavingstudentrecords.core.CourseInformation;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.CourseQueries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.*;

public class CourseRegistration {
    CourseQueries courseQueries = new CourseQueries();
    private String studentId;
    ObservableList<CourseInformation> courseInformationsChoosen, courseInformationsPossible;
    Map<String, CourseInformation> courseInformationTrace = new HashMap<>();

    public CourseRegistration(String studentId) {
        super();
        this.studentId = studentId;
    }

    public CourseRegistration() {
        super();

    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @FXML
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
            courseSectionPossible,
            courseSizePossible,
            courseRemainPossible;
    Set<String> courseChoosenTrace = new HashSet<>();

    public void setCourseCloseButtonClick(ActionEvent event) {
    }

    public void setCourseAboutButtonClick(ActionEvent event) {
    }

    public void setRegistrationDeleteClick(ActionEvent event) {
    }

    public void setRegistrationTakeClick(ActionEvent event) {
    }

    public void setRegistrationDoneClick(ActionEvent event) {
        System.out.println(studentId);
    }

    public void refresh() {
        tableCourseChosen.refresh();
        tableCoursePossible.refresh();

    }

    public void refreshClick() {
        refresh();
        loadStudentCourse();
    }

    public void setRegistrationSearchClick(ActionEvent event) {

    }


    public void initialize() {
        if (studentId == null) return;
        System.out.println(studentId);
    }

    public void loadStudentCourse() {
        courseChoosenTrace.clear();
        ArrayList<String> courseRegistered = courseQueries.getCourseRegistration(this.studentId);
//        courseInformationsChoosen =
        courseInformationsPossible = courseQueries.getCoursesList();
        for (CourseInformation course : courseInformationsPossible) {
            courseInformationTrace.put(course.getCourseId(), course);
        }
        courseInformationsChoosen = FXCollections.observableArrayList();
        for (String courseId : courseRegistered) {
            courseInformationsChoosen.add(courseInformationTrace.get(courseId));
            courseChoosenTrace.add(courseId);
        }
        System.out.println(courseInformationsChoosen);
    }

    public void init() {
        loadStudentCourse();
        courseIdChose.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        courseTitleChose.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        teacherIDChose.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
        courseCreditChose.setCellValueFactory(new PropertyValueFactory<>("courseCredits"));
        courseSectionChose.setCellValueFactory(new PropertyValueFactory<>("courseSection"));
        tableCourseChosen.setItems(courseInformationsChoosen);

        courseIdPossible.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        courseTitlePossible.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        teacherIdPossible.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
        courseCreditPossible.setCellValueFactory(new PropertyValueFactory<>("courseCredits"));
        courseSectionPossible.setCellValueFactory(new PropertyValueFactory<>("courseSection"));
        courseSizePossible.setCellValueFactory(new PropertyValueFactory<>("size"));
        courseRemainPossible.setCellValueFactory(new PropertyValueFactory<>("remaining"));
        tableCoursePossible.setItems(courseInformationsPossible);

    }

    public void setRegistrationRefreshClick() {
        refresh();
        init();
    }

    public void deleteRegisterClick() {
        CourseInformation course = tableCourseChosen.getSelectionModel().getSelectedItem();

        courseQueries.deleteCourseRegistered(this.studentId, course.getCourseId());
        courseChoosenTrace.remove(course.getCourseTitle());
        courseInformationsChoosen.remove(course);
    }

    boolean checkCanAddCourse(CourseInformation course) {
        if (courseChoosenTrace.contains(course.getCourseTitle())) {
            showWarning("Course already registered");
            return false;
        }
        if (courseQueries.getRemainSlot(course.getCourseId()) < 1) {
            showWarning("Out of slot!!!!");
            return false;
        }
        return true;
    }

    public void chooseRegisterClick() {
        System.out.println(tableCoursePossible.getSelectionModel().getSelectedItem());
        CourseInformation course = tableCoursePossible.getSelectionModel().getSelectedItem();
        if (checkCanAddCourse(course) == false) {
            return;
        }
        courseInformationsChoosen.add(course);
        courseChoosenTrace.add(course.getCourseTitle());
        refresh();
        courseQueries.addCourseStudentRegistered(this.studentId, course.getCourseId());
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
