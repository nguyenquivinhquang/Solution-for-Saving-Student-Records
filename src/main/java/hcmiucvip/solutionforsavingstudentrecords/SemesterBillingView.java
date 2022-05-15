package hcmiucvip.solutionforsavingstudentrecords;

import hcmiucvip.solutionforsavingstudentrecords.core.DB.CourseQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.EnrolledCourseQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.PriceQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.SchoolYear;
import hcmiucvip.solutionforsavingstudentrecords.core.SemesterBilling;
import hcmiucvip.solutionforsavingstudentrecords.core.TuitionFee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SemesterBillingView {
    String studentId;
    @FXML
    Text studentIdField;
    @FXML
    TableView<SemesterBilling> semesterBillingTable;
    @FXML
    TableColumn<SemesterBilling, String> yearRow, semesterRow, creditsRow, insurancePriceRow, totalRow;
    Stage stage;
    PriceQueries priceQueries = new PriceQueries();
    EnrolledCourseQueries enrolledCourseQueries = new EnrolledCourseQueries();
    ObservableList<TuitionFee> tuitionFees;
    ObservableList<SemesterBilling> semesterBillings = FXCollections.observableArrayList();;

    public void init() {
        tuitionFees = priceQueries.getTuitionList();
        for (TuitionFee tuitionFee : tuitionFees) {
            String year = String.valueOf(tuitionFee.getYear());
            String semester = String.valueOf(tuitionFee.getSemester());
            int totalCredits = enrolledCourseQueries.getTotalSemesterYearCredits(studentId, year + '-' + semester);

            System.out.println(totalCredits);
            if (totalCredits == 0) continue;
            semesterBillings.add(new SemesterBilling(tuitionFee.getYear(),
                    tuitionFee.getSemester(),
                    totalCredits,
                    tuitionFee.getPricePerCredit(),
                    tuitionFee.getInsurancePrice(), (double) 0));
        }
        yearRow.setCellValueFactory(new PropertyValueFactory<>("year"));
        semesterRow.setCellValueFactory(new PropertyValueFactory<>("semester"));
        creditsRow.setCellValueFactory(new PropertyValueFactory<>("credits"));
        insurancePriceRow.setCellValueFactory(new PropertyValueFactory<>("insurancePrice"));
        totalRow.setCellValueFactory(new PropertyValueFactory<>("total"));
//
        semesterBillingTable.setItems(semesterBillings);
        studentIdField.setText(studentId);
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
        tuitionFees = priceQueries.getTuitionList();

    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void closeClick() {
        this.stage.close();
    }
}

