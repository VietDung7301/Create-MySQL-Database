import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class initDB {
    public static void main(String[] args) 
    {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/DatabaseLab";
        String user = "root";
        String password = "#nothingspecial";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "CREATE TABLE KHACH_HANG ("+
                    "ID_KH VARCHAR (30) NOT NULL," +
                    "SDT_KH VARCHAR (20) NOT NULL," +
                    "TEN_KH VARCHAR (50) NOT NULL," +
                    "DIA_CHI_KH VARCHAR (100) NOT NULL," +
                    "GMAIL_KH VARCHAR (50) NOT NULL," +
                    "CONSTRAINT PK_KH PRIMARY KEY (ID_KH))";
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            System.out.println("**** Created table KHACH_HANG ****");
        } catch (SQLException e) {
            e.printStackTrace();
         } 
    }
}