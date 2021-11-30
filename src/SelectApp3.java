import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author sqlitetutorial.net
 */
public class SelectApp3 {

    /**
     * Connect to the test.db database
     * 
     * @return the Connection object
     */
    static private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:src\\reto4GUI_DB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    static public String getConteo() {
        String sql = "SELECT `diagnostico`, COUNT(`diagnostico`) AS `conteo` FROM     `pacientes` GROUP BY `diagnostico` ORDER BY `conteo` DESC LIMIT 1;";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            String diagnostico = rs.getString("diagnostico");
            return diagnostico;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
    static public int getConteoSin() {
        String sql = "SELECT `diagnostico`, COUNT(`diagnostico`) AS `conteo` FROM     `pacientes` WHERE DIAGNOSTICO = 'Sin diagnostico' LIMIT    1";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            //
            ResultSet rs = pstmt.executeQuery();

            // loop through the result set
            int conteoSin = rs.getInt("conteo");
            return conteoSin;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}