package hcmiucvip.solutionforsavingstudentrecords.core.DB;

import hcmiucvip.solutionforsavingstudentrecords.core.Auth.AuthUtil;
import javafx.util.Pair;

import java.sql.*;
import java.util.ArrayList;

public abstract class Querier {
    protected Connection connection;

    protected String querySQL;
    protected String tableName;
    protected PreparedStatement queryStatement;

    public Querier(Connection connection) {
        this.connection = connection;
    }

    public Querier(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
    }

    /**
     * This function will get username from userID
     * If userId does not e
     *
     * @param userid
     * @return
     */
    public String getUsername(int userid) {
        return "none";
    }

    public void updateRowString(String columnKey, String userId, String columnChange, String newVal) {
        String SQL = "UPDATE %s SET %s='%s' WHERE %s='%s'";
        SQL = String.format(SQL, this.tableName, columnChange, newVal, columnKey, userId);
        System.out.println(SQL);
        runSetQuery(SQL);
    }

    public void updateRowInteger(String columnKey, String userId, String columnChange, Integer newVal) {
        String SQL = "UPDATE %s SET %s=%s WHERE %s='%s'";
        SQL = String.format(SQL, this.tableName, columnChange, newVal, columnKey, userId);
        System.out.println(SQL);
        runSetQuery(SQL);
    }

    public void updateUserPass(String username, String password) {
        System.out.println("Update Pass");
        password = AuthUtil.hashString(password);
        String SQL = "UPDATE [User] SET Password='%s' WHERE 'Username'='%s'";
        SQL = String.format(SQL, password, username);
        System.out.println(SQL);
        runSetQuery(SQL);
    }

    protected void runSetQuery(String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected ResultSet runGetQuery(String SQL) {
        try {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SQL);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void insertMultiValues(String tableName, ArrayList<Pair<String, Object>> insertValues) {
        ArrayList<Object> rows = new ArrayList<>();
        ArrayList<Object> values = new ArrayList<>();
        String commandRow = "(";
        String commandValue = "(";
        for (int i = 0; i < insertValues.size(); i++) {
            rows.add(insertValues.get(i).getKey());
            values.add(insertValues.get(i).getValue());
            commandRow = commandRow + insertValues.get(i).getKey();
            if (insertValues.get(i).getValue() instanceof String) {
                commandValue = commandValue + "'" + insertValues.get(i).getValue() + "'";
            } else {
                commandValue = commandValue + insertValues.get(i).getValue();
            }
            if (i != insertValues.size() - 1) {
                commandRow = commandRow + ",";
                commandValue = commandValue + ",";
            }
        }
        commandRow = commandRow + ")";
        commandValue = commandValue + ")";

        String SQL = "INSERT INTO " + tableName + " " + commandRow + " VALUES " +
                commandValue + ";";
        System.out.println(SQL);
        runSetQuery(SQL);
    }

    public void addNewUser(String username, String password, String userRole, String Userid) {
        String SQL = "INSERT INTO [User] VALUES ('%s','%s','%s','%s')";
        SQL = String.format(SQL, username, password, userRole, Userid);
        System.out.println(SQL);
        runSetQuery(SQL);
    }

    public boolean existUsername(String tableName, String username) {
//        String SQL = "SELECT Username FROM  %s  WHERE Username = '%s' ";
//        SQL = String.format(SQL, tableName, username);
//        System.out.println(SQL);
//        ResultSet res = runGetQuery(SQL);
//        if (res == null) return false;
//        try {
//            if (res.isBeforeFirst()) return true;
//        } catch (Exception e) {
////            e.printStackTrace();
//            return false;
//        }
//        return false;
        return existValueRows(tableName, "Username", username );
    }

    public boolean existValueRows(String tableName, String row, Object value) {

        String SQL = "SELECT Username FROM  %s  WHERE %s = '%s' ";
        if (value instanceof Integer)
            SQL = "SELECT Username FROM  %s  WHERE %s = %s ";
        SQL = String.format(SQL, tableName, row, value);
        System.out.println(SQL);
        ResultSet res = runGetQuery(SQL);
        if (res == null) return false;
        try {
            if (res.isBeforeFirst()) return true;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
        return false;

    }

    public void deleteUser(String username) {
        String SQL = String.format("DELETE FROM [User] Where Username='%s'", username);
        System.out.println(SQL);
        runSetQuery(SQL);
    }

    public boolean existUsername(String username) {
        return this.existUsername("[dbo].[User]", username);
    }
}