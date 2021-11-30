import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sqlitetutorial.net
 */
public class SelectApp {

    /**
     * Connect to the test.db database
     * 
     * @return the Connection object
     */
    private static Connection connect() {
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

    /**
     * select all rows in the warehouses table
     */
    public static String selectAll() {
        String sql = "SELECT nombre, cedula, nauseas, vomitos, dolorabdominal, diarrea, fiebre FROM pacientes";
        String tempString = "";
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                tempString = tempString + (rs.getString("nombre") + "-" + rs.getInt("cedula") + "-"
                        + rs.getString("nauseas") + "-" + rs.getString("vomitos") + "-" + rs.getString("dolorabdominal")
                        + "-" + rs.getString("diarrea") + "-" + rs.getString("fiebre") + "\n");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tempString;
    }

}