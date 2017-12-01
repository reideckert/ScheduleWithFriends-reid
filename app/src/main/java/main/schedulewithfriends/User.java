package main.schedulewithfriends;

/**
 * Created by venge on 12/1/2017.
 */

class User {
    int id;
    String userName, firstName, lastName, email;

    //Constructors
    public User(){};
    public User(int id, String userName, String firstName, String lastName, String email){
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //Setters
    public void setId(int id){
        this.id = id;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setEmail(String email){
        this.email = email;
    }

    //Getters
    public int getId(){
        return this.id;
    }
    public String getUserName(){
        return this.userName;
    }
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public String getEmail(){
        return this.email;
    }
}
