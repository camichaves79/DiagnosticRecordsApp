import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.*;

public class Reto4GUIControlador {

    Connection con;
    Connection cn;
    Statement st;
    ResultSet rs;

    private void mensaje(String titulo, String texto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(titulo);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    private void confirmarEliminacion() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText("¿Seguro que desea eliminar?");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK){
            EliminarCampos();
        }
    }

    private void ingresarDatos() {
        try {

            int ced = Integer.parseInt(txtIngreCedula.getText());
            String cedStr = txtIngreCedula.getText();
            String nom = txtIngreNombre.getText();
            String nau, vom, dol, dia, fie, diagnostico;

            if (cbxIngreNauseas.isSelected()) {
                nau = "si";
            } else {
                nau = "no";
            }

            if (cbxIngreVomitos.isSelected()) {
                vom = "si";
            } else {
                vom = "no";
            }

            if (cbxIngreDolor.isSelected()) {
                dol = "si";
            } else {
                dol = "no";
            }

            if (cbxIngreDiarrea.isSelected()) {
                dia = "si";
            } else {
                dia = "no";
            }

            if (cbxIngreFiebre.isSelected()) {
                fie = "si";
            } else {
                fie = "no";
            }

            Paciente paciente = new Paciente(nau, vom, dol, dia, fie, nom, cedStr);
            diagnostico = paciente.diagnosticar();

            InsertApp app = new InsertApp();

            app.insert(nom, ced, nau, vom, dol, dia, fie, diagnostico);

            mensaje("Registro", "Paciente registrado con exito.");

            txtIngreCedula.setText("");
            txtIngreNombre.setText("");
            cbxIngreNauseas.setSelected(false);
            cbxIngreVomitos.setSelected(false);
            cbxIngreDolor.setSelected(false);
            cbxIngreDiarrea.setSelected(false);
            cbxIngreFiebre.setSelected(false);

        } catch (Exception e) {

            mensaje("Error", "Verifique la informacion e intente de nuevo" );
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void obtenerDatos2() {
        String longString = SelectApp.selectAll();
        txtProDatosRegistros.setText("");
        txtProDatosRegistros.setText(longString);
    }

    private void procesarDatos2() {
        String longString2 = SelectApp2.selectAll();
        String diaString = SelectApp3.getConteo();
        int conteoSinDia = SelectApp3.getConteoSin();
        txtProDatosSalidas.setText("");
        txtProDatosSalidas.setText(longString2 + diaString + "\n" + conteoSinDia);
        if (longString2.equals("")){
            txtProDatosSalidas.setText("");
        }
    }

    private void buscarRegistros() {
        try {
            int cedula = Integer.parseInt(txtEdiEliCedula.getText());
            String longString3 = BuscarApp.selectAll(cedula);
            String camposArray[] = longString3.split("-");
            
            txtEdiEliNombre.setText(camposArray[0]);
            txtEdiEliCedula2.setText(camposArray[1]);
            CheckBox sintomas[] = { cbxEdiEliNauseas, cbxEdiEliVomitos, cbxEdiEliDolor, cbxEdiEliDiarrea,
                    cbxEdiEliFiebre };
            for (int i = 0; i < 5; i++) {

                if (camposArray[i + 2].equals("si")) {
                    sintomas[i].setSelected(true);
                
                } else {
                    sintomas[i].setSelected(false);
                
                }
            }
        } catch (Exception e) {

            mensaje("Error", "Verifique la informacion e intente de nuevo");
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void EditarDatos() {
        try {

            int ced = Integer.parseInt(txtEdiEliCedula2.getText());
            int cedula2 = Integer.parseInt(txtEdiEliCedula.getText());
            String cedStr = txtEdiEliCedula2.getText();
            String nom = txtEdiEliNombre.getText();
            String nau, vom, dol, dia, fie, diagnostico;

            if (cbxEdiEliNauseas.isSelected()) {
                nau = "si";
            } else {
                nau = "no";
            }

            if (cbxEdiEliVomitos.isSelected()) {
                vom = "si";
            } else {
                vom = "no";
            }

            if (cbxEdiEliDolor.isSelected()) {
                dol = "si";
            } else {
                dol = "no";
            }

            if (cbxEdiEliDiarrea.isSelected()) {
                dia = "si";
            } else {
                dia = "no";
            }

            if (cbxEdiEliFiebre.isSelected()) {
                fie = "si";
            } else {
                fie = "no";
            }

            Paciente paciente = new Paciente(nau, vom, dol, dia, fie, nom, cedStr);
            diagnostico = paciente.diagnosticar();

            EditApp app = new EditApp();

            app.edit(nom, ced, nau, vom, dol, dia, fie, diagnostico, cedula2);

            mensaje("Registro", "Paciente editado con exito.");
            txtEdiEliCedula.setText("");
            txtEdiEliCedula2.setText("");
            txtEdiEliNombre.setText("");
            cbxEdiEliNauseas.setSelected(false);
            cbxEdiEliVomitos.setSelected(false);
            cbxEdiEliDolor.setSelected(false);
            cbxEdiEliDiarrea.setSelected(false);
            cbxEdiEliFiebre.setSelected(false);

        } catch (Exception e) {

            mensaje("Error", "Verifique la informacion e intente de nuevo");
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void EliminarCampos() {
        try {

            DeleteApp app = new DeleteApp();
            
            int cedula = Integer.parseInt(txtEdiEliCedula.getText());
            app.delete(cedula);
            

            mensaje("Registro", "Paciente eliminado con exito.");
  
            txtEdiEliCedula.setText("");
            txtEdiEliCedula2.setText("");
            txtEdiEliNombre.setText("");
            cbxEdiEliNauseas.setSelected(false);
            cbxEdiEliVomitos.setSelected(false);
            cbxEdiEliDolor.setSelected(false);
            cbxEdiEliDiarrea.setSelected(false);
            cbxEdiEliFiebre.setSelected(false);

        } catch (Exception e) {

            mensaje("Error", "Verifique la informacion e intente de nuevo");
            System.out.println("Error: " + e.getMessage());
        }
    }

    @FXML
    private MenuBar txtIngresarNombre;

    @FXML
    private TextField txtIngreNombre;

    @FXML
    private TextField txtIngreCedula;

    @FXML
    private CheckBox cbxIngreNauseas;

    @FXML
    private CheckBox cbxIngreVomitos;

    @FXML
    private CheckBox cbxIngreDolor;

    @FXML
    private CheckBox cbxIngreDiarrea;

    @FXML
    private CheckBox cbxIngreFiebre;

    @FXML
    private Button btnIngreIngresar;

    @FXML
    private TextArea txtProDatosRegistros;

    @FXML
    private TextArea txtProDatosSalidas;

    @FXML
    private Button btnProDatosObtener;

    @FXML
    private Button btnProDatosProcesar;

    @FXML
    private TextField txtEdiEliNombre;

    @FXML
    private TextField txtEdiEliCedula2;

    @FXML
    private CheckBox cbxEdiEliNauseas;

    @FXML
    private CheckBox cbxEdiEliVomitos;

    @FXML
    private CheckBox cbxEdiEliDolor;

    @FXML
    private CheckBox cbxEdiEliDiarrea;

    @FXML
    private CheckBox cbxEdiEliFiebre;

    @FXML
    private Button btnEdiEliBuscar;

    @FXML
    private TextField txtEdiEliCedula;

    @FXML
    private Button btnEdiEliEditar;

    @FXML
    private Button btnEdiEliEliminar;

    @FXML
    private Button btnEdiEliLimpiar;


    @FXML
    void IngresarDatos(ActionEvent event) {

        ingresarDatos();
    }

    @FXML
    void obtenerDatos(ActionEvent event) {
        obtenerDatos2();
    }

    @FXML
    void procesarDatos(ActionEvent event) {
        procesarDatos2();
    }

    @FXML
    void buscar(ActionEvent event) {
        buscarRegistros();
    }

    @FXML
    void Editar(ActionEvent event) {
        EditarDatos();
    }

    @FXML
    void EliminarCampo(ActionEvent event) {
        confirmarEliminacion();
    }

    @FXML
    void quitApp(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        txtEdiEliCedula.setText("");
        txtEdiEliCedula2.setText("");
        txtEdiEliNombre.setText("");
        cbxEdiEliNauseas.setSelected(false);
        cbxEdiEliVomitos.setSelected(false);
        cbxEdiEliDolor.setSelected(false);
        cbxEdiEliDiarrea.setSelected(false);
        cbxEdiEliFiebre.setSelected(false);

    }

}