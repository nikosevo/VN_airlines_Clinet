package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class FlightController {

    @FXML
    private Label flightID;


    @FXML
    public void initialize(String flightid)
    {
        flightID.setText(flightid);
    }

    public void setLabels(String flightid){
        flightID.setText(flightid);
    }



}
