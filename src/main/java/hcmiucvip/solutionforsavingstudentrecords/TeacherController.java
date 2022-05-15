package hcmiucvip.solutionforsavingstudentrecords;

import hcmiucvip.solutionforsavingstudentrecords.core.CourseStudentScore;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.CourseQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.EnrolledCourseQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.StudentQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.TeacherQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.StudentInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TeacherController {
    @FXML
    public ComboBox teacherChooseCLass;
    String teacherId;
    @FXML
    protected TextField inclassField, midtermField, finalField;
    @FXML
    TableView<CourseStudentScore> teacherTableView;
    @FXML
    TableColumn<String, CourseStudentScore> studentIdCol, firstNameCol, lastNameCol, inClassCol, midtermCol, finalCol, totalCol;
    @FXML
    Button teacherAddGPAButton, teacherCancelButton;
    @FXML
    Text studentIdLabel;
    @FXML
    Text courseNameLabel;
    ObservableList<String> teacherClasses = FXCollections.observableArrayList();

    ObservableList<CourseStudentScore> studentRecord;
    ObservableList<StudentInformation> studentInformations;
    Map<String, CourseStudentScore> studentRecordTrace = new HashMap<>();
    TeacherQueries teacherQueries = new TeacherQueries();
    StudentQueries studentQueries = new StudentQueries();
    EnrolledCourseQueries enrolledCourseQueries = new EnrolledCourseQueries();

    String curSection, curCourseId;
    public void setCourseCloseButtonClick(ActionEvent event) {
    }

    public void setCourseAboutButtonClick(ActionEvent event) {
    }

    public void setTeacherLoadClick(ActionEvent event) {
        studentRecordTrace.clear();
        String courseSection = (String) teacherChooseCLass.getValue();
        Pair<String, String> _temp = parseGetSectionCourseId(courseSection);
        String courseId = _temp.getKey();
        String section = _temp.getValue();
        curCourseId = courseId;
        curSection = section;
        System.out.println(courseId + ' ' + section);
        studentRecord = teacherQueries.getStudentRecordList(teacherId, courseId, section);
        for (CourseStudentScore studentScore : studentRecord) {
            StudentInformation studentInfor = studentQueries.getStudentInformation(
                    studentScore.getStudentId());
            studentScore.setFirstName(studentInfor.getFirstName());
            studentScore.setLastName(studentInfor.getLastName());
            studentRecordTrace.put(studentScore.getStudentId(), studentScore);
        }
        teacherTableView.setItems(studentRecord);
        teacherTableView.refresh();

        CourseQueries courseQueries = new CourseQueries();
        courseNameLabel.setText(courseQueries.getCourseTitle(courseId));

    }

    public void setTeacherUpdateGPAClick() {
        Double inClassScore = Double.valueOf(inclassField.getText());
        Double midtermScore = Double.valueOf(midtermField.getText());
        Double finalScore = Double.valueOf(finalField.getText());
        Double totalScore = enrolledCourseQueries.countTotalScore(inClassScore, midtermScore, finalScore,
                curCourseId, curSection, this.teacherId);
        CourseStudentScore studentScore = studentRecordTrace.get(studentIdLabel.getText());

        enrolledCourseQueries.updateScore(inClassScore, midtermScore,finalScore,
                teacherId, studentScore.getCourseId(),curSection,studentScore.getStudentId());

        studentScore.setAssignmentScore(inClassScore);
        studentScore.setFinalScore(finalScore);
        studentScore.setMidtermScore(midtermScore);
        studentScore.setTotalScore(totalScore);
        teacherTableView.refresh();

        setEditableField(false);
        setVisibleField(false);
        studentIdLabel.setText("");

    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public void setTeacherCancelClick() {
        inclassField.clear();
        midtermField.clear();
        finalField.clear();
    }

    public void resetPassword(ActionEvent event) throws IOException {
        System.out.println("reset password");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ResetPassword.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setTitle("Course Panel");
        stage.show();
    }

    Pair<String, String> parseGetSectionCourseId(String str) {
        Pair<String, String> parseString;
        String delimsComma = "[-]+";
        String[] arr = str.split(delimsComma);
        parseString = new Pair<>(arr[0], arr[1]);
        System.out.println(arr[1] + " " + arr[0]);
        return parseString;
    }

    public void init() {
        ArrayList<String> temp = teacherQueries.getTeacherClass(this.teacherId);
        teacherClasses = FXCollections.observableArrayList(temp);
        teacherChooseCLass.setItems(teacherClasses);


        // Init for tableView
        studentIdCol.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        inClassCol.setCellValueFactory(new PropertyValueFactory<>("assignmentScore"));
        midtermCol.setCellValueFactory(new PropertyValueFactory<>("midtermScore"));
        finalCol.setCellValueFactory(new PropertyValueFactory<>("finalScore"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("totalScore"));


    }

    private void setVisibleField(boolean cond) {
        teacherAddGPAButton.setVisible(cond);
        teacherCancelButton.setVisible(cond);
        inclassField.setVisible(cond);
        midtermField.setVisible(cond);
        finalField.setVisible(cond);
    }
    private void setEditableField(boolean condition) {
        midtermField.setDisable(!condition);
        finalField.setDisable(!condition);
        inclassField.setDisable(!condition);
    }
    public void setTeacherSelectClick(ActionEvent event) {
        CourseStudentScore studentRecord = teacherTableView.getSelectionModel().getSelectedItem();
        System.out.println(studentRecord.getStudentId());
        inclassField.setText(String.format("%.0f", studentRecord.getAssignmentScore()));
        midtermField.setText(String.format("%.0f", studentRecord.getMidtermScore()));
        finalField.setText(String.format("%.0f", studentRecord.getFinalScore()));
        finalField.setText(String.format("%.0f", studentRecord.getFinalScore()));
//        totalLabel.setText(String.format("%.2f", studentRecord.getTotalScore()));

        teacherCancelButton.setDisable(false);
        teacherAddGPAButton.setDisable(false);

        studentIdLabel.setText(studentRecord.getStudentId());
        setVisibleField(true);
        setEditableField(true);

    }
}
