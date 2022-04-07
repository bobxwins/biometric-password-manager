package sample;
import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;

public class EntryController implements Serializable {

    @FXML
    private   TableView <Entry> entryTable ;
    @FXML
    private   TableColumn<Entry,String> colTitel;
    @FXML
    private TableColumn<Entry,String> colUsername;
    @FXML
    private TableColumn<Entry,String> colURL;
    @FXML
    private TableColumn<Entry,String> colPassword;
    @FXML
    private TableColumn<Entry,String> colNotes;
    @FXML
    private ResourceBundle resources;
     @FXML
    private TextField tfSearch;
     private ObservableList<Entry> entryData = FXCollections.observableArrayList();
    ObjectsFXML objectsFXML = new ObjectsFXML();

   /* public EntryController () {

        entryData.add(new Entry("1","2","3","3","5"));
    }

    */
    @FXML
    void createEntry(ActionEvent event) throws Exception {
        //GetFilesFromAndroid getFilesFromAndroid = new GetFilesFromAndroid();
       //  getFilesFromAndroid.readFile();
      objectsFXML.EntryObject();
     entryTable.getItems().addAll(objectsFXML.createEntryRows(entryData));
    }

    @FXML
    void loadEntry(ActionEvent event) throws Exception {
         objectsFXML.createEntryfromFile (entryTable);
    }

    @FXML
    void deleteRow(ActionEvent event) throws  Exception {
        Object selectedItem = entryTable.getSelectionModel().getSelectedItem();
        entryTable.getItems().remove(selectedItem);
        System.out.println(selectedItem);
    }

    @FXML
    void saveEntry(ActionEvent event) throws Exception {
      //  ObservableList<Entry> entry = FXCollections.observableArrayList();
        entryData.addAll(new Entry("", "","","",""));
        ObjectsFXML.writeEntrytoFile(ObjectsFXML.passwordFileName,entryTable);
    }

    @FXML
void searchEntry(ActionEvent event) throws Exception {
String searchString =    tfSearch.getText();
    tfSearch.setOnKeyReleased(events -> {
        if (events.getCode() == KeyCode.ENTER){
            System.out.println( searchString);
          //  filter();
        }
    });
}


    @FXML
   private void initialize() throws Exception {
        colTitel.setCellValueFactory(new PropertyValueFactory<Entry, String>("titel"));
        colUsername.setCellValueFactory(new PropertyValueFactory<Entry, String>("username"));
        colURL.setCellValueFactory(new PropertyValueFactory<Entry, String>("url"));
        colPassword.setCellValueFactory(new PropertyValueFactory<Entry, String>("password"));
        colNotes.setCellValueFactory(new PropertyValueFactory<Entry, String>("Notes"));


    }
private void filter()
{
    FilteredList<Entry> filteredData = new FilteredList<>(entryData, p -> true);

    // 2. Set the filter Predicate whenever the filter changes.
    tfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(entry -> {
            // If filter text is empty, display all entries.
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            // Compare first column and last column  of every entry with filter text.
            String lowerCaseFilter = newValue.toLowerCase();

            if (entry.getTitel().getText().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches titel.
            } else

            if (entry.getUsername().getText().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches getusern
            }
            return false; // Does not match.
        });
    });

    // 3. Wrap the FilteredList in a SortedList.
    SortedList<Entry> sortedData = new SortedList<>(filteredData);

    // 4. Bind the SortedList comparator to the TableView comparator.
    sortedData.comparatorProperty().bind(entryTable.comparatorProperty());

    // 5. Add sorted (and filtered) data to the table.
    entryTable.setItems(sortedData);


}
}