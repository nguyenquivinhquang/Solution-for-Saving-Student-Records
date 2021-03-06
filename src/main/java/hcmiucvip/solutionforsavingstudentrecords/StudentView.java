package hcmiucvip.solutionforsavingstudentrecords;

import hcmiucvip.solutionforsavingstudentrecords.core.Auth.AuthUtil;
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
    boolean isEdit = false;
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
    TextField firstnameLabel, lastnameLabel, emailLabel;
    @FXML
    Button saveProfile;
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
            studentQueries.updateUserPass(this.studentId, AuthUtil.hashString(newPasswordField.getText().trim()));
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

        reNewPasswordField.setEditable(true);
        reNewPasswordField.setVisible(true);

        labelFieldEditable(false);
        isEdit = false;
        saveProfile.setVisible(false);

    }
    private void labelFieldEditable(boolean condition) {
        firstnameLabel.setEditable(condition);
        lastnameLabel.setEditable(condition);
        emailLabel.setEditable(condition);
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
        LoadScene loadScene = new LoadScene();

        loadScene.studentRegistration(this.studentId);
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
    public void setSemesterBillingClick() throws IOException {
        LoadScene loadScene = new LoadScene();

        loadScene.semesterBilling(this.studentId);
    }
    public void setStudentEditClick() {
        isEdit = true;
        labelFieldEditable(true);
        saveProfile.setVisible(true);
    }
    public void saveProfileClick() {
        studentQueries.updateFirstName(this.studentId,firstnameLabel.getText());
        studentQueries.updateLastName(this.studentId, lastnameLabel.getText());
        studentQueries.updateMail(this.studentId, emailLabel.getText());
        isEdit = false;
        labelFieldEditable(false);
        saveProfile.setVisible(false);

    }
}
