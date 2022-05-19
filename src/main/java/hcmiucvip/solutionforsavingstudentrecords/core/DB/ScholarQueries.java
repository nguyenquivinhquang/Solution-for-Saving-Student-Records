package hcmiucvip.solutionforsavingstudentrecords.core.DB;

import hcmiucvip.solutionforsavingstudentrecords.core.ScholarInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;

public class ScholarQueries extends Querier {
    private static Connection connection = DatabaseConnectionManager.getDBConnection();

    public ScholarQueries() {
        super(connection, "Scholarship");
    }

    public ObservableList<ScholarInformation> getScholarShip() {
        ObservableList<ScholarInformation> scholarInformations = FXCollections.observableArrayList();

        String SQL = "SELECT  [Semester]\n" +
                "      ,[Tuition]\n" +
                "      ,[Year]\n" +
                "      ,[Student_Id]\n" +
                "      ,[Scholarship_type]\n" +
                "  FROM [StudentRecord].[dbo].[Scholarship]";
        ResultSet res = runGetQuery(SQL);
        try {
            while (res.next()) {
                scholarInformations.add(new ScholarInformation(
                        res.getString("Student_Id"),
                        res.getString("Scholarship_type"),
                        res.getInt("Tuition"),
                        res.getInt("Year"),
                        res.getInt("Semester")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bug on getScholarShip");
        }
        return scholarInformations;
    }
}
