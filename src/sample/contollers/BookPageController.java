package sample.contollers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Handler;

import java.io.IOException;

public class BookPageController {

    Handler handler;


    public void bookNow(){
        handler.booknow(2,1,"nikos","nikos@hotmail.com","20","123");
    }
}
