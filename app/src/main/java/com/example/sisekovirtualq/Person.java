package com.example.sisekovirtualq;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

public class Person {

    private String name;
    private String status;
    String date;
    String state;
    public static int code=0;
    public Person(String name){
        this.name=name;
        code++;


    }

    public String generateCode(){

        Date d= Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        date=dateFormat.format(d);
        String tempcode="Name:"+name+"\n"+"number"+code+"\n"+"Date"+date;
        return tempcode;
    }


    public void setState(String state) {
        this.state = state;
    }
    public String getState(){
        return state;
    }


    public void setName(String name){
        this.name=name;

    }

    public void setStatus(String status){
        this.status=status;
    }

    public String getStatus(){

        return status;
    }
    public int getCode(){
        return code;
    }
    public String getName(){
        return name;
    }


}
