package hcmiucvip.solutionforsavingstudentrecords;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.DatabaseConnectionManager;
import hcmiucvip.solutionforsavingstudentrecords.core.DB.QueryRole;
import javafx.util.Pair;

import java.util.ArrayList;

public class Main {
    private static final String URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=StudentRecord;"
            + "integratedSecurity=True;";
    private static final String username = "sa";
    private static final String password = "123456789";

    public static void main(String[] args) {
        DatabaseConnectionManager.init(URL, username, password);
        Windows.start(args);
//        QueryRole.debug();

    }
}

