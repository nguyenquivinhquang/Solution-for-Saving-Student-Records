package hcmiucvip.solutionforsavingstudentrecords.core.DB;

import java.sql.*;

public class QueryRole {
    private static Connection connection = DatabaseConnectionManager.getDBConnection();

    public static void query() {
        String username = "123";
        try {
            String SQL = "SELECT * FROM  [dbo].[User]  WHERE Username = '%s' ";
            SQL = String.format(SQL, username);
//                String SQL = "SELECT Password FROM \"User\" WHERE Username = ?";
            System.out.println(SQL);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SQL);
            if (!res.isBeforeFirst())
                return;
            System.out.println("debug");
            while (res.next()) {
                System.out.println(res.getString("UserId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(String.format("something %s", "name"));
    }

    public static void debug() {
        StudentQueries infor = new StudentQueries();
        infor.getStudentList();
    }
}
