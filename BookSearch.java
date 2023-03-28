package LibrarySystem;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class BookSearch extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) throws FileNotFoundException{

      // Created Panes
    StackPane pane = new StackPane();
    ScrollPane pane2 = new ScrollPane();
    Pane pane3 = new Pane();
    Pane pane4 = new Pane();

    // Created Nodes to place within Panes
    Text title = new Text("Book Search System");
    TextField txtSearch = new TextField("Search");
    Button BTSearch = new Button("Search");
    Button BTAdd = new Button("Add");
    Button BTRefresh = new Button("Refresh");
    Button BTSave = new Button("Save & Quit");
    File file = new File("Inventory.txt");
    Library inv = new Library(file);
    Text result = new Text(inv.setText());

    // Added actions to the nodes
    pane.setPadding(new Insets(10, 10, 10, 10));
    txtSearch.setOnAction(e -> search(txtSearch, result, inv.getInv())); // Allows you to press enter after typing to search
    BTSearch.setOnAction(e -> search(txtSearch, result, inv.getInv())); // Allows you to press the search button when done typing
    BTAdd.setOnAction(e -> {
        Stage newStage = createNewStage(inv); // Creates a new stage with the features defined in the createNewStage class
        newStage.show(); // Displays the Stage
    });
    BTRefresh.setOnAction(e -> {
            result.setText(inv.setText()); // Redraws the results in the Textbox using the ArrayList
    });
    BTSave.setOnAction(e ->{
        try {
            inv.exportInventory(file); // Exports the new and old books to the file
            primaryStage.close(); // Closes the stage
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    });
    // set locations of the nodes within the panes
    BTAdd.setTranslateY(50);
    BTAdd.setTranslateX(0);
    BTRefresh.setTranslateX(50);
    BTRefresh.setTranslateY(50);
    BTSave.setTranslateY(460);
    title.setTranslateY(10);
    result.setX(200);
    result.setY(10);
    txtSearch.setTranslateY(20);
    BTSearch.setTranslateY(20);
    BTSearch.setTranslateX(100);

    // set locations and parameters of the Panes
    pane2.setTranslateX(100);
    pane2.setFitToWidth(true);
    pane2.setFitToHeight(true);
    pane2.setMaxWidth(300);
    pane2.setContent(result);

    // Add Nodes to their respective panes
    pane3.getChildren().addAll(title,txtSearch,BTSearch);
    pane4.getChildren().addAll(BTAdd,BTRefresh,BTSave);
    pane.getChildren().addAll(pane2, pane3, pane4);

    // Create a scene and place it in the stage
    Scene scene = new Scene(pane, 500, 500);
    primaryStage.setTitle("Book Search & Storage System"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.setResizable(false);
    primaryStage.show(); // Display the stage
  }
  
  public static void main(String[] args) {
    launch(args);
  }

// Creates and returns a new Stage consisting of 5 Test fields and a Button to add a new book to ArrayList
  public static Stage createNewStage(Library inv){
      Stage secondStage = new Stage(); // Creates a new scene
      Pane pane = new Pane(); // Creates a new Pane
      // Creates 5 TextField nodes and the submit button
      TextField txtTitle = new TextField("Title");
      TextField txtVolume = new TextField("Volume");
      TextField txtAuthor = new TextField("Author");
      TextField txtGenre = new TextField("Genre");
      TextField txtShelfNumber = new TextField("Shelf Number");
      Button BTSubmit = new Button("Submit");

      // Submit button action
      BTSubmit.setOnAction(e -> {
            // Takes the text from the text fields and uses it to create a new book to add to the ArrayList
              inv.getInv().add(new Book(txtTitle.getText(), txtVolume.getText(),
                      txtAuthor.getText(), txtGenre.getText(), txtShelfNumber.getText()));
              secondStage.close(); // Closes the new Stage
      });
      // Sets the locations of the nodes
      txtTitle.setTranslateY(10);
      txtTitle.setTranslateX(10);

      txtVolume.setTranslateY(10);
      txtVolume.setTranslateX(160);

      txtAuthor.setTranslateY(50);
      txtAuthor.setTranslateX(10);

      txtGenre.setTranslateY(50);
      txtGenre.setTranslateX(160);

      txtShelfNumber.setTranslateY(90);
      txtShelfNumber.setTranslateX(10);

      BTSubmit.setTranslateX(10);
      BTSubmit.setTranslateY(150);

      // Adds nodes to the pane
      pane.getChildren().add(txtTitle);
      pane.getChildren().add(txtVolume);
      pane.getChildren().add(txtAuthor);
      pane.getChildren().add(txtGenre);
      pane.getChildren().add(txtShelfNumber);
      pane.getChildren().add(BTSubmit);

      Scene scene = new Scene(pane, 500, 500); // Creates a new scene with the pane in it, and sets its size
      secondStage.setResizable(false);
      secondStage.setTitle("Add Book to System"); // Set the stage title
      secondStage.setScene(scene); // Place the scene in the stage
      return secondStage;
  }

  // Search Method for finding books based on a String
  public static void search(TextField txtSearch, Text result, ArrayList<Book> list){
      result.setText("");
      for(int i = 0; i < list.size(); i++){
          // Compares input String to every string in the array, converted to lowercase so its case insensitive
          if(list.get(i).toString().toLowerCase()
                  .contains(txtSearch.getText().toLowerCase())){

              // Prints the results
              result.setText(result.getText() + "\n" + list.get(i).toString());
          }
      }
  }
}
