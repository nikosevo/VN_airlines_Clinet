package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.Handler;
import sample.Person;




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
        String p_name = name.getText();
        String p_email = email.getText();
        String p_age = age.getText();
        String p_phoneNum = phoneNum.getText();

        Person p = new Person(p_name,p_email,p_age,p_phoneNum,"696969696969");
        handler.bookPermenantly(flightid,place,p);

    }
}