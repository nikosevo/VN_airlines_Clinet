package sample;

import sample.Flight;
import sample.Interfaces.Operations;

import java.rmi.Naming;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Handler {


    public Flight getFlight(String id){
        Flight f = null;
        try {
            Operations dirop = (Operations) Naming.lookup("rmi://localhost:1099/valnik");
            f = dirop.getFlightId(id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return f;
    }

    public ArrayList<Flight> getFlightsWith(String cityFrom, String cityTo,LocalDate date) {
        ArrayList<Flight> tempList = new ArrayList<Flight>();
        try {
            Operations dirop = (Operations) Naming.lookup("rmi://localhost:1099/valnik");
             tempList = dirop.getFlightWith(cityFrom, cityTo, date);
        } catch (Exception e) {
            System.out.println(e);
        }
        return tempList;

    }
}
