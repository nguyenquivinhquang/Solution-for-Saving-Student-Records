package hcmiucvip.solutionforsavingstudentrecords;

import hcmiucvip.solutionforsavingstudentrecords.core.CourseInformation;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.CourseQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.DatabaseConnectionManager;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.TeacherQueries;
import javafx.collections.FXCollections;
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
    public ChoiceBox sectionSelect;
    public Button saveButton;
    boolean isEdit = false;
    @FXML
    TextField courseIdField, courseTitleField, courseCreditField;
    @FXML
    TextArea descriptionTextArea;
    @FXML
    TextField teacherIdField;

    private CourseQueries courseQueries = new CourseQueries();
    private TeacherQueries teacherQueries = new TeacherQueries();
    @FXML
    public TableView<CourseInformation> adminCourseView;

    @FXML
    public TableColumn<CourseInformation, String> courseTableId, courseTableTitle;
    @FXML
    public TableColumn<CourseInformation, String> courseTableCredits, courseTableDescription, courseTableSection, courseTeacherName;

    private HashMap<String, CourseInformation> courseTrace = new HashMap<>();
    private static Connection connection = DatabaseConnectionManager.getDBConnection();
    ObservableList<CourseInformation> courseInformations;

    public void setCourseCloseButtonClick(ActionEvent event) {
    }

    ObservableList<String> Day = FXCollections.observableArrayList("Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

    public void setSectionSelect() {
        sectionSelect.setItems(Day);
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
        sectionSelect.setValue(course.getCourseSection());
        teacherIdField.setText(course.getTeacherId());
        descriptionTextArea.setText(course.getCourseDescription());
    }

    public void setCourseDeleteButtonClick(ActionEvent event) {
        CourseInformation course = adminCourseView.getSelectionModel().getSelectedItem();
        courseQueries.deleteCourse(course.getCourseId());
        courseInformations.remove(course);
        courseTrace.remove(course.getCourseId());
        resetField();
    }

    public void setCourseSearchButtonClick(ActionEvent event) {
    }

    public void setCourseRefreshButtonClick(ActionEvent event) {
        adminCourseView.refresh();
    }

    private void showWarning(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        // alert.setHeaderText("Results:");
        alert.setContentText(message);

        alert.showAndWait();
    }

    public void setCourseClearButtonClick() {
        resetField();
    }

    private boolean inputOK() {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        System.out.println(sectionSelect.getValue());
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
        if (teacherIdField.getText().isBlank()) {
            showWarning("Teacher Id must not empty");
            return false;
        }
        if (!teacherQueries.existTeacherId(teacherIdField.getText())) {
            showWarning("Teacher Id isn't exist");
            return false;
        }
        if (sectionSelect.getValue() == null) {
            showWarning("Course section must be chosen");
            return false;
        }
        return true;
    }

    private void resetField() {
        courseIdField.clear();
        courseTitleField.clear();
        courseCreditField.clear();
        sectionSelect.setValue("");
        sectionSelect.setItems(Day);
        teacherIdField.clear();
        descriptionTextArea.clear();

    }

    private void setVisibleField(boolean condition) {
        courseIdField.setVisible(condition);
        courseTitleField.setVisible(condition);
        courseCreditField.setVisible(condition);
        sectionSelect.setVisible(condition);
        teacherIdField.setVisible(condition);
    }

    private void disableVisibleField() {
        setVisibleField(false);
    }

    private void enableVisibleField() {
        setVisibleField(true);
    }

    private void addCourse() {
        // Todo: add course size field
        CourseInformation course = new CourseInformation(courseIdField.getText(), courseTitleField.getText(),
                Integer.parseInt(courseCreditField.getText()), teacherIdField.getText(), descriptionTextArea.getText(), (String) sectionSelect.getValue());
        Integer courseSize = 30;
        courseQueries.addCourse(courseIdField.getText(), courseTitleField.getText(),
                Integer.parseInt(courseCreditField.getText()), descriptionTextArea.getText(),
                teacherIdField.getText(), (String) sectionSelect.getValue(), courseSize, courseSize);
        adminCourseView.refresh();
        courseInformations.add(course);
        courseTrace.put(course.getCourseId(), course);
        resetField();
    }
    private void editCourse() {
        String courseId = courseIdField.getText().toLowerCase();
        String courseTitle = courseTitleField.getText();
        Integer courseCredit = Integer.valueOf(courseCreditField.getText());
        String courseSelection = (String) sectionSelect.getValue();
        String teacherId = teacherIdField.getText();
        String description = descriptionTextArea.getText();

        if (description == null) description = "";
        courseQueries.updateCourseName(courseId, courseTitle);
        courseQueries.updateCourseTeacherId(courseId, teacherId);
        courseQueries.updateCourseCredits(courseId, courseCredit);
        courseQueries.updateCourseDescription(courseId, description);
        courseQueries.updateCourseSection(courseId, courseSelection);

        CourseInformation course = courseTrace.get(courseId);

        course.setCourseCredits(courseCredit);
        course.setCourseDescription(description);
        course.setCourseTitle(courseTitle);
        course.setCourseSection(courseSelection);
        course.setTeacherId(teacherId);

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
        courseTableSection.setCellValueFactory(new PropertyValueFactory<>("courseSection"));
        courseTeacherName.setCellValueFactory(new PropertyValueFactory<>("teacherId"));

        courseInformations = courseQueries.getCoursesList();
        for (CourseInformation course : courseInformations) {
            courseTrace.put(course.getCourseId(), course);
            System.out.println(course);
        }
        adminCourseView.setItems(courseInformations);
        disableVisibleField();

    }
}
