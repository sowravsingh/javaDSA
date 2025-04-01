package DatabaseConnectivity;

import java.sql.SQLException;

public class main {
    public static void main(String[] args) throws SQLException {
        JavaPreparedStatements js = new JavaPreparedStatements();
        //js.insertUsersTable("sai",28,50000);
        System.out.println(js.getName());
    }
}
