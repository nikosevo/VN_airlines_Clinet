package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.Handler;

import java.util.ArrayList;

public class DetailsController {

    @FXML
    private Label flightId;

    @FXML
    private Label seat;

    @FXML
    private Label name;

    @FXML
    private Label to;

    @FXML
    private Label timeTo;

    @FXML
    private Label dateTo;

    @FXML
    private Label from;

    @FXML
    private Label timeFrom;

    @FXML
    private Label dateFrom;

    @FXML
    private Button exitBtn;

    @FXML
    public void initialize(ArrayList<String> details) {
        name.setText(details.get(0));
        seat.setText(details.get(1));
        flightId.setText(details.get(2));
        dateFrom.setText(details.get(3));
        timeFrom.setText(details.get(4));
        from.setText(details.get(5));
        dateTo.setText(details.get(6));
        timeTo.setText(details.get(7));
        to.setText(details.get(8));

    }

    public void exit(){

        Stage st = (Stage)exitBtn.getScene().getWindow();
        st.close();
    }

}