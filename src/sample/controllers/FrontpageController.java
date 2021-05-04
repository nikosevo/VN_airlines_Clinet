package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Flight;
import sample.Handler;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private VBox vbox;

    @FXML
    private TextField idSearch;

    @FXML
    private Button idSearchButton;

    @FXML
    private TextField name;

    @FXML
    private TextField flightId;

    @FXML
    private Button seeInfo;


    Handler handler = new Handler();
    ArrayList<Flight> tempList = new ArrayList<Flight>();


    public void bookFlight(){

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmls/bookPage.fxml"));
            Parent root = loader.load();
            BookPageController c = loader.getController();
            c.initialize(handler,idSearch.getText());
            Stage stage = new Stage();
            stage.setTitle("notitle");
            stage.setScene(new Scene(root, 1111, 444));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void searchWithId(){
        String flightId = idSearch.getText();
        Flight f = handler.getFlight(flightId);
        tempList.clear();
        tempList.add(f);
        updateList();
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
        vbox.getChildren().clear();
        //adding manually to list so we konw that this scene into scene is not  bullshiet n acc workds
        //somehow  if the list if empty still renders a piece of default string shit todo
        for(Flight f : tempList){

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmls/flightComponent.fxml"));
                Parent root = loader.load();
                FlightController fc = loader.getController();
                SubScene flight = new SubScene(root,800,50);
                vbox.getChildren().add(flight);
                fc.setLabels(f.getId());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public void seeDetails(){
        ArrayList<String> details = new ArrayList<>();
        details = handler.getDetails(name.getText(),flightId.getText());
        try{

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmls/detailsPage.fxml"));
            Parent root = loader.load();
            DetailsController c = loader.getController();
            c.initialize(details);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root, 371, 622));
            stage.setResizable(false);
            stage.show();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }



}
