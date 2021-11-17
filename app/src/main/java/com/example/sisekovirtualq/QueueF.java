package com.example.sisekovirtualq;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
public class QueueF {

    Queue<Person>Q=new LinkedList<Person>();
    ArrayList<Person>checkin=new ArrayList<Person>();
    int checkinL;
    int lengthofQ;
    String code;

    public QueueF(){
           lengthofQ=200;
           checkinL=100;

    }


    public String  joinQ(String name){
         Person person=new Person(name);
        if(checkin.size()<checkinL){
             person.setStatus("checked in");
             person.setState("coming");
             checkin.add(person);
             return "checkein";
        }
        else if(Q.size()<lengthofQ){
            person.setStatus("Waiting");
            person.setState("out");
            Q.offer(person);
            return "waiting";

        }
        else{
            return "try again late queue is full";

        }


    }


    public Boolean checkOut(int code){
        for(Person person1:Q) {
            if(person1.getCode()==code){
                checkin.remove(person1);
                if(Q.size()>0){
                    Person person2=Q.remove();
                    person2.setStatus("checkedin");
                    person2.setState("coming");
                    checkin.add(person2);
                    return true;
                }
                else {
                    return true;
                }
            }
        }
        return false;
    }

    public String setInState(int code){
        for (Person person:checkin) {
            if(person.getCode()==code){
                person.setState("checkedin");
                return person.getState();
            }
        }
        return "code do not exist";

    }



    public void setCheckeinL(int wq){
        this.checkinL=wq;
    }
    public void setLengthofQ(int lQ){
        this.lengthofQ=lQ;
    }
    public void setCode(String code){
        this.code=code;

    }

    public void clearQ(){
        Q.clear();
        checkin.clear();
        Person.code=0;
    }


    public int getCheckinL(){

        return checkinL;
    }


    public int getLengthofQ(){
        return lengthofQ;
    }



}
