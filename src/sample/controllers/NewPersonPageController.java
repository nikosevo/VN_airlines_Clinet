package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Handler;
import sample.Person;

import javax.swing.*;


public class NewPersonPageController {


    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextField age;

    @FXML
    private TextField phoneNum;

    @FXML
    private Button next;

    @FXML
    private TextArea details;

    protected Handler handler;
    protected String flightid;
    protected String place;

    @FXML
    public void initialize(Handler handler, String flightid,String place)
    {
        this.handler = handler;
        this.flightid = flightid;
        this.place = place;
    }
    public void createPerson(){

        if(name.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please enter your name and surname.", "Name missing" , JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(age.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Please enter your Age.", "Age missing" , JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(email.getText().isEmpty() || !email.getText().contains("@") )
        {
            JOptionPane.showMessageDialog(null, "Please enter an email that it is valid.", "Age missing" , JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(phoneNum.getText().isEmpty() || phoneNum.getText().length()<10)
        {
            JOptionPane.showMessageDialog(null, "Please enter a phone number that it is valid.", "Invalid phone number" , JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Person p = new Person(name.getText(),email.getText(),age.getText(),phoneNum.getText());
        handler.bookPermenantly(flightid,place,p);

        Stage st = (Stage) next.getScene().getWindow();
        st.close();

    }

}