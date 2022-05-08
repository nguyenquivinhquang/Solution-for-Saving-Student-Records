package hcmiucvip.solutionforsavingstudentrecords;

import hcmiucvip.solutionforsavingstudentrecords.core.Auth.AuthUtil;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.TeacherQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.StudentInformation;
import hcmiucvip.solutionforsavingstudentrecords.core.TeacherInformation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AdminTeacherAddController implements Initializable {
    private boolean isEdit = false;
    @FXML
    protected TextField firstnameField, lastnameField, teacherIdField, mailField;
    @FXML
    protected TextField departmentField, passField;
    @FXML
    protected TableView<TeacherInformation> teacherTableView;
    @FXML
    public TableColumn<TeacherInformation, String> teacherIdCol, firstNameCol, lastNameCol, emailCol, departmentCol;
    @FXML
    public Button saveButton;
    Label warningField;
    private HashMap<String, TeacherInformation> teacherTrace = new HashMap<>();
    ObservableList<TeacherInformation> teacherInformations;
    TeacherQueries teacherQueries = new TeacherQueries();


    public void setAdminTeacherClearButtonClick() {
        firstnameField.clear();
        lastnameField.clear();
        teacherIdField.clear();
        mailField.clear();
        departmentField.clear();
        passField.clear();

    }

    private void setVisibleField(boolean condition) {
        teacherIdField.setVisible(condition);
        firstnameField.setVisible(condition);
        lastnameField.setVisible(condition);
        passField.setVisible(condition);
        mailField.setVisible(condition);
        departmentField.setVisible(condition);
    }

    private void disableVisibleField() {
        setVisibleField(false);
    }

    private void enableVisibleField() {
        setVisibleField(true);
    }

    public void setAdminTeacherSaveButtonClick(ActionEvent event) {
        if (isEdit == true) {
            //Todo: Update DB
        } else {
            //Todo: Add new teacher
        }
    }

    private void addTeacher() {
        String firstName = firstnameField.getText(),
                lastName = lastnameField.getText(),
                teacherId = teacherIdField.getText(),
                password = passField.getText(),
                department = departmentField.getText(),
                mail = mailField.getText();
        if (teacherId.isEmpty()) {
            showWarning("Teacher Id must not empty");
        }
        if (teacherQueries.existTeacherId(teacherId)) {
            showWarning("Teacher Id already exist");
        }
        if (firstName.isEmpty()) {
            showWarning("First name must not empty");
            return;
        }
        if (lastName.isEmpty()) {
            showWarning("Last name must not empty");
            return;
        }
        if (password.isEmpty()) password = "123";
        if (mail.isEmpty()) {
            mail = teacherId + "@hcmiu.edu.vn";
        }
        password = AuthUtil.hashString(password);
        ArrayList<Pair<String, Object>> insertValues = new ArrayList<>();
        insertValues.add(new Pair<>("First_name", firstName));
        insertValues.add(new Pair<>("Last_name", lastName));
        insertValues.add(new Pair<>("Teacher_Id", teacherId));
        insertValues.add(new Pair<>("Department", department));
        insertValues.add(new Pair<>("Mail", mail));
        insertValues.add(new Pair<>("Username", teacherId));

        teacherQueries.addNewUser(teacherId,password,"Teacher",teacherId);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        teacherIdCol.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("mail"));
        departmentCol.setCellValueFactory(new PropertyValueFactory<>("department"));

        teacherInformations = teacherQueries.getTeacherList();
        for (TeacherInformation teacher : teacherInformations) {
            teacherTrace.put(teacher.getTeacherId(), teacher);
        }
        teacherTableView.setItems(teacherInformations);
        disableVisibleField();
    }

    public void setCourseCloseButtonClick(ActionEvent event) {

    }

    public void setCourseAboutButtonClick(ActionEvent event) {
    }

    public void setAdminTeacherAddNewButtonClick(ActionEvent event) {
        enableVisibleField();
        teacherIdField.setEditable(true);
        passField.setEditable(true);
        saveButton.setText("Add");
    }

    public void setAdminTeacherEditButtonClick(ActionEvent event) {
    }

    public void setAdminTeacherDeleteButtonClick(ActionEvent event) {
    }

    public void setAdminTeacherSearchButtonClick(ActionEvent event) {
    }

    public void setAdminTeacherRefreshButtonClick(ActionEvent event) {
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
