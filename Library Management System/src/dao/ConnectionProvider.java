package dao;
import java.sql.*;
public class ConnectionProvider {
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libray_management?useSSL=false","root","");
        return con;
    }
}
