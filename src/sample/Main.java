//Icsd18174 Chrysovalantis Pateiniotis
//Icsd18218 Nikos Tzekas

package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmls/frontPage.fxml"));
        //root.getStylesheets().add(getClass().getResource("../css/fronPageStyle.css").toExternalForm());
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1110, 616 ));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {launch(args);}}
