import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class reto4GUI extends Application {
    public static void main(String[] args) throws Exception {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        URL ruta = getClass().getResource("Reto4GUIView.fxml");

        FXMLLoader loader = new FXMLLoader(ruta);


        Scene escena = new Scene(loader.load());

        primaryStage.setScene(escena);
        primaryStage.show();

    }
}
