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
    ScrollPane scrollPane = new ScrollPane();
    Pane titlePane = new Pane();
    Pane buttonPane = new Pane();

    // Created Nodes to place within Panes
    Text title = new Text("Book Search System");
    TextField txtSearch = new TextField("Search");
    Button BTSearch = new Button("Search");
    Button BTAdd = new Button("Add");
    Button BTEdit = new Button("Edit");
    Button BTSave = new Button("Save & Quit");
    File file = new File("Inventory.txt");
    Library inv = new Library(file);
    Text result = new Text(inv.setText());

    // Added actions to the nodes
    pane.setPadding(new Insets(10, 10, 10, 10));
    txtSearch.setOnAction(e -> search(txtSearch, result, inv.getInv())); // Allows you to press enter after typing to search
    BTSearch.setOnAction(e -> search(txtSearch, result, inv.getInv())); // Allows you to press the search button when done typing
    BTAdd.setOnAction(e -> {
        Stage newStage = createAddStage(inv, result); // Creates a new stage with the features defined in the createNewStage class
        newStage.show(); // Displays the Stage
    });
    BTEdit.setOnAction(e -> {
        Stage newStage = createEditStage(inv, result);
        newStage.show();
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
    BTAdd.setTranslateX(63);
    BTSave.setTranslateY(460);
    BTSearch.setTranslateY(50);
    BTSearch.setTranslateX(0);
    BTEdit.setTranslateX(113);
    BTEdit.setTranslateY(50);
    title.setTranslateY(10);
    result.setX(200);
    result.setY(10);
    txtSearch.setTranslateY(20);


    // set locations and parameters of the Panes
    scrollPane.setTranslateX(100);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);
    scrollPane.setMaxWidth(300);
    scrollPane.setContent(result);

    // Add Nodes to their respective panes
    titlePane.getChildren().addAll(title);
    buttonPane.getChildren().addAll(txtSearch,BTSearch, BTAdd,BTSave,BTEdit);
    pane.getChildren().addAll(titlePane, buttonPane, scrollPane);

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
  public static Stage createAddStage(Library inv, Text result){
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
          result.setText(inv.setText()); // Redraws the list of books
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
      pane.getChildren().addAll(txtTitle,txtVolume,txtAuthor,txtGenre,txtShelfNumber,BTSubmit);

      Scene scene = new Scene(pane, 500, 500); // Creates a new scene with the pane in it, and sets its size
      secondStage.setResizable(false);
      secondStage.setTitle("Add Book to System"); // Set the stage title
      secondStage.setScene(scene); // Place the scene in the stage
      return secondStage;
  }

    public static Stage createEditStage(Library inv, Text result){
        Stage secondStage = new Stage(); // Creates a new scene
        StackPane pane = new StackPane(); // Creates a new Pane
        Pane textPane = new Pane();
        Pane textPane2 = new Pane();
        ScrollPane scrollPane = new ScrollPane();

        String stringText = inv.setText();

        Text text = new Text(stringText);
        // Creates 5 TextField nodes and the submit button
        TextField txtIndex = new TextField("Index");
        Button BTSubmit = new Button("Submit");

        TextField txtTitle = new TextField("Title");
        TextField txtVolume = new TextField("Volume");
        TextField txtAuthor = new TextField("Author");
        TextField txtGenre = new TextField("Genre");
        TextField txtShelfNumber = new TextField("Shelf Number");
        Button BTSubmit2 = new Button("Submit");
        Button BTDel = new Button("Delete");



        // Submit button action
        BTSubmit.setOnAction(e -> {
            if(txtIndex.getText().equals("")){
                text.setText(stringText);
            }else {
                text.setText(inv.getInv().get(Integer.valueOf(txtIndex.getText())).toString());
            }
            BTSubmit.setVisible(false);
            txtIndex.setVisible(false);
            pane.getChildren().add(textPane2);

        });
        txtIndex.setOnAction(BTSubmit.getOnAction());

        BTSubmit2.setOnAction(e -> {
            inv.getInv().get(Integer.parseInt(txtIndex.getText())).setAuthor(txtAuthor.getText());
            inv.getInv().get(Integer.parseInt(txtIndex.getText())).setTitle(txtTitle.getText());
            inv.getInv().get(Integer.parseInt(txtIndex.getText())).setGenre(txtGenre.getText());
            inv.getInv().get(Integer.parseInt(txtIndex.getText())).setShelfNumber(txtShelfNumber.getText());
            inv.getInv().get(Integer.parseInt(txtIndex.getText())).setVolumeNumber(txtVolume.getText());

            secondStage.close(); // Closes the new Stage
            result.setText(inv.setText()); // Redraws the list of books
        });

        BTDel.setOnAction(e -> {
            inv.getInv().remove(Integer.parseInt(txtIndex.getText()));

            secondStage.close(); // Closes the new Stage
            result.setText(inv.setText()); // Redraws the list of books
        });
        // Sets the locations of the nodes
        txtIndex.setTranslateY(10);
        txtIndex.setTranslateX(10);

        BTSubmit.setTranslateX(10);
        BTSubmit.setTranslateY(90);

        txtTitle.setTranslateY(10);
        txtTitle.setTranslateX(10);

        txtVolume.setTranslateY(50);
        txtVolume.setTranslateX(10);

        txtAuthor.setTranslateY(90);
        txtAuthor.setTranslateX(10);

        txtGenre.setTranslateY(130);
        txtGenre.setTranslateX(10);

        txtShelfNumber.setTranslateY(170);
        txtShelfNumber.setTranslateX(10);

        BTSubmit2.setTranslateX(10);
        BTSubmit2.setTranslateY(210);
        BTDel.setTranslateX(70);
        BTDel.setTranslateY(210);

        scrollPane.setTranslateX(100);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setMaxWidth(300);
        scrollPane.setContent(text);

        // Adds nodes to the pane
        textPane.getChildren().addAll(txtIndex,BTSubmit);
        textPane2.getChildren().addAll(txtTitle, txtVolume, txtAuthor, txtGenre, txtShelfNumber, BTSubmit2, BTDel);

        pane.getChildren().addAll(textPane, scrollPane);

        Scene scene = new Scene(pane, 500, 500); // Creates a new scene with the pane in it, and sets its size
        secondStage.setResizable(false);
        secondStage.setTitle("Edit a Book in the System"); // Set the stage title
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
              result.setText(result.getText() + "\n" + "Index " + i + ": " + list.get(i).toString() + "\n");
          }
      }
  }
}
