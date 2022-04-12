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
        //EntryController entryController = new EntryController();
        LoginController loginController = new LoginController();
        FXMLLoader loader = new FXMLLoader();
       loader.setController(loginController);
      String ProjectPath = System.getProperty("user.dir")+"/login.fxml";
      // String ProjectPath = System.getProperty("user.dir")+"/pmlayerAuthenticated.fxml";
      loader.setLocation(new URL(   "file:///"+ProjectPath));

        //loader.setLocation(getClass().getResource("/FXML/pmlayerAuthenticated.fxml"));

       /* String test = Main.class.getResource("/FXML/pmlayerAuthenticated.fxml").getFile();
        String test2= Main.class.getResource("/FXML/pmlayerAuthenticated.fxml").toString();
        System.out.println(test+test2); */
       anchorPane = loader.<AnchorPane>load();

        Scene scene = new Scene( anchorPane);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
