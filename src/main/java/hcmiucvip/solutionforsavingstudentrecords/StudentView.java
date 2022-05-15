package hcmiucvip.solutionforsavingstudentrecords;

import hcmiucvip.solutionforsavingstudentrecords.core.Auth.UserAuthenticator;
import hcmiucvip.solutionforsavingstudentrecords.core.CourseInformation;
import hcmiucvip.solutionforsavingstudentrecords.core.CourseStudentScore;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.CourseQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.EnrolledCourseQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.StudentQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.StudentInformation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class StudentView {
    StudentInformation studentInformation;
    StudentQueries studentQueries = new StudentQueries();
    CourseQueries courseQueries = new CourseQueries();
    EnrolledCourseQueries enrolledCourseQueries = new EnrolledCourseQueries();
    private String studentId;
    @FXML
    public PasswordField oldPasswordField, newPasswordField, reNewPasswordField;
//    @FXML
//    TableView<CourseInformation> timeTable;
    @FXML
    TableView<CourseStudentScore> resultHistoryTable;
//    @FXML
//    TableColumn<CourseInformation, String> studentRCourseColumnCode, studentRCourseColumnTitle, studentRCourseColumnSec, studentRCourseColumnCredits;
    @FXML
    TableColumn<CourseStudentScore, String> resultHistoryColumnCode, resultHistoryColumnName, resultHistoryColumnSemester,
            resultHistoryColumnAssignment, resultHistoryColumnMidterm, resultHistoryColumnFinal, resultHistoryColumnTotal;
    @FXML
    Label firstnameLabel, lastnameLabel, emailLabel;
    @FXML
    Text totalCreditsText, averageScoreText;
    ObservableList<CourseStudentScore> courseStudentScores;
    ObservableList<CourseInformation> courseSection;

    private int totalCredits = 0;
    private double totalScore = 0;
    public void setCourseCloseButtonClick(ActionEvent event) {
    }

    public void setStudentSaveClick(ActionEvent event) {
        if (oldPasswordField.getText() == "" || newPasswordField.getText() == ""
                || reNewPasswordField.getText() == "") {
            showWarning("Password field must not empty");
            return;
        }
        if (UserAuthenticator.auth(this.studentId, oldPasswordField.getText()).getKey() == "none") {
            showWarning("Old Password is not correct");
            return;
        }
        if (newPasswordField.getText().trim().equals(reNewPasswordField.getText().trim()) == false) {
            showWarning("New password does not match");
            return;
        }
        studentQueries.updateUserPass(this.studentId, newPasswordField.getText().trim());
        oldPasswordField.clear();
        newPasswordField.clear();
        reNewPasswordField.clear();

    }

    private void getStudentScoreRecord() {
        courseStudentScores = enrolledCourseQueries.getStudentScoreList(this.studentId);
        for (CourseStudentScore courseStudentScore: courseStudentScores) {
            courseStudentScore.setCourseName(courseQueries.getCourseName(courseStudentScore.getCourseId()));
        }
    }

    private void getRunningCourse() {
        courseSection = studentQueries.getCurrentRunningCourse(this.studentId);

        for (CourseInformation course : courseSection) {
//            course.setCourseSection(courseQueries.getCourseSection(course.getCourseId()));
            course.setCourseCredits(courseQueries.getCourseCredit(course.getCourseId()));
            course.setCourseTitle(courseQueries.getCourseName(course.getCourseId()));
        }
    }

    public void init() {
        getRunningCourse();
        getStudentScoreRecord();
//        studentRCourseColumnCredits.setCellValueFactory(new PropertyValueFactory<>("courseCredits"));
//        studentRCourseColumnTitle.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
//        studentRCourseColumnSec.setCellValueFactory(new PropertyValueFactory<>("courseSection"));

//        timeTable.setItems(courseSection);

        resultHistoryColumnCode.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        resultHistoryColumnName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        resultHistoryColumnSemester.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        resultHistoryColumnAssignment.setCellValueFactory(new PropertyValueFactory<>("assignmentScore"));
        resultHistoryColumnMidterm.setCellValueFactory(new PropertyValueFactory<>("midtermScore"));
        resultHistoryColumnFinal.setCellValueFactory(new PropertyValueFactory<>("finalScore"));
        resultHistoryColumnTotal.setCellValueFactory(new PropertyValueFactory<>("totalScore"));

        resultHistoryTable.setItems(courseStudentScores);
        studentInformation = studentQueries.getStudentInformation(this.studentId);
        updateStudentInformationField();
        calculateRecord();

        totalCreditsText.setText(String.valueOf(totalCredits));
        Double avgScore;
        System.out.println(totalScore / totalCredits);
        if (totalCredits == 0) avgScore = 0.0;
        else avgScore = totalScore / totalCredits;
        averageScoreText.setText(String.format("%.2f", avgScore));

    }

    private void updateStudentInformationField() {
        if (this.studentInformation == null) return;
        firstnameLabel.setText(studentInformation.getFirstName());
        lastnameLabel.setText(studentInformation.getLastName());
        emailLabel.setText(studentInformation.getMail());
        oldPasswordField.setText("");
        newPasswordField.setText("");
        reNewPasswordField.setText("");
    }

    public void setStudentCancelClick() {
        oldPasswordField.setText("");
        newPasswordField.setText("");
        reNewPasswordField.setText("");

    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setStudentRegistrationClick() throws IOException {
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
        controller.setStudentId(this.studentId);
        controller.setStage(stage);
        stage.show();
        controller.init();
    }
    private void calculateRecord() {
        totalScore = 0;
        totalCredits = 0;
        for (CourseStudentScore course : courseStudentScores) {

            Integer curCredits = courseQueries.getCourseCredit(course.getCourseId());
            totalScore += course.getTotalScore() * curCredits;

            totalCredits += curCredits;

        }

    }
    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        // alert.setHeaderText("Results:");
        alert.setContentText(message);
        alert.showAndWait();
    }
    public void refreshClick() {
        init();
    }
}
