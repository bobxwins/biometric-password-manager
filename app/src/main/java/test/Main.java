package test;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.lang.*;

import javafx.scene.text.Text;

// This program is based on a program from http://tutorials.jenkov.com/javafx/filechooser.html
public class Main extends Application {
    byte[] array = new byte[100];
  public static int num7;
    public static String PassText;
    public static String SignatureText;
   static TextField SignatureField = new  TextField ();
    Boolean isDecrypting;
    // Den fil som kryptering eller dekrypterings funktionen vælger

  //  public static String Mode = "";
   // String som afgører om password skal kryptere eller dekryptere

     public static Text text []  = {new Text(),new Text()};
 // text er public og er et static array af tekst object,
    // således at 2 tekster med forskelige farver kan bruges samtidigt.
    // den er static således at den tidligere tekst slettes hver gang der kommer
    // en ny besked fra programmet som fx en fejl

    //lokationen hvor at progrmmet åbnes i windows explorer
    public void start(Stage primaryStage) throws Exception {
        Stage anotherStage = new Stage();
        primaryStage.setTitle("File Encrypter");

        Button PasswordButton = new Button("Set Password");
        Button GeneratePassword = new Button("Generate Password");
        Button VerifySignatureButton = new Button("Verify Signature");


        Button Encrypt = new Button("Encrypt file");

        Button Decrypt = new Button("Decrypt file");
        // setonAction er en event handler der håndtere buttons

        Encrypt.setOnAction(e -> {

                isDecrypting = false;
                Decrypt.setDisable(false);
                Encrypt.setDisable(true);
                //   Mode = "Encryption";
                text[1].setFill(Color.GREEN);
                text[0].setFill(Color.PALEVIOLETRED);
                text[0].setText("Please enter password to complete encryption!");


        });
        Decrypt.setOnAction(e -> {
           isDecrypting = true;
            Decrypt.setDisable(true);
            Encrypt.setDisable(false);

                text[1].setFill(Color.GREEN);
                text[0].setFill(Color.PALEVIOLETRED);

                text[0].setText("Please enter password to complete decryption!");

            });

        PasswordField passwordfield = new  PasswordField ();

        PasswordButton.setOnAction(e -> {
            PassText = String.valueOf(passwordfield.getText());
            // Det der indtastest i password field er PassText's værdi, starter med at være tom

            passwordfield.setText("");
            if (isDecrypting==false) {
                Encrypt encrypt = new Encrypt();
                encrypt.SymmetricKeyGenerator();
            }
            else {
                Decrypt decrypt = new Decrypt();
                 decrypt.DecryptFile();}
        });


        //Setting font to the text
        text[0].setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));


        //Setting the text to be added.
        text[0].setText("");


        text[1].setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 16));


        text[1].setText("");

        Label Passwordlabel = new Label("Password:");
        Label SignatureLabel = new Label("Signature:");

        HBox hbox []  = {new HBox(),new HBox()};


        hbox [1] .getChildren().addAll(SignatureLabel,SignatureField,GeneratePassword,VerifySignatureButton,Passwordlabel,passwordfield,PasswordButton);
// der laves 2 hbox, hvori at password og signatur elementer er øverst og resten er nederst
        hbox [1] .setSpacing(10);



        hbox [0].setSpacing(10);


        GridPane gridpane = new GridPane();

        hbox [0].getChildren().addAll( Encrypt,Decrypt);

        // de resterende knapper nederst


        gridpane.setVgap(50);

        // den lodrette afstand mellem den øverste og nederste HBOX

        gridpane.add( hbox [0], 0,1 , 1, 1);
        gridpane.add(hbox [1] , 0, 0, 1, 1);
        gridpane.add( text[0], 0, 2, 1, 2);
        gridpane.add( text[1], 0, 2, 1, 1);

        ColumnConstraints col = new ColumnConstraints();
        col.setHalignment(HPos.CENTER);


        gridpane.getColumnConstraints().add(col);

        // sørger for al Tekst i Gridpanen  er centreret


        Scene scene = new Scene(gridpane, 1000, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
