module hcmiucvip.solutionforsavingstudentrecords {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens hcmiucvip.solutionforsavingstudentrecords to javafx.fxml;
    exports hcmiucvip.solutionforsavingstudentrecords;
    exports hcmiucvip.solutionforsavingstudentrecords.core;
    opens hcmiucvip.solutionforsavingstudentrecords.core to javafx.fxml;
//    exports hcmiucvip.solutionforsavingstudentrecords.core.controller;
//    opens hcmiucvip.solutionforsavingstudentrecords.core.controller to javafx.fxml;
}