package JavaFX;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private String url="jdbc:mysql://localhost:3306/PetStore";
    private  String username="root";
    private String password="priyanka@7849";
    private String driver="com.mysql.cj.jdbc.Driver";
    public Connection getConnection(){
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection con=null;
        try {
            con= DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return con;
    }
}
