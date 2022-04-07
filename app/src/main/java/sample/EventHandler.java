package sample;

import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView.*;

public class EventHandler {
    TableViewSelectionModel selectionModel = DatabaseController.tab.getSelectionModel();
     ObservableList selecedItems = selectionModel.getSelectedItems();
     void Selection ()
     {
         System.out.println(selecedItems);
     }
}
