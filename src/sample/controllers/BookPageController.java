package sample.controllers;

import sample.Handler;

public class BookPageController {

    Handler handler;


    public void bookNow(){
        handler.booknow(2,1,"nikos","nikos@hotmail.com","20","123");
    }
}
