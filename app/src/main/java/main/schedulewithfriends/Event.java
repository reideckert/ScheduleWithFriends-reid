package main.schedulewithfriends;

/**
 * Created by venge on 12/1/2017.
 */

public class Event {
    int eventID;
    String eventName, date, startTime, endTime, location, description;

    public Event(){};
    public Event(int eventID, String eventName, String date, String startTime, String endTime, String location, String description){
        this.eventID = eventID;
        this.eventName = eventName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.description = description;
    }

    //Setters
    public void setEventID(int eventID){
        this.eventID = eventID;
    }
    public void setEventName(String eventName){
        this.eventName = eventName;
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setStartTime(String startTime){
        this.startTime = startTime;
    }
    public void setEndTime(String endTime){
        this.endTime = endTime;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public void setDescription(String description){
        this.description = description;
    }

    //Getters
    public int getEventID(){
        return this.eventID;
    }
    public String getEventName(){
        return this.eventName;
    }
    public String getDate(){
        return this.date;
    }
    public String getStartTime(){
        return this.startTime;
    }
    public String getEndTime(){
        return this.endTime;
    }
    public String getLocation(){
        return this.location;
    }
    public String getDescription(){
        return this.description;
    }
}
