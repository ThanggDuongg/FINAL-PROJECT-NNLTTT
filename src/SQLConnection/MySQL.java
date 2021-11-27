package SQLConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQL {
    private static String URL = "jdbc:mysql://localhost:3306/final_project_ntlttt_db";
    private static String USER = "root";
    private static String PASSWD = "Thang1407";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWD);
            System.out.println("connect successfully!");
        }
        catch (Exception e) {
            System.out.println("connect failure!");
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection(String URL, String USER, String PASSWD) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWD);
            System.out.println("connect successfully!");
        }
        catch (Exception e) {
            System.out.println("connect failure!");
            e.printStackTrace();
        }
        return conn;
    }
}
