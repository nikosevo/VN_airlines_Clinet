package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import sample.Handler;
import sample.Person;

import javax.net.ssl.SNIHostName;
import java.util.ArrayList;
import java.util.Arrays;

public class BookPageController {

    @FXML
    private GridPane grid;

    Handler handler;
    private boolean clicked[][];
    private ArrayList<String> notAvailable;
    private ArrayList<String> temporarilyNotAvailable;
    protected String flightid;  //this is protected so that the fxml can see it

    //kinna like a constructor but is acrivated after the constructor so we can create the obj and then call this method
    @FXML
    public void initialize(Handler handler,String flightId) {
        this.handler = handler;
        this.flightid = flightId;
        setClicked();
        setGrid();
    }
    public void setGrid(){


        notAvailable = handler.getNonAvailable(flightid);;
        temporarilyNotAvailable = handler.getTempNonAvailable(flightid);

        for(int i = 0 ; i < 26 ; i++){
            for(int j = 0 ; j < 5 ; j++){

                if(i==0 || j==0)
                    continue;

                String btnId = i + "-" + j;
                Button temp_btn = newSeatButton(i,j);
                grid.add(temp_btn,i,j,1,1);
                for(String seats : notAvailable){
                    if(seats.equals(btnId))
                        temp_btn.setStyle("-fx-background-color: red");
                }
                for(String seats : temporarilyNotAvailable){
                    if(seats.equals(btnId)){
                        temp_btn.setStyle("-fx-background-color: yellow");
                    }
                }
            }
        }
    }
    //lets proceed to booking by taking all the true values in the clicked list and senting a request to the server to check availability(usually this will work but maybe someone was faster than you and booked the same seat just before u sent the request)
    public void proceedToBooking(){
        ArrayList<String> wishlist = new ArrayList<String>();
        for(int i = 0 ; i < 25 ; i++){
            for(int j= 0 ; j <4 ; j++){
                if(clicked[i][j] == true){
                    wishlist.add((i+1)+"-"+(j+1));
                }
            }
        }


        if(handler.checkAvailability(flightid ,wishlist)){
             ArrayList<Person> tmp = new ArrayList<>();

            //collect data from the user via the ui

            //thats for later after we collect all our data from the uesr
            tmp.add(new Person("name","email","age","startcity"));
            handler.bookTemporarily(flightid ,wishlist);
        }
    }
    private void setClicked(){
        clicked = new boolean[25][4];

        for(int i = 0 ; i < 25 ; i++){
            for(int j = 0 ; j<4 ; j++){
                clicked[i][j] = false;
            }
        }
    }
    private Button newSeatButton(int i , int j){
        Button btn = new Button();
        btn.setStyle("-fx-background-color: green");
        btn.setPrefWidth(40);
        btn.setId(i+"-"+j);
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                String id = btn.getId();
                for(String btnId : notAvailable)
                    if(id.equals(btnId))
                        return;
                String[] parts = id.split("-");
                String part1 = parts[0];
                String part2 = parts[1];

                try{
                    int i = Integer.parseInt(part1);
                    int j = Integer.parseInt(part2);

                }
                catch (NumberFormatException ex){
                    ex.printStackTrace();
                }
                if(clicked[i-1][j-1] == false) {
                    clicked[i-1][j-1] = true;
                    btn.setStyle("-fx-background-color: orange");
                }
                else{
                    clicked[i-1][j-1] = false;
                    btn.setStyle("-fx-background-color: green");
                }

            }
        });

        return btn;

    }


}
