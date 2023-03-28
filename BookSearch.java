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
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BookSearch extends Application {
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) throws FileNotFoundException{

    Pane pane = new Pane();
    TextField txtSearch = new TextField("Search");
    Button BTSearch = new Button("Search");
    Button BTAdd = new Button("Add");
    Button BTRefresh = new Button("Refresh");
    Button BTSave = new Button("Save & Quit");
    
    File file = new File("Inventory.txt");
    Library inv = new Library(file);
    Text result = new Text(inv.setText());
    SearchButton search = new SearchButton(txtSearch, result, inv.getInv());
    
    
    pane.setPadding(new Insets(10, 10, 10, 10));
    txtSearch.setOnAction(search);
    BTSearch.setOnAction(search);
    BTAdd.setOnAction(e -> {
        Stage newStage = createNewStage(inv);
        newStage.show();
    });
    BTRefresh.setOnAction(e -> {
            result.setText(inv.setText());
    });
    BTSave.setOnAction(e ->{
        try {
            inv.exportInventory(file);
            primaryStage.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    });
    BTAdd.setTranslateY(50);
    BTAdd.setTranslateX(0);
    BTRefresh.setTranslateX(50);
    BTRefresh.setTranslateY(50);
    BTSave.setTranslateY(470);
    ScrollBar scroll = new ScrollBar();
    Text title = new Text("Book Search System");
    scroll.setOrientation(Orientation.VERTICAL);
    title.setTranslateY(10);
    scroll.setTranslateX(475);
    scroll.setTranslateY(100);
    scroll.setScaleY(3);
    scroll.setMax(500);
    result.setX(200);
    result.setY(10);

    scroll.setOnMouseClicked(e -> {
        result.setTranslateY(scroll.getValue() * -1);
    });
      scroll.setOnScroll(e -> {
          result.setTranslateY(scroll.getValue() * -1);
      });
    txtSearch.setTranslateY(20);
    BTSearch.setTranslateY(20);
    BTSearch.setTranslateX(100);
    pane.getChildren().addAll(title,scroll,txtSearch,BTSearch,result,BTAdd,BTRefresh,BTSave);

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


  public static Stage createNewStage(Library inv){
      Stage secondStage = new Stage();
      Pane pane = new Pane();

      TextField txtTitle = new TextField("Title");
      TextField txtVolume = new TextField("Volume");
      TextField txtAuthor = new TextField("Author");
      TextField txtGenre = new TextField("Genre");
      TextField txtShelfNumber = new TextField("Shelf Number");
      Button BTSubmit = new Button("Submit");

      BTSubmit.setOnAction(e -> {
              inv.getInv().add(new Book(txtTitle.getText(), txtVolume.getText(), txtAuthor.getText(), txtGenre.getText(), txtShelfNumber.getText()));
              secondStage.close();
      });
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

      pane.getChildren().add(txtTitle);
      pane.getChildren().add(txtVolume);
      pane.getChildren().add(txtAuthor);
      pane.getChildren().add(txtGenre);
      pane.getChildren().add(txtShelfNumber);
      pane.getChildren().add(BTSubmit);

      Scene scene = new Scene(pane, 500, 500);
      secondStage.setTitle("Add Book to System"); // Set the stage title
      secondStage.setScene(scene); // Place the scene in the stage
      return secondStage;


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
