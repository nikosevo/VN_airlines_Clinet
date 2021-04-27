package sample;

import javafx.scene.control.Alert;
import sample.Flight;
import sample.Interfaces.Operations;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Handler {

    //this is our variable where we will story our connection to the server
    Operations fromOperations;
    public Handler() {
        try {
            fromOperations = (Operations) Naming.lookup("rmi://localhost:1099/valnik");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public Flight getFlight(String id){
        Flight f = null;
        try {
            f = fromOperations.getFlightId(id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return f;
    }

    //since this method is called from the controller we need to return the list
    public ArrayList<Flight> getFlightsWith(String cityFrom, String cityTo,LocalDate date) {
        ArrayList<Flight> tempList = new ArrayList<Flight>();
        try {
             tempList = fromOperations.getFlightWith(cityFrom, cityTo, date);
        } catch (Exception e) {
            System.out.println(e);
        }
        return tempList;

    }

    public void booknow(int x, int y, String name, String email, String age,String flightId){
        boolean an = false;
        try {
            an = fromOperations.checkAvailability(flightId,x,y,new Person(name,email,age,"samos"));
            System.out.println(an);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if(an == false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Alert Message");
            alert.show();
        }


    }

    public ArrayList<String> getNonAvailable(String s) {
        try {
            return fromOperations.occupiedSeats(s);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
