package hcmiucvip.solutionforsavingstudentrecords;

import hcmiucvip.solutionforsavingstudentrecords.core.DB.ScholarQueries;
import hcmiucvip.solutionforsavingstudentrecords.core.ScholarInformation;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AdminScholarshipAdd {
    boolean isEdit = false;
    @FXML
    TextField studentId, scholarType, scholarPrice, scholarYear, scholarSemester;
    @FXML
    TableView<ScholarInformation> adminScholarView;
    @FXML
    TableColumn<ScholarInformation, String> studentIdRow, scholarTypeRow, scholarPriceRow, scholarSemRow, scholarYearType;

    ScholarQueries scholarQueries = new ScholarQueries();

    ObservableList<ScholarInformation> scholarInformations;
    Stage stage;
    public void init() {
        studentIdRow.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        scholarTypeRow.setCellValueFactory(new PropertyValueFactory<>("type"));
        scholarSemRow.setCellValueFactory(new PropertyValueFactory<>("semester"));
        scholarPriceRow.setCellValueFactory(new PropertyValueFactory<>("price"));
        scholarYearType.setCellValueFactory(new PropertyValueFactory<>("year"));

        scholarInformations = scholarQueries.getScholarShip();

        adminScholarView.setItems(scholarInformations);
        isEdit = false;
    }


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
