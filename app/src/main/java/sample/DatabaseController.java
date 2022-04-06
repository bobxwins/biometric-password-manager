package sample;
import java.io.File;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class DatabaseController implements Serializable {

    @FXML

  public  static TableView  tab = new TableView ();

    private int   button1ClickCount = 0;
    int   y =  (int) Screen.getPrimary().getBounds().getHeight()/2;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tfPassword;

    @FXML
    private TableColumn<Entry, String> colTitel;

    @FXML
    private TableColumn<Entry, String> colPwd;

    @FXML
    private Label labelEntry;

    @FXML
    public  ImageView imgFolder;

    @FXML
    private TextField tfTitel;

    @FXML
    private TextField tfUsername;

    @FXML
    public Button btnCreate;

    @FXML
    private TableColumn<Entry, String> colUserName;

    @FXML
    private TextField tfURL;

    @FXML
    private TextField tfNotes;

    @FXML
    private TableColumn<Entry, String> colURL;

    @FXML
    private Button btnOpenEntry;

    @FXML
    private TableColumn<Entry, String> colNotes;

    @FXML
    private Button btnDelete;

    @FXML
    private TextField tfSearch;



    TableColumn UsernameColumn = new TableColumn("username");

    TableColumn TitelColumn = new TableColumn("titel");

    TableColumn URLColumn = new TableColumn("url");

    TableColumn PasswordColumn = new TableColumn("password");

    TableColumn NotesColumn = new TableColumn("notes");



    @FXML
    void createEntry(ActionEvent event) throws Exception {
        //GetFilesFromAndroid getFilesFromAndroid = new GetFilesFromAndroid();
       //  getFilesFromAndroid.readFile();

   ObjectsFXML objectsFXML = new ObjectsFXML();
      objectsFXML.EntryObject();

    }
    @FXML
    void loadEntry(ActionEvent event) throws Exception {
        ObjectsFXML objectsFXML = new ObjectsFXML();
         objectsFXML.createEntryfromFile ();
    }

    @FXML
    void saveEntry(ActionEvent event) throws Exception {
        ObservableList<Entry> entry = FXCollections.observableArrayList();
        entry.addAll(new Entry("", "","","",""));
        ObjectsFXML.writeEntrytoFile(ObjectsFXML.passwordFileName,entry);
    }

    @FXML
    void initialize() {
        tab.setLayoutX(432);
        tab.setLayoutY(45);
        UsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        TitelColumn.setCellValueFactory(new PropertyValueFactory<>("titel"));
        URLColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        PasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        NotesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
        tab.getColumns().addAll(TitelColumn,UsernameColumn,URLColumn,PasswordColumn,NotesColumn);
        assert tfPassword != null : "fx:id=\"tfPassword\" was not injected: check your FXML file 'pmlayer2.fxml'.";
        assert colTitel != null : "fx:id=\"colTitel\" was not injected: check your FXML file 'pmlayer2.fxml'.";
        assert colPwd != null : "fx:id=\"colPwd\" was not injected: check your FXML file 'pmlayer2.fxml'.";
        assert labelEntry != null : "fx:id=\"labelEntry\" was not injected: check your FXML file 'pmlayer2.fxml'.";
        assert imgFolder != null : "fx:id=\"imgFolder\" was not injected: check your FXML file 'pmlayer2.fxml'.";
        assert tfTitel != null : "fx:id=\"tfTitel\" was not injected: check your FXML file 'pmlayer2.fxml'.";
        assert tfUsername != null : "fx:id=\"tfUsername\" was not injected: check your FXML file 'pmlayer2.fxml'.";
        assert btnCreate != null : "fx:id=\"btnCreate\" was not injected: check your FXML file 'pmlayer2.fxml'.";
        assert colUserName != null : "fx:id=\"colUserName\" was not injected: check your FXML file 'pmlayer2.fxml'.";
        assert tfURL != null : "fx:id=\"tfURL\" was not injected: check your FXML file 'pmlayer2.fxml'.";
        assert tfNotes != null : "fx:id=\"tfNotes\" was not injected: check your FXML file 'pmlayer2.fxml'.";
        assert colURL != null : "fx:id=\"colURL\" was not injected: check your FXML file 'pmlayer2.fxml'.";
        assert btnOpenEntry != null : "fx:id=\"btnOpenEntry\" was not injected: check your FXML file 'pmlayer2.fxml'.";
        assert colNotes != null : "fx:id=\"colNotes\" was not injected: check your FXML file 'pmlayer2.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'pmlayer2.fxml'.";
        assert tfSearch != null : "fx:id=\"tfSearch\" was not injected: check your FXML file 'pmlayer2.fxml'.";


    }

}