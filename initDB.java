import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class jdbc {
    public static void main(String[] args) 
    {
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/Lab01";
        String user = "root";
        String password = "#nothingspecial";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url 
                    + "?useUnicode=true&characterEncoding=utf-8", user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "show tables";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("Tables_in_Lab01"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}