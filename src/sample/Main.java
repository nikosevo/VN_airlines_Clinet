package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Interfaces.FlightOperations;

import java.rmi.Naming;
import java.time.LocalTime;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmls/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1130, 530 ));
        primaryStage.show();
    }


    public static void main(String[] args) {

        try {
            Scanner s = new Scanner(System.in);
            FlightOperations dirop = (FlightOperations) Naming.lookup("rmi://localhost:1099/valnik");
            System.out.println("search flight:");
            Flight f = dirop.getFlightId(s.nextLine());
            System.out.println(f == null ? "user not found" : f);

        } catch (Exception e) {
            System.out.println(e);
        }

        launch(args);
    }
}
