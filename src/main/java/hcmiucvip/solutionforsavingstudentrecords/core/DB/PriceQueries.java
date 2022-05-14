package hcmiucvip.solutionforsavingstudentrecords.core.DB;

import hcmiucvip.solutionforsavingstudentrecords.core.TuitionFee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;

public class PriceQueries extends Querier {
    private static Connection connection = DatabaseConnectionManager.getDBConnection();

    public PriceQueries() {
        super(connection);
    }

    public ObservableList<TuitionFee> getTuitionList() {
        ObservableList<TuitionFee> tuitionFees = FXCollections.observableArrayList();

        try {
            String SQL = "SELECT [Price_per_credit]\n" +
                    "      ,[Semeter]\n" +
                    "      ,[Year]\n" +
                    "      ,[Insurance_price]\n" +
                    "  FROM [StudentRecord].[dbo].[Tuition_fee]";
            ResultSet res = runGetQuery(SQL);
            if (!res.isBeforeFirst()) return tuitionFees;
            while (res.next()) {
                tuitionFees.add(new TuitionFee(
                        res.getInt("Semeter"),
                        res.getInt("Year"),
                        res.getDouble("Price_per_credit"),
                        res.getDouble("Insurance_price")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bug on getTuitionList");
        }

        return tuitionFees;
    }

    public void addTuition(Integer semester, Integer year,
                           Double pricePerCredit, Double insurancePrice) {
        String SQL = "insert into [Tuition_fee](Year, Semeter, Price_per_credit, Insurance_price)\n" +
                "Values (%s,%s,%s,%s);";
        SQL = String.format(SQL, year, semester, pricePerCredit, insurancePrice);
        runSetQuery(SQL);

    }
    public void updateValues(Integer year, Integer semester, String columnChange, Double newVal) {
        String SQL = "UPDATE %s SET %s=%s WHERE Year=%s and Semeter=%s";
        SQL = String.format(SQL, "Tuition_fee", columnChange, newVal, year, semester);
        System.out.println(SQL);
        runSetQuery(SQL);
    }
}
