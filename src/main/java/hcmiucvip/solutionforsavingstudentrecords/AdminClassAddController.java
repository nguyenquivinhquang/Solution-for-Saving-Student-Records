package hcmiucvip.solutionforsavingstudentrecords;

import hcmiucvip.solutionforsavingstudentrecords.core.Classroom;
import hcmiucvip.solutionforsavingstudentrecords.core.CourseInformation;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.CourseQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.DatabaseConnectionManager;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.TeacherQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.TeacherInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.Connection;
import java.util.*;
import java.util.regex.Pattern;

public class AdminClassAddController implements Initializable {
    //Todo: Add courseSize, courseRemain
    public ChoiceBox sectionSelect;
    public Button saveButton;
    boolean isEdit = false;
    @FXML
    Label courseIdField, courseTitleField;
    @FXML
    TextArea descriptionTextArea;
    @FXML
    Text teacherIdField, teacherNameField;
    @FXML
    TextField classSize;
    private CourseQueries courseQueries = new CourseQueries();
    private TeacherQueries teacherQueries = new TeacherQueries();
    @FXML
    public TableView<CourseInformation> adminClassView, adminCourseView;
    @FXML
    public TableView<TeacherInformation> teacherViewTable;
    @FXML
    public TableColumn<CourseInformation, String> courseIdClass, courseTitleClass, teacherIdClass, courseSectionClass, classSizeClass, classRemainingClass;
    @FXML
    public TableColumn<CourseInformation, String> courseIdDetail, courseTitleDetail, courseCreditsDetail;
    @FXML
    public TableColumn<TeacherInformation, String> teacherIdCol, teacherNameCol, teacherDepartmentRow;
    private HashMap<String, CourseInformation> courseTrace = new HashMap<>();
    private static Connection connection = DatabaseConnectionManager.getDBConnection();

    ObservableList<CourseInformation> courseInformations, classInformation;
    ObservableList<TeacherInformation> teacherInformations;
    ObservableList<String> Day = FXCollections.observableArrayList("Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
    Map<String, TeacherInformation> teacherInformationTrace = new HashMap<>();
    Map<String, CourseInformation> classTrace = new HashMap<>();

    public void setSectionSelect() {
        sectionSelect.setItems(Day);
    }


    public void setCourseAddNewButtonClick(ActionEvent event) {
        enableVisibleField();
        resetField();
        saveButton.setText("Add");
    }

    public void setCourseEditButtonClick(ActionEvent event) {
        saveButton.setText("Save");
        isEdit = true;
        enableVisibleField();
        CourseInformation course = adminClassView.getSelectionModel().getSelectedItem();
        if (course == null) return;
        System.out.println(course);
        courseIdField.setText(course.getCourseId());
        courseTitleField.setText(course.getCourseTitle());
        sectionSelect.setValue(course.getCourseSection());
        teacherIdField.setText(course.getTeacherId());
        teacherNameField.setText(teacherInformationTrace.get(course.getTeacherId()).getFirstName());
        classSize.setText(String.valueOf(course.getSize()));

        sectionSelect.setVisible(false);
    }

    public void setCourseDeleteButtonClick(ActionEvent event) {
        CourseInformation course = adminClassView.getSelectionModel().getSelectedItem();
        courseQueries.deleteClass(course.getCourseId(), course.getTeacherId(), course.getCourseSection());
        classInformation.remove(course);

        classTrace.remove(getHashKeyCourse(course));
        resetField();
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
            showWarning("You must choose course");
            return false;
        }
        if (courseTitleField.getText().isBlank()) {
            showWarning("You must choose course");
            return false;
        }

        if (teacherIdField.getText().isBlank()) {
            showWarning("You must choose Teacher");
            return false;
        }
        if (!teacherQueries.existTeacherId(teacherIdField.getText())) {
            showWarning("You must choose teacher");
            return false;
        }
        if (sectionSelect.getValue() == null) {
            showWarning("Course section must be chosen");
            return false;
        }
        if (classSize.getText() == null || classSize.getText().isBlank()) {
            showWarning("Class size must not empty");
            return false;
        }
        if (isEdit == false) {
            String _section = (String) sectionSelect.getValue();
            String _tempKey = courseIdField.getText().toLowerCase()
                    + teacherIdField.getText().toLowerCase()
                    + _section.toLowerCase();
            if (classTrace.containsKey(_tempKey)) {
                showWarning("This class already exist");
                return false;
            }
        }


        return true;
    }

    private void resetField() {
        courseIdField.setText("");
        courseTitleField.setText("");
        sectionSelect.setValue("");
        sectionSelect.setItems(Day);
        teacherIdField.setText("");
        teacherNameField.setText("");
        classSize.setText("");
        setVisibleField(false);

    }

    private void setVisibleField(boolean condition) {
        courseIdField.setVisible(condition);
        courseTitleField.setVisible(condition);
        sectionSelect.setVisible(condition);
        teacherIdField.setVisible(condition);
        classSize.setVisible(condition);
    }

    private void disableVisibleField() {
        setVisibleField(false);
    }

    private void enableVisibleField() {
        setVisibleField(true);
    }


    private void editClass() {

        Integer courseSize = Integer.valueOf(classSize.getText());
        String courseId = courseIdField.getText().trim(),
                teacherId = teacherIdField.getText().trim(),
                section = (String) sectionSelect.getValue();
        courseQueries.updateCourseSize(courseId, teacherId, section, courseSize);
        String tempKey = courseId.toLowerCase() + teacherId.toLowerCase() + section.toLowerCase();
        classTrace.get(tempKey).setSize(courseSize);
        adminCourseView.refresh();
        adminClassView.refresh();
        resetField();
        disableVisibleField();
    }

    public void addClass() {
        String courseId = courseIdField.getText().trim();
        String courseTitle = courseTitleField.getText().trim();
        String courseSection = (String) sectionSelect.getValue();
        String teacherId = teacherIdField.getText().trim();
        String teacherName = teacherNameField.getText().trim();
        Integer size = Integer.parseInt(classSize.getText());

        CourseInformation course = new CourseInformation(courseId, teacherId, courseSection, size, size);
        course.setCourseTitle(courseTitle);
        courseQueries.addClass(courseId, teacherId, courseSection, size, size);
        classInformation.add(course);
        classTrace.put(getHashKeyCourse(course), course);
        resetField();
    }

    public void setCourseSaveButtonClick() {
        if (!inputOK()) return;
        if (isEdit == true) {
            // Edit course
            editClass();

        } else {
            // Add course
            addClass();
        }
        isEdit = false;

    }

    private String getHashKeyCourse(CourseInformation course) {
        String hashKey = course.getCourseId().toLowerCase().trim() +
                course.getTeacherId().toLowerCase().trim() +
                course.getCourseSection().toLowerCase().trim();
        return hashKey;
    }

    public void handleClassroom() {
        classInformation = courseQueries.getClassList();

        for (CourseInformation _class : classInformation) {
            String courseId = _class.getCourseId();
            CourseInformation course = courseTrace.get(courseId);
            _class.setCourseTitle(course.getCourseTitle());


            classTrace.put(getHashKeyCourse(_class), _class);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Course
        courseIdDetail.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        courseTitleDetail.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        courseCreditsDetail.setCellValueFactory(new PropertyValueFactory<>("courseCredits"));

        courseInformations = courseQueries.getCoursesList();
        for (CourseInformation course : courseInformations) {
            courseTrace.put(course.getCourseId(), course);
            System.out.println(course);
        }
        adminCourseView.setItems(courseInformations);

        // init teacher
        teacherIdCol.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
        teacherNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        teacherDepartmentRow.setCellValueFactory(new PropertyValueFactory<>("department"));
        teacherInformations = teacherQueries.getTeacherList();
        teacherViewTable.setItems(teacherInformations);
        for (TeacherInformation teacher : teacherInformations) {
            teacherInformationTrace.put(teacher.getTeacherId(), teacher);
        }

        // Classroom information
        courseIdClass.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        courseTitleClass.setCellValueFactory(new PropertyValueFactory<>("courseTitle"));
        courseSectionClass.setCellValueFactory(new PropertyValueFactory<>("courseSection"));
        teacherIdClass.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
        classSizeClass.setCellValueFactory(new PropertyValueFactory<>("size"));
        classRemainingClass.setCellValueFactory(new PropertyValueFactory<>("remaining"));
        handleClassroom();
        adminClassView.setItems(classInformation);

        // Clean
        disableVisibleField();

        courseIdField.setText("");
        courseTitleField.setText("");
        teacherIdField.setText("");
        teacherNameField.setText("");
    }

    public void chooseCourseButton() {
        saveButton.setText("Add");
        setVisibleField(true);
        CourseInformation course = adminCourseView.getSelectionModel().getSelectedItem();
        System.out.println(course);
        courseIdField.setText(course.getCourseId());
        courseTitleField.setText(course.getCourseTitle());
    }

    public void teacherChooseButton(ActionEvent event) {
        saveButton.setText("Add");
        setVisibleField(true);
        TeacherInformation teacher = teacherViewTable.getSelectionModel().getSelectedItem();
        teacherIdField.setText(teacher.getTeacherId());
        teacherNameField.setText(teacher.getFirstName());
    }

    public void setSaveButtonClick() {
        if (isEdit == true) {
            // do edit
        } else {
            // add new
        }

    }

    public void setCourseCloseButtonClick(ActionEvent event) {
    }

    public void setCourseAboutButtonClick(ActionEvent event) {
    }
}
