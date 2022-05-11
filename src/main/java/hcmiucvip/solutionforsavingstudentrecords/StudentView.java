package hcmiucvip.solutionforsavingstudentrecords;

import hcmiucvip.solutionforsavingstudentrecords.core.CourseInformation;
import hcmiucvip.solutionforsavingstudentrecords.core.CourseStudentScore;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.CourseQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.StudentQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.StudentInformation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class StudentView {
    StudentInformation studentInformation;
    StudentQueries studentQueries = new StudentQueries();
    CourseQueries courseQueries = new CourseQueries();
    private String studentId;
    @FXML
    public TextField firstnameField, lastnameField, emailField, oldPassword, newPassField;
    @FXML
    TableView<CourseInformation> timeTable;
    @FXML
    TableColumn<CourseInformation, String> studentRCourseColumnCode, studentRCourseColumnTitle, studentRCourseColumnSec,studentRCourseColumnCredits;
    @FXML
    TableColumn<CourseInformation, String> resultHistoryColumnCode, resultHistoryColumnName, resultHistoryColumnSemester,
            resultHistoryColumnAssignment, resultHistoryColumnMidterm, resultHistoryColumnFinal, resultHistoryColumnTotal;
    @FXML
    Label firstnameLabel, lastnameLabel;
    ObservableList<CourseStudentScore> courseStudentScores;
    ObservableList<CourseInformation> courseSection;

    public void setCourseCloseButtonClick(ActionEvent event) {
    }

    public void setStudentSaveClick(ActionEvent event) {
        firstnameField.clear();
        lastnameField.clear();
        emailField.clear();
        oldPassword.clear();
        newPassField.clear();
    }

    private void getStudentScore() {

    }

    private void getRunningCourse() {
        courseSection = studentQueries.getCurrentRunningCourse(this.studentId);

        for (CourseInformation course: courseSection) {
            course.setCourseSection(courseQueries.getCourseSection(course.getCourseId()));
            course.setCourseCredits(courseQueries.getCourseCredit(course.getCourseId()));
            course.setCourseTitle(courseQueries.getCourseName(course.getCourseId()));
        }
    }

    public void init() {
        getRunningCourse();
        studentRCourseColumnCredits.setCellValueFactory(new PropertyValueFactory<>("courseCredits"));
        studentRCourseColumnTitle.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        studentRCourseColumnSec.setCellValueFactory(new PropertyValueFactory<>("courseSection"));

        timeTable.setItems(courseSection);
//        resultHistoryColumnCode.setCellValueFactory(new PropertyValueFactory<>("courseId"));
//        resultHistoryColumnName.setCellValueFactory(new PropertyValueFactory<>("courseId"));
//        resultHistoryColumnSemester.setCellValueFactory(new PropertyValueFactory<>("courseId"));
//        resultHistoryColumnAssignment.setCellValueFactory(new PropertyValueFactory<>("assignmentScore"));
//        resultHistoryColumnMidterm.setCellValueFactory(new PropertyValueFactory<>("midtermScore"));
//        resultHistoryColumnFinal.setCellValueFactory(new PropertyValueFactory<>("finalScore"));
//        resultHistoryColumnTotal.setCellValueFactory(new PropertyValueFactory<>("totalScore"));
         studentInformation = studentQueries.getStudentInformation(this.studentId);
         updateStudentInformationField();
    }
    private void updateStudentInformationField() {
        if (this.studentInformation == null) return;
        firstnameLabel.setText(studentInformation.getFirstName());
        lastnameLabel.setText(studentInformation.getLastName());

    }
    public void setStudentCancelClick() {

    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
