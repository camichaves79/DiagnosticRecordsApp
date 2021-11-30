
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EditApp {

    public Connection connect() {
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

    public void edit(String nombre, int cedula, String nauseas, String vomitos, String dolorAbdominal, String Diarrea,
            String fiebre, String diagnostico, int cedula2) {
        String sql = "UPDATE pacientes SET nombre=?, cedula=?, nauseas=?, vomitos=?, dolorAbdominal=?, Diarrea=?, fiebre=?, diagnostico=? WHERE Cedula ="
                + cedula2;

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, cedula);
            pstmt.setString(3, nauseas);
            pstmt.setString(4, vomitos);
            pstmt.setString(5, dolorAbdominal);
            pstmt.setString(6, Diarrea);
            pstmt.setString(7, fiebre);
            pstmt.setString(8, diagnostico);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}