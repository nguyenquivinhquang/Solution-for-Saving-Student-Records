package hcmiucvip.solutionforsavingstudentrecords;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class AddStudentController implements Initializable {
    public RadioButton maleButton;
    public RadioButton femaleButton;
    public ToggleGroup Gender;

    @FXML
    private Button submitButton;
    @FXML
    private Pane rootPane;
    @FXML
    private TextField usrField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField studentIdField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField academicField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField countryField;

    @FXML
    private TextField dayField;

    @FXML
    private TextField monthField;

    @FXML
    private TextField yearField;

    @FXML
    private TextArea aboutField;

    @FXML
    private Label usrFieldWarning, emailFieldWarning, passwdFieldWarning, confirmPasswdFieldWarning;

    public void verifyDay() {
        if (!dayField.getText().matches("\\d*")) {
            dayField.setText(dayField.getText().replaceAll("\\D", ""));
            dayField.positionCaret(dayField.getText().length());
        }
        if (!dayField.getText().equals(""))
            if (Integer.parseInt(dayField.getText()) > 31)
                dayField.deletePreviousChar();
    }

    public void verifyMonth() {
        if (!monthField.getText().matches("\\d*")) {
            monthField.setText(monthField.getText().replaceAll("\\D", ""));
            monthField.positionCaret(monthField.getText().length());
        }
        if (!monthField.getText().equals(""))
            if (Integer.parseInt(monthField.getText()) > 12)
                monthField.deletePreviousChar();
    }

    public void verifyYear() {
        if (!yearField.getText().matches("\\d*")) {
            yearField.setText(yearField.getText().replaceAll("\\D", ""));
            yearField.positionCaret(yearField.getText().length());
        }
        if (!yearField.getText().equals(""))
            if (Integer.parseInt(yearField.getText()) > 2020)
                yearField.deletePreviousChar();
    }

    private String gender = "";
    private String studentId = "";
    private String birthDay = "";

    public void submitButton() {
        gender = maleButton.isSelected() ? "male" : "female";
        System.out.println(gender);
        birthDay = yearField.getText() + '-' + monthField.getText() + '-' + dayField.getText();
        Timestamp DOB = null;
        if (birthDay.matches("\\d{1,4}-\\d{1,2}-\\d{1,2}"))
            DOB = Timestamp.valueOf(birthDay + " 00:00:00.000000000");
        System.out.println(DOB);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void uppperCaseStudentId() {
        System.out.println(studentIdField.getText());
        studentIdField.setText(studentIdField.getText().toUpperCase());
    }

    public void setEmail() {
        emailField.setText(studentIdField.getText().toUpperCase() +
                                        "@student.hcmiu.edu.vn");
    }

}
