package content;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("interface.fxml")),753, 844);
        primaryStage.setTitle("Java-Arena");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
         launch(Main.class, args);
    }


}
