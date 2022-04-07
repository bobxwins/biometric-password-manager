package sample;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class ObjectsFXML  implements Serializable  {

    String ProjectPath ="file:"+System.getProperty("user.dir");
    public static int   Y =  (int) (Screen.getPrimary().getBounds().getHeight()/2)-150;
   static String passwordFileName = System.getProperty("user.dir")+"/minfil.txt";
    public void EntryObject  () throws Exception {

        Button newEntryButton  = new Button(" New Entry ");
        Image image = new Image(String.valueOf(new File(ProjectPath+"/folder.png")));
        ImageView imgview=new ImageView(image);
        newEntryButton.setLayoutY(Y+=30);
        newEntryButton.setLayoutX(37);
        newEntryButton.setStyle("-fx-background-color: transparent;");
        newEntryButton.setGraphic(imgview);
        newEntryButton.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        newEntryButton.setOnAction(e -> {
            try{

            } catch (Exception E) {

            }
        });
        imgview.setLayoutX(37);
        imgview.setLayoutY(Y+=30);
        imgview.setFitHeight(48.9);
        imgview.setFitWidth(48.0);
        imgview.setPickOnBounds(true);
        Label entryLabel = new Label("EntryName");
        entryLabel.setLayoutX(105);
        entryLabel.setLayoutY(Y+=5);
        Main.anchorPane.getChildren().addAll(imgview,entryLabel);
        Main.anchorPane.getChildren().add(newEntryButton);
    }


   ObservableList <Entry> createEntryRows (ObservableList <Entry> entry) throws Exception
    {
     //   entry = FXCollections.observableArrayList();
        entry.addAll(new Entry("", "","","",""));
        return entry;
    }



    void createEntryfromFile  (TableView tableView) throws Exception {
        List<Entry> input ; ;
            input = readEntryFile(passwordFileName);
          tableView.getItems().clear();
           tableView.getItems().addAll(input);
    }

    public static void writeEntrytoFile(String filename, TableView tabs)
            throws IOException {
        FileWriter writer = new FileWriter(filename);
        Entry entry;
        for (int i = 0; i < tabs.getItems().size(); i++) {
            entry = (Entry) tabs.getItems().get(i);

            if (entry.getNotes().getText().length() == 0) {
                 writer.write(entry.getTitel().getText() + "," + entry.getUsername().getText() + "," +
                        entry.getUrl().getText() + ","
                        + entry.getPassword().getText() + ", " + entry.getNotes().getText() + "\n");
            }
             else {
                writer.write(
                        entry.getTitel().getText() + "," + entry.getUsername().getText() + "," +
                                entry.getUrl().getText() + ","
                                + entry.getPassword().getText() + "," + entry.getNotes().getText() + "\n");
                  }

        }
        writer.close();
    }

    private  List<Entry> readEntryFile(String filename)
            throws IOException {
        List<Entry> entry = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(Paths.get(filename));
      String line;
        while ((line = reader.readLine()) != null) {
            String[] entries = line.split(",");
           Entry entryObj = (new Entry(entries[0], entries[1],entries[2],entries[3],entries[4]));
           entryObj.getNotes();
           entry.add (entryObj);
        }

     return entry;

    }

}
