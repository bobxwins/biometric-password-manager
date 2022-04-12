package sample;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
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
    private FilteredList <Entry>filteredData ;
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
    private TextField tfSearch;
    private ObservableList<Entry> entryData = FXCollections.observableArrayList();
    ObjectsFXML objectsFXML = new ObjectsFXML();


    @FXML
    void createEntry(ActionEvent event) throws Exception {
        entryData.add(new Entry("", "","","",""));
        objectsFXML.EntryObject();
    }

    @FXML
    void loadEntry(ActionEvent event) throws Exception {
         objectsFXML.createEntryfromFile (entryData,entryData);
    }

    @FXML
    void deleteRow(ActionEvent event) throws  Exception {
        Entry selectedItem = entryTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            entryData.remove(selectedItem);

        }
    }


    @FXML
    void saveEntry(ActionEvent event) throws Exception {
        ObjectsFXML.writeEntrytoFile(ObjectsFXML.passwordFileName,entryTable);
    }

    @FXML
   private void initialize() throws Exception {
        colTitel.setCellValueFactory(new PropertyValueFactory<Entry, String>("titel"));
        colUsername.setCellValueFactory(new PropertyValueFactory<Entry, String>("username"));
        colURL.setCellValueFactory(new PropertyValueFactory<Entry, String>("url"));
        colPassword.setCellValueFactory(new PropertyValueFactory<Entry, String>("password"));
        colNotes.setCellValueFactory(new PropertyValueFactory<Entry, String>("Notes"));

       entryTable.setItems(entryData);
        filter();
    }

    @FXML
    private void filter()
    {

        FilteredList<Entry> filteredData = new FilteredList<>(entryTable.getItems()
                , p -> true);

        tfSearch.textProperty().addListener((observable, oldValue,    newValue) -> {
            filteredData.setPredicate(entry -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // Compare first column and last column  of every entry with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (entry.getTitel().getText().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches titel.
                } else

                if (entry.getUsername().getText().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches titel.
                } else

                if (entry.getUrl().getText().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches titel.
                } else

                if (entry.getPassword().getText().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches titel.
                } else

                if (entry.getNotes().getText().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches getusern
                }
                return false; // Does not match.

            });
        });
        SortedList<Entry> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(entryTable.comparatorProperty());
        entryTable.setItems(sortedData);
    }



}