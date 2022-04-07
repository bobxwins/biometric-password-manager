package sample;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;

import javafx.stage.Stage;

import java.net.URL;
import java.lang.*;

public class Main extends Application   {

    @FXML
    public static AnchorPane anchorPane = new AnchorPane();
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        EntryController entryController = new EntryController();
        FXMLLoader loader = new FXMLLoader();
       loader.setController(entryController);
      String ProjectPath = System.getProperty("user.dir")+"/pmlayerAuthenticated.fxml";
       loader.setLocation(new URL(   "file:///"+ProjectPath));
       anchorPane = loader.<AnchorPane>load();

        Scene scene = new Scene( anchorPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
