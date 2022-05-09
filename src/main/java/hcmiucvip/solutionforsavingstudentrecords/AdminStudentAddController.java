package hcmiucvip.solutionforsavingstudentrecords;

import hcmiucvip.solutionforsavingstudentrecords.core.Auth.AuthUtil;
//import hcmiucvip.solutionforsavingstudentrecords.core.DB.StudentQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.StudentQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.StudentInformation;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AdminStudentAddController implements Initializable {
    boolean isEdit = false;
    public TextField passwordField, birthdayField;
    public Button saveButton;
    private StudentQueries studentQueries = new StudentQueries();
    @FXML
    public TableView<StudentInformation> adminTableView;
    @FXML
    public TableColumn<StudentInformation, String> adminTableStudentID;
    @FXML
    public TableColumn<StudentInformation, String> adminTableFirstName, adminTableLastName, adminTableBirthday;
    @FXML
    public TableColumn<StudentInformation, String> adminTableStudentCGPA, adminTableYear;
    @FXML
    public TableColumn<StudentInformation, String> adminTableMail;
    @FXML
    public Label warningField;
    private HashMap<String, StudentInformation> studentTrace = new HashMap<>();
//    private static Connection connection = DatabaseConnectionManager.getDBConnection();
    ObservableList<StudentInformation> studentInformations;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        adminTableStudentID = new TableColumn<>("Debug");
//        adminTableStudentID.setPrefWidth(124.0);
        adminTableStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        adminTableFirstName.setCellValueFactory(new PropertyValueFactory<StudentInformation, String>("firstName"));
        adminTableLastName.setCellValueFactory(new PropertyValueFactory<StudentInformation, String>("lastName"));
        adminTableBirthday.setCellValueFactory(new PropertyValueFactory<StudentInformation, String>("day"));
        adminTableMail.setCellValueFactory(new PropertyValueFactory<StudentInformation, String>("mail"));

        adminTableYear.setCellValueFactory(new PropertyValueFactory<StudentInformation, String>("academicYear"));


        studentInformations = studentQueries.getStudentList();
        for (StudentInformation student : studentInformations) {
            System.out.println(student);
            studentTrace.put(student.getStudentId(), student);
        }
        adminTableView.setItems(studentInformations);
//        adminTableView.getItems().add(new StudentInformation("QUang","aa","aa",1,"2"));
        disableVisibleField();
    }

    public void renderStudentList() {
    }


    @FXML
    private TextField studentIDField, firstNameField, lastNameField, mailField, academicField, addressField;

    public void setAdminCloseButtonClick(ActionEvent event) {

    }

    public void setAdminCoursePanelClick() throws IOException {
        System.out.println("Cute");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CourseView.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setTitle("Course Panel");
        stage.show();
    }

    public void setAdminTeacherPanelClick() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminTeacherAdd.fxml"));
        loader.load();
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p));
        stage.setTitle("Course Panel");
        stage.show();
    }

    public void setAdminAboutButtonClick(ActionEvent event) {

    }

    public void setAdminAddNewButtonClick(ActionEvent event) {
        enableVisibleField();
        studentIDField.setEditable(true);
        passwordField.setEditable(true);
        saveButton.setText("Add");

    }

    public void setAdminSearchButtonClick(ActionEvent event) {
    }

    public void setAdminViewButtonClick(ActionEvent event) {
    }

    public void setAdminRefreshButtonClick(ActionEvent event) {
        adminTableView.refresh();
    }

    public void fillEmail() {

    }

    public void setAdminSaveButtonClick(ActionEvent event) {
        if (isEdit == true) {
            // Todo: Update DB
            System.out.println("Edit mode");
            editStudent();
        } else {
            // Todo: Add new User
            addStudent();
        }
        // Change mode
        isEdit = false;
    }
    private void addStudent() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String studentId = studentIDField.getText();
        String password = passwordField.getText();
        String email = mailField.getText();
        String strYear = academicField.getText();
        String birthday = birthdayField.getText();

        if (studentId.isEmpty()) {
            showWarning("Student Id must not empty");
            return;
        }
        if (firstName.isEmpty()) {
            showWarning("First name must not empty");
            return;
        }
        if (lastName.isEmpty()) {
            showWarning("Last name must not empty");
            return;
        }
        if (strYear.isEmpty()) {
            showWarning("Academic year must not empty");
            return;
        }
        if (studentQueries.existStudentId(studentId)) {
            showWarning("StudentId already exist");
            return;
        }
        Integer academicYear = Integer.parseInt(academicField.getText());
        studentId = studentId.toUpperCase();
        if (password.isEmpty()) {
            password = "123";
        }
        if (birthday.isEmpty()) {
            birthday = "2001-01-01";
        }
        if (email.isEmpty()) {
            email =  studentId + "@hcmiu.edu.vn";
        }
        password = AuthUtil.hashString(password);
        studentQueries.addNewUser(studentId,password,"Student",studentId);
        ArrayList<Pair<String, Object>> insertValues = new ArrayList<>();
        insertValues.add(new Pair<>("First_name", firstName));
        insertValues.add(new Pair<>("Last_name", lastName));
        insertValues.add(new Pair<>("Student_Id", studentId));
        insertValues.add(new Pair<>("Birth_day", birthday));
        insertValues.add(new Pair< >("Academic_year", academicYear));
        insertValues.add(new Pair<>("Mail", email));
        insertValues.add(new Pair<>("Username", studentId));

        if (!studentQueries.insertMultiValues(insertValues)){
            return;
        }

        StudentInformation student = new StudentInformation(studentId,firstName,lastName, academicYear,birthday, email);
        studentTrace.put(studentId, student);
        studentInformations.add(student);
        resetField();
        adminTableView.refresh();
    }
    private void editStudent() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String studentId = studentIDField.getText();
        String password = passwordField.getText();
        String email = mailField.getText();
        Integer academicYear = Integer.parseInt(academicField.getText());
        String birthday = birthdayField.getText();

        studentQueries.updateFirstName(studentId, firstName);
        studentQueries.updateLastName(studentId, lastName);
        studentQueries.updateBirthday(studentId, birthday);
        studentQueries.updateMail(studentId, email);
        studentQueries.updateAcademicYear(studentId, academicYear);
        if (!password.isEmpty()) {
            System.out.println(password);
            password = AuthUtil.hashString(password);
            studentQueries.updateUserPass(studentId, password);
        }
        System.out.println(birthday);
        StudentInformation student = studentTrace.get(studentId);
        student.setAcademicYear(academicYear);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setDay(birthday);
        student.setEmail(email);
        adminTableView.refresh();
        resetField();
        disableVisibleField();

    }

    public void setAdminClearButtonClick(ActionEvent event) {
        String studentId = studentIDField.getText();
        resetField();
        studentIDField.setEditable(true);
    }

    public void setAdminEditButtonClick(ActionEvent event) {
        enableVisibleField();
        StudentInformation studentChose = adminTableView.getSelectionModel().getSelectedItem();

        if (studentChose == null) return;
        System.out.println(studentChose);
        studentTrace.put(studentChose.getStudentId(), studentChose);
        studentIDField.setText(studentChose.getStudentId());
        studentIDField.setEditable(false);
        lastNameField.setText(studentChose.getLastName());
        firstNameField.setText(studentChose.getFirstName());
        mailField.setText(studentChose.getMail());
        academicField.setText(studentChose.getAcademicYear().toString());
        birthdayField.setText(studentChose.getDay());
        saveButton.setText("Save");

        isEdit = true;


    }

    private void resetField() {
        studentIDField.clear();
        firstNameField.clear();
        lastNameField.clear();
        passwordField.clear();
        mailField.clear();
        academicField.clear();
        birthdayField.clear();
    }
    private void setVisibleField(boolean condition) {
        studentIDField.setVisible(condition);
        firstNameField.setVisible(condition);
        lastNameField.setVisible(condition);
        passwordField.setVisible(condition);
        mailField.setVisible(condition);
        academicField.setVisible(condition);
        birthdayField.setVisible(condition);
    }
    private void disableVisibleField() {
        setVisibleField(false);
    }
    private void enableVisibleField() {
        setVisibleField(true);
    }

    public void setAdminDeleteButtonClick(ActionEvent event) {
        StudentInformation studentChose = adminTableView.getSelectionModel().getSelectedItem();
        studentQueries.deleteStudent(studentChose.getStudentId());
        studentQueries.deleteUser(studentChose.getStudentId());
        studentInformations.remove(studentChose);
        studentTrace.remove(studentChose.getStudentId());
        resetField();

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
