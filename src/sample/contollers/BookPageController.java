package sample.contollers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Handler;

import java.io.IOException;

public class BookPageController {

    Handler handler;

    BookPageController(Handler handler){
        this.handler = handler;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("../fxmls/bookPage.fxml"));
            Stage stage = new Stage();
            stage.setTitle("My New Stage Title");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
