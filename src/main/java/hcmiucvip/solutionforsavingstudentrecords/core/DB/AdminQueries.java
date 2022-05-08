package hcmiucvip.solutionforsavingstudentrecords.core.DB;

import java.sql.Connection;

public class AdminQueries extends Querier{
    public AdminQueries(Connection connection) {
        super(connection,"Administrator");
    }

}
