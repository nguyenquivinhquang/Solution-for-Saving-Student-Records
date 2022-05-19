package hcmiucvip.solutionforsavingstudentrecords.core.Auth;

import hcmiucvip.solutionforsavingstudentrecords.core.DB.DatabaseConnectionManager;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserAuthenticator {
    private static Connection connection = DatabaseConnectionManager.getDBConnection();

    /**
     * @param username
     * @param password
     * @return If password is correct return Pair<UserId,UserRole>
     * else: return Pair<"none", "none">;
     */
    public static Pair<String, String> auth(String username, String password) {
        // Todo: Remove default pass
//        username = "admin_quang";
//        password = "cvip123";
        Pair<String, String> result = new Pair<>("none", "none");
        username = username.toLowerCase();
        try {
            String SQL = String.format("SELECT * FROM  [dbo].[User]  WHERE Username = '%s' ",
                    username);
            System.out.println(SQL);
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery(SQL);

            if (!res.isBeforeFirst()) return result; // does not exist username
            res.next();
            String truePassword = res.getString("Password").trim();

            System.out.println(password);
            System.out.println(truePassword);
            System.out.println(AuthUtil.hashString(password));
            if (!AuthUtil.hashString(password).trim().equals(truePassword)
                    &&!password.trim().equals(truePassword)) {
                SQL = String.format("SELECT * FROM  [dbo].[User]  WHERE Username = '%s' ",
                        username.toUpperCase());
                statement = connection.createStatement();
                res = statement.executeQuery(SQL);
                if (!res.isBeforeFirst()) return result; // does not exist username
                res.next();
                truePassword = res.getString("Password").trim();
                if (!AuthUtil.hashString(password).trim().equals(truePassword))
                        return result;
                result = new Pair<>(username.toUpperCase(), res.getString("Role").trim());
                return result;
            }

            result = new Pair<>(username.toLowerCase(), res.getString("Role").trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Log in success");
        return result;
    }
}
