package LIS.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnection {

    public Connection conn = null;
    public PreparedStatement pstmt = null;
    public ResultSet rs = null;


    private static final DatabaseConnection instance = new DatabaseConnection();
    public static DatabaseConnection getInstance() { return instance; }

    public DatabaseConnection(){
        conn = getConnection();
    }
    public Connection getConnection() {
        System.out.println("Connection 설정");
        String dbURL = "jdbc:mysql://localhost:3306/LIS?serverTimezone=UTC";
        String dbID = "root";
        String dbPassword = "1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
