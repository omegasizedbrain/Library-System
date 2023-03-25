package LibrarySystem;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BookSearch extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) throws FileNotFoundException{

    GridPane pane = new GridPane();
    TextField txtSearch = new TextField("Search");
    Button BTSearch = new Button("Search");
    
    File file = new File("BackupInventory.txt");
    Library inv = new Library(file);
    Text result = new Text(setText(inv));
    SearchButton search = new SearchButton(txtSearch, result, inv.getInv());
    
    
    pane.setPadding(new Insets(10, 10, 10, 10));
    txtSearch.setOnAction(search);
    BTSearch.setOnAction(search);
    pane.add(new Text("Book Search System"), 0, 0);
    pane.add(txtSearch, 0, 1);
    pane.add(BTSearch, 1, 1);
    pane.add(result, 6, 2);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 500, 500);
    primaryStage.setTitle("Book Search & Storage System"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }
  
  public static void main(String[] args) {
    launch(args);
  }

  public static String setText(Library inv){
    String result = "";
    for(int i = 0; i < inv.inventory.size(); i++) {
			result+= inv.getInv().get(i).toString() + "\n";
		}	
    return result;
  }
}

class SearchButton implements EventHandler<ActionEvent>{

    TextField txtSearch;
    Text result;
    ArrayList<Book> list;

    SearchButton(TextField txtSearch, Text result, ArrayList<Book> list){

        this.list = list;
        this.txtSearch = txtSearch;
        this.result = result;

    }

@Override
public void handle(ActionEvent e){

        result.setText("");
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).toString().toLowerCase()
            .contains(txtSearch.getText().toLowerCase())){
              
               result.setText(result.getText() + "\n" + list.get(i).toString());
            }
        }
    }

}
