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
    PriceQueries priceQueries = new PriceQueries();
    public void clearClick() {
        yearField.clear();
        semesterField.clear();
        priceField.clear();
        insuranceField.clear();

    }

    public void saveClick() {

    }

    public void init() {
        tuitionFees = priceQueries.getTuitionList();
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        semesterCol.setCellValueFactory(new PropertyValueFactory<>("semester"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("pricePerCredit"));
        insuranceCol.setCellValueFactory(new PropertyValueFactory<>("insurancePrice"));

        tuitionRecordTable.setItems(tuitionFees);

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
        priceField.setEditable(condition);
        insuranceField.setEditable(condition);
    }
    public void addNewClick() {
        setVisibleField(true);
        setEditable(true);
        saveButton.setText("Add new");
    }
}
