package sample.contollers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Flight;
import sample.Handler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FrontpageController {

    @FXML
    private TextField fromText;

    @FXML
    private TextField toText;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button flyBtn;

    @FXML
    private ListView<String> list;

    @FXML
    private TextField idSearch;

    @FXML
    private Button idSearchButton;

    Handler handler = new Handler();
    ArrayList<Flight> tempList = new ArrayList<Flight>();



    public void searchWithId(){
        String flightId = idSearch.getText();
        Flight f = handler.getFlight(flightId);
        tempList.clear();
        tempList.add(f);
        updateList();
    }
    public void bookFlight(){
        new BookPageController(handler);

    }
    public void search(){
        String cityFrom = fromText.getText();
        String cityTo = toText.getText();
        LocalDate date = datePicker.getValue();
        tempList.clear();
        tempList = handler.getFlightsWith(cityFrom,cityTo,date);
        updateList();
    }
    private void updateList(){
        list.getItems().clear();
        for(Flight f : tempList)
            list.getItems().add(f.toString());
    }



}
