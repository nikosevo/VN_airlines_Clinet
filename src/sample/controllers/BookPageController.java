package sample.controllers;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import sample.Handler;
import sample.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class BookPageController implements Initializable
{

    @FXML
    private GridPane grid;
    @FXML
    private Button cancelBtn;


    Handler handler;
    private boolean clicked[][];
    private ArrayList<String> notAvailable;
    private ArrayList<String> temporarilyNotAvailable;
    private ArrayList<Person> onlyForNewPersons = new ArrayList<Person>();
    protected String flightid;  //this is protected so that the fxml can see it
    private boolean needWindowForNewPerson = false;
    private int openWindows = 0;
    ArrayList<String> tmpCauseWeAreNoobs;

    //kinna like a constructor but is acrivated after the constructor so we can create the obj and then call this method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(2), e -> {
                    setGrid();
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    @FXML
    public void initialize(Handler handler, String flightId)
    {
        this.handler = handler;
        this.flightid = flightId;
        setClicked();
        setGrid();

    }

    public void setGrid()
    {

        notAvailable = handler.getNonAvailable(flightid);

        temporarilyNotAvailable = handler.getTempNonAvailable(flightid);

        for (int i = 0; i < 26; i++)
        {
            for (int j = 0; j < 5; j++)
            {

                if (i == 0 || j == 0) continue;

                String btnId = i + "-" + j;
                Button temp_btn = newSeatButton(i, j);
                grid.add(temp_btn, i, j, 1, 1);
                for (String seats : notAvailable)
                {
                    if (seats.equals(btnId)) temp_btn.setStyle("-fx-background-color: red");
                }
                for (String seats : temporarilyNotAvailable)
                {
                    if (seats.equals(btnId))
                    {
                        temp_btn.setStyle("-fx-background-color: yellow");
                    }
                }
                if(clicked[i-1][j-1] == true)
                    temp_btn.setStyle("-fx-background-color: orange");

            }
        }
    }

    //lets proceed to booking by taking all the true values in the clicked list and senting a request to the server to check availability(usually this will work but maybe someone was faster than you and booked the same seat just before u sent the request)
    public void proceedToBooking()
    {
        ArrayList<String> wishlist = new ArrayList<String>();
        for (int i = 0; i < 25; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if (clicked[i][j])
                {
                    wishlist.add((i + 1) + "-" + (j + 1));
                }
            }
        }
        if (handler.checkAvailability(flightid, wishlist))
        {
            if (handler.bookTemporarily(flightid, wishlist))
            {
                //collect data from user vie the ui
                //TODO THIS NEED TO BE FIXED FIRSTLY WE TAKE THE INFO FROM THE USER AND PROCEED ONLY WHEN HE HAS FILLED THE WINDOWS
                //WITH HIS INFO
                for(int i = 0 ; i < 25 ; i++){
                    for(int j = 0 ; j < 4 ; j++){
                        clicked[i][j] = false;
                    }
                }
                setGrid();
                for (int i = 0; i < wishlist.size(); i++)
                {
                    newpersonWindow(wishlist,i);
                }
                tmpCauseWeAreNoobs = wishlist;
                openWindows = wishlist.size();
                System.out.println(openWindows);
            }
        }
    }

    public void closeWindow(){
        openWindows--;
        System.out.println(openWindows);
        if(openWindows == 0){
            handler.remove(flightid,tmpCauseWeAreNoobs);
        }
    }

    private void newpersonWindow(ArrayList<String> wishList , int i)
    {

        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmls/newPersonPage.fxml"));
            Parent root = loader.load();
            NewPersonPageController newperson = loader.getController();
            newperson.initialize(handler , flightid , wishList.get(i),this);
            Stage stage = new Stage();
            stage.setTitle("no other title");
            stage.setScene(new Scene(root, 420 , 300));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    private void setClicked()
    {
        clicked = new boolean[25][4];

        for (int i = 0; i < 25; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                clicked[i][j] = false;
            }
        }
    }

    private Button newSeatButton(int i, int j)
    {
        Button btn = new Button();
        btn.setStyle("-fx-background-color: green");
        btn.setPrefWidth(40);
        btn.setId(i + "-" + j);
        btn.setOnAction(new EventHandler<ActionEvent>()
        {

            @Override
            public void handle(ActionEvent event)
            {
                String id = btn.getId();
                for (String btnId : notAvailable)
                    if (id.equals(btnId)) return;
                String[] parts = id.split("-");
                String part1 = parts[0];
                String part2 = parts[1];

                try
                {
                    int i = Integer.parseInt(part1);
                    int j = Integer.parseInt(part2);

                } catch (NumberFormatException ex)
                {
                    ex.printStackTrace();
                }
                if (clicked[i - 1][j - 1] == false)
                {
                    clicked[i - 1][j - 1] = true;
                    btn.setStyle("-fx-background-color: orange");
                } else
                {
                    clicked[i - 1][j - 1] = false;
                    btn.setStyle("-fx-background-color: green");
                }

            }
        });

        return btn;

    }

    public void exit(){
        Stage st = (Stage) cancelBtn.getScene().getWindow();
        st.close();
    }
}