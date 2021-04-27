package sample.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import sample.Handler;
import sample.Person;

import java.util.ArrayList;
import java.util.Arrays;

public class BookPageController {

    @FXML
    private GridPane grid;

    Handler handler;
    private boolean clicked[][];
    private ArrayList<String> notAvailable;


    public void setGrid(ArrayList<String> notAvailableSeats){
        setClicked();
        this.notAvailable = notAvailableSeats;

        for(int i = 0 ; i < 26 ; i++){
            for(int j = 0 ; j < 5 ; j++){

                if(i==0 || j==0)
                    continue;

                String btnId = i + "-" + j;
                Button temp_btn = newSeatButton(i,j);
                grid.add(temp_btn,i,j,1,1);
                for(String seats : notAvailableSeats){
                    if(seats.equals(btnId)) {
                        temp_btn.setStyle("-fx-background-color: red");
                    }


                }
            }
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

    public void setHandler(Handler handler){this.handler = handler;}
}
