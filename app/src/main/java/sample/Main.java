package sample;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;

import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.Serializable;
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

      //  int screenWidth = (int) Screen.getPrimary().getBounds().getWidth();
   //     int screenHeight = (int) Screen.getPrimary().getBounds().getHeight();
        DatabaseController databaseController = new DatabaseController();
        FXMLLoader loader = new FXMLLoader();
       loader.setController(databaseController);
      String ProjectPath = System.getProperty("user.dir")+"/pmlayer2.fxml";
       loader.setLocation(new URL(   "file:///"+ProjectPath));
       anchorPane = loader.<AnchorPane>load();
        anchorPane.getChildren().add(DatabaseController.tab);
        Scene scene = new Scene( anchorPane);
        primaryStage.setScene(scene);
        primaryStage.show();

        DatabaseController.tab.setOnMouseClicked(e->{
            EventHandler eventHandler = new EventHandler();
            eventHandler.Selection();
        });


    }
}
