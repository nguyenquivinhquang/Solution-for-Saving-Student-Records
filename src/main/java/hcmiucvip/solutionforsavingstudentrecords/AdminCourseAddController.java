package hcmiucvip.solutionforsavingstudentrecords;

import hcmiucvip.solutionforsavingstudentrecords.core.CourseInformation;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.CourseQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.DatabaseConnectionManager;
import hcmiucvip.solutionforsavingstudentrecords.core.DeleteTable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AdminCourseAddController implements Initializable {
    //Todo: Add courseSize, courseRemain
    public Button saveButton;
    boolean isEdit = false;
    @FXML
    TextField courseIdField, courseTitleField, courseCreditField;
    @FXML
    TextArea descriptionTextArea;


    private CourseQueries courseQueries = new CourseQueries();

    @FXML
    public TableView<CourseInformation> adminCourseView;

    @FXML
    public TableColumn<CourseInformation, String> courseTableId, courseTableTitle;
    @FXML
    public TableColumn<CourseInformation, String> courseTableCredits, courseTableDescription;

    private HashMap<String, CourseInformation> courseTrace = new HashMap<>();
    private static Connection connection = DatabaseConnectionManager.getDBConnection();
    ObservableList<CourseInformation> courseInformations;

    public void setCourseCloseButtonClick(ActionEvent event) {
    }


    public void setCourseAboutButtonClick(ActionEvent event) {

    }

    public void setCourseAddNewButtonClick(ActionEvent event) {
        enableVisibleField();
        resetField();
        saveButton.setText("Add");
        courseIdField.setEditable(true);
    }

    public void setCourseEditButtonClick(ActionEvent event) {
        saveButton.setText("Save");
        isEdit = true;
        enableVisibleField();
        CourseInformation course = adminCourseView.getSelectionModel().getSelectedItem();
        if (course == null) return;
        courseIdField.setText(course.getCourseId());
        courseIdField.setEditable(false);
        courseTitleField.setText(course.getCourseTitle());
        courseCreditField.setText(String.valueOf(course.getCourseCredits()));
//        teacherIdField.setText(course.getTeacherId());
        descriptionTextArea.setText(course.getCourseDescription());
    }

    public void setCourseDeleteButtonClick(ActionEvent event) {
        CourseInformation course = adminCourseView.getSelectionModel().getSelectedItem();
        DeleteTable deleteTable = new DeleteTable();
        deleteTable.deleteCourse(course.getCourseId());
        courseInformations.remove(course);
        courseTrace.remove(course.getCourseId());
        resetField();
    }
    public void setCourseRefreshButtonClick(ActionEvent event) {
        adminCourseView.refresh();
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setCourseClearButtonClick() {
        resetField();
    }

    private boolean inputOK() {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (courseIdField.getText().isBlank()) {
            showWarning("Course Id must not empty");
            return false;
        }
        if (courseTitleField.getText().isBlank()) {
            showWarning("Course Title must not empty");
            return false;
        }
        if (courseCreditField.getText().isBlank()) {
            showWarning("Course credit must not empty");
            return false;
        }

        if (!pattern.matcher(courseCreditField.getText()).matches()) {
            showWarning("Course credit be a number");
            return false;
        }
        return true;
    }

    private void resetField() {
        courseIdField.clear();
        courseTitleField.clear();
        courseCreditField.clear();
        descriptionTextArea.clear();
    }

    private void setVisibleField(boolean condition) {
        courseIdField.setVisible(condition);
        courseTitleField.setVisible(condition);
        courseCreditField.setVisible(condition);
    }

    private void disableVisibleField() {
        setVisibleField(false);
    }

    private void enableVisibleField() {
        setVisibleField(true);
    }

    private void addCourse() {
        CourseInformation course = new CourseInformation(courseIdField.getText(), courseTitleField.getText(),
                Integer.parseInt(courseCreditField.getText()), descriptionTextArea.getText());
        courseQueries.addCourse(courseIdField.getText(),
                courseTitleField.getText(),
                descriptionTextArea.getText(),
                Integer.parseInt(courseCreditField.getText()));
        adminCourseView.refresh();
        courseInformations.add(course);
        courseTrace.put(course.getCourseId(), course);
        resetField();
    }

    private void editCourse() {
        String courseId = courseIdField.getText().toUpperCase().trim();
        String courseTitle = courseTitleField.getText();
        Integer courseCredit = Integer.valueOf(courseCreditField.getText());
        String description = descriptionTextArea.getText();

        if (description == null) description = "";
        courseQueries.updateCourseName(courseId, courseTitle);
        courseQueries.updateCourseCredits(courseId, courseCredit);
        courseQueries.updateCourseDescription(courseId, description);
        CourseInformation course = courseTrace.get(courseId);

        course.setCourseCredits(courseCredit);
        course.setCourseDescription(description);
        course.setCourseTitle(courseTitle);

        adminCourseView.refresh();
        resetField();
        disableVisibleField();
    }

    public void setCourseSaveButtonClick() {
        if (!inputOK()) return;
        if (isEdit == true) {
            // Edit course
            editCourse();

        } else {
            // Add course
            addCourse();
        }
        isEdit = false;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        courseTableId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        courseTableTitle.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        courseTableCredits.setCellValueFactory(new PropertyValueFactory<>("courseCredits"));
        courseTableDescription.setCellValueFactory(new PropertyValueFactory<>("courseDescription"));

        courseInformations = courseQueries.getCoursesList();
        for (CourseInformation course : courseInformations) {
            courseTrace.put(course.getCourseId(), course);
            System.out.println(course);
        }
        adminCourseView.setItems(courseInformations);
        disableVisibleField();

    }
}
