package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import sample.Handler;

import java.util.ArrayList;

public class BookPageController {

    @FXML
    private GridPane grid;

    Handler handler;

    public void setGrid(ArrayList<String> booked){
        for(int i = 0 ; i < 26 ; i++){
            for(int j = 0 ; j < 5 ; j++){
                if(i==0 && j==0)
                    continue;
                if(i == 0 ){
                    grid.add(new Label(j + ""),i,j,1,1);
                    continue;
                }
                if(j==0){
                    grid.add(new Label(i+""),i,j,1,1);
                    continue;
                }
                String content = i + "" + j;
                Button temp_btn = new Button();
                grid.add(temp_btn,i,j,1,1);
                for(String seat : booked){
                    if(seat.equals(content))
                        temp_btn.setStyle("-fx-background-color: red");
                    else
                        temp_btn.setStyle("-fx-background-color: green");

                }
            }
        }
    }

    public void setHandler(Handler handler){this.handler = handler;}
}
