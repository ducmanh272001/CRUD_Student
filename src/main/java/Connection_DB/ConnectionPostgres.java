package Connection_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPostgres {
    //Khai bao cac bien connect den db
    private static final String jdbc_url = "jdbc:postgresql://localhost:5433/tuyen_dung";
    private static final String user_name = "postgres";
    private static final String pass_word = "02072001";


    private ConnectionPostgres() {

    }

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(jdbc_url, user_name, pass_word);
            return con;
        } catch (ClassNotFoundException e) {
            System.out.println("Loi nap thu vien");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Sai thong tin dang nhap");
            e.printStackTrace();
        }
        return con;
    }
}
