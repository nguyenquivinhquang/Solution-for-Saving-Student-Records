package hcmiucvip.solutionforsavingstudentrecords;

import hcmiucvip.solutionforsavingstudentrecords.core.DB.PriceQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.TuitionFee;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.HashMap;
import java.util.Map;

public class TuitionFeeView {
    @FXML
    TextField yearField, semesterField, priceField, insuranceField;
    @FXML
    TableView<TuitionFee> tuitionRecordTable;
    @FXML
    TableColumn<String, TuitionFee> yearCol, semesterCol, priceCol, insuranceCol;
    @FXML
    Button saveButton;
    ObservableList<TuitionFee> tuitionFees;
    Map<String, TuitionFee> tuitionFeeTrace = new HashMap<>();
    PriceQueries priceQueries = new PriceQueries();

    private void resetField() {
        yearField.clear();
        semesterField.clear();
        priceField.clear();
        insuranceField.clear();
    }

    public void clearClick() {
        resetField();

    }

    public void saveClick() {
        if (saveButton.getText().equals("Add new")) {
            addNew();
        } else {
            editField();
        }
    }

    private String getHashName(Integer year, Integer semester) {
        return String.valueOf(year) + '-' + String.valueOf(semester);
    }

    public void init() {
        tuitionFees = priceQueries.getTuitionList();
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        semesterCol.setCellValueFactory(new PropertyValueFactory<>("semester"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("pricePerCredit"));
        insuranceCol.setCellValueFactory(new PropertyValueFactory<>("insurancePrice"));

        tuitionRecordTable.setItems(tuitionFees);
        for (TuitionFee tuitionFee : tuitionFees) {
            tuitionFeeTrace.put(getHashName(tuitionFee.getYear(), tuitionFee.getSemester()), tuitionFee);
        }
        setEditable(false);
        setVisibleField(false);
    }

    private void setVisibleField(boolean cond) {
        yearField.setVisible(cond);
        semesterField.setVisible(cond);
        priceField.setVisible(cond);
        insuranceField.setVisible(cond);
    }

    private void setEditable(boolean condition) {
        yearField.setEditable(condition);
        semesterField.setEditable(condition);

    }

    public void editClick() {
        TuitionFee tuitionFee = tuitionRecordTable.getSelectionModel().getSelectedItem();

        setVisibleField(true);

        yearField.setText(String.valueOf(tuitionFee.getYear()));
        semesterField.setText(String.valueOf(tuitionFee.getSemester()));
        priceField.setText(String.valueOf(tuitionFee.getPricePerCredit()));
        insuranceField.setText(String.valueOf(tuitionFee.getInsurancePrice()));
        saveButton.setText("Save");
        setEditable(false);
    }

    public void addNewClick() {
        setVisibleField(true);
        setEditable(true);
        saveButton.setText("Add new");
    }

    private void addNew() {
        TuitionFee tuitionFee = new TuitionFee();
        tuitionFee.setYear(Integer.valueOf(yearField.getText()));
        tuitionFee.setSemester(Integer.valueOf(semesterField.getText()));
        tuitionFee.setPricePerCredit(Double.valueOf(priceField.getText()));
        tuitionFee.setInsurancePrice(Double.valueOf(insuranceField.getText()));

        priceQueries.addTuition(tuitionFee.getSemester(),
                tuitionFee.getYear(),
                tuitionFee.getPricePerCredit(),
                tuitionFee.getInsurancePrice());
        tuitionFees.add(tuitionFee);
        tuitionRecordTable.refresh();
        setVisibleField(false);
    }

    private void editField() {
        Integer year = Integer.valueOf(yearField.getText());
        Integer semester = Integer.valueOf(semesterField.getText());
        Double pricePcredits = Double.valueOf(priceField.getText());
        Double insurancePrice = Double.valueOf(insuranceField.getText());

        TuitionFee tuitionFee = tuitionFeeTrace.get(yearField.getText()+'-'+semesterField.getText());


        priceQueries.updateValues(year, semester, "Price_per_credit", pricePcredits);
        priceQueries.updateValues(year, semester, "Insurance_price", insurancePrice);

        tuitionFee.setInsurancePrice(insurancePrice);
        tuitionFee.setPricePerCredit(pricePcredits);

        tuitionRecordTable.refresh();
        setVisibleField(false);
    }
}
