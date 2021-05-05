package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmls/frontPage.fxml"));
        //root.getStylesheets().add(getClass().getResource("../css/fronPageStyle.css").toExternalForm());
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1110, 616 ));
        primaryStage.show();
    }


    public static void main(String[] args) {launch(args);}}
