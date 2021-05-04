package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Handler;

import java.io.IOException;


public class FlightController {

    @FXML
    private Label flightID;

    @FXML
    private Button booBtn;

    @FXML
    private Label from;

    @FXML
    private Label to;

    @FXML
    private Label numOfSeats;

    @FXML
    private Label cost;

    private Handler handler;
    private String flightid;



    @FXML
    public void initialize(String flightid, Handler handler)
    {
        this.handler = handler;
        this.flightid = flightid;
        flightID.setText(flightid);
    }


    public void book() {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmls/bookPage.fxml"));
            Parent root = loader.load();
            BookPageController c = loader.getController();
            c.initialize(handler,flightid);
            Stage stage = new Stage();
            stage.setTitle("notitle");
            stage.setScene(new Scene(root, 1111, 444));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLabels(String from,String to, String numOfSeats , String cost){
        this.from.setText(from);
        this.to.setText(to);
        this.numOfSeats.setText(numOfSeats);
        this.cost.setText(cost+"€");
    }




}
