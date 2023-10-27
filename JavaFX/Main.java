package JavaFX;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Hello World");
   Parent pt= FXMLLoader.load(getClass().getResource("WelComePage.fxml"));
     // Parent pt= FXMLLoader.load(getClass().getResource("CustomerPetInfo.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(pt,600,500));
        stage.show();
    }
}






