package main.schedulewithfriends;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Time;
import java.util.Date;

/**
 * Created by venge on 11/20/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    
    private static final String DATABASE_NAME = "Schedule_Database";

    private static final String USER_TABLE = "userTable";
    private static final String USER_COL0 = "userID";
    private static final String USER_COL1 = "userName";
    private static final String USER_COL2 = "firstName";
    private static final String USER_COL3 = "lastName";
    private static final String USER_COL4 = "email";

    private static final String CREATE_USER_TABLE = "CREATE TABLE "+USER_TABLE+"("+USER_COL0+
            " INTEGER PRIMARY KEY AUTOINCREMENT, "+ USER_COL1+" TEXT, "+USER_COL2+" TEXT, "+
            USER_COL3+" TEXT, "+USER_COL4+" TEXT)";
    
    private static final String EVENT_TABLE = "eventTable";
    private static final String EVENT_COL0 = "eventID";
    private static final String EVENT_COL1 = "eventName";
    private static final String EVENT_COL2 = "date";
    private static final String EVENT_COL3 = "startTime";
    private static final String EVENT_COL4 = "endTime";
    private static final String EVENT_COL5 = "location";
    private static final String EVENT_COL6 = "description";

    private static final String CREATE_EVENT_TABLE = "CREATE TABLE "+EVENT_TABLE+"("+EVENT_COL0+
            " INTEGER PRIMARY KEY AUTOINCREMENT, "+ EVENT_COL1+" TEXT, "+EVENT_COL2+" DATE, "+
            EVENT_COL3+" TIME, "+EVENT_COL4+" TIME, "+EVENT_COL5+" TEXT, "+EVENT_COL6+")";
    
    

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_EVENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE);
        onCreate(db);
    }

    public boolean addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL1, user.userName);
        contentValues.put(USER_COL2, user.firstName);
        contentValues.put(USER_COL3, user.lastName);
        contentValues.put(USER_COL4, user.email);

        Log.d(TAG, "addData: Adding "+user.userName+" to "+USER_TABLE);

        long result = db.insert(USER_TABLE, null, contentValues);

        if(result == -1) {
            return false;
        }else{
            return true;
        }
    }

    public boolean addEvent(Event event){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EVENT_COL1, event.eventName);
        contentValues.put(EVENT_COL2, event.date);
        contentValues.put(EVENT_COL3, event.startTime);
        contentValues.put(EVENT_COL4, event.endTime);
        contentValues.put(EVENT_COL5, event.location);
        contentValues.put(EVENT_COL6, event.description);

        Log.d(TAG, "addData: Adding "+event.eventName+" to "+EVENT_TABLE);

        long result = db.insert(EVENT_TABLE, null, contentValues);

        if(result == -1) {
            return false;
        }else{
            return true;
        }
    }

    /**
     * Returns all the users from database
     * @return
     */
    public Cursor getAllUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+USER_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns all the events from database
     * @return
     */
    public Cursor getAllEvents(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+EVENT_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
            * Returns only the user that matches the id passed in
     * @param id
     * @return
             */
    public User getUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+USER_TABLE+
                " WHERE "+USER_COL0+" = '"+id+"'";
        Cursor data = db.rawQuery(query, null);
        if(data != null)
            data.moveToFirst();

        User user = new User();
        user.setId(data.getInt(data.getColumnIndex(USER_COL0)));
        user.setUserName(data.getString(data.getColumnIndex(USER_COL1)));
        user.setFirstName(data.getString(data.getColumnIndex(USER_COL2)));
        user.setLastName(data.getString(data.getColumnIndex(USER_COL3)));
        user.setEmail(data.getString(data.getColumnIndex(USER_COL4)));
        
        return user;
    }

    /**
     * Returns only the event that matches the id passed in
     * @param id
     * @return
     */
    public Event getEvent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+EVENT_TABLE+
                " WHERE "+EVENT_COL0+" = '"+id+"'";
        Cursor data = db.rawQuery(query, null);
        if(data != null)
            data.moveToFirst();

        Event event = new Event();
        event.setEventID(data.getInt(data.getColumnIndex(EVENT_COL0)));
        event.setEventName(data.getString(data.getColumnIndex(EVENT_COL1)));
        event.setDate(data.getString(data.getColumnIndex(EVENT_COL2)));
        event.setStartTime(data.getString(data.getColumnIndex(EVENT_COL3)));
        event.setEndTime(data.getString(data.getColumnIndex(EVENT_COL4)));
        event.setLocation(data.getString(data.getColumnIndex(EVENT_COL5)));
        event.setDescription(data.getString(data.getColumnIndex(EVENT_COL6)));
        
        return event;
    }

    /**
     * Updates the fields of user
     * @param newUser
     * @param oldUser
     */
    public void updateUser(User newUser, User oldUser){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+USER_TABLE+
                " SET "+USER_COL1+" = '"+newUser.userName+
                "', "+USER_COL2+" = '"+newUser.firstName+
                "', "+USER_COL3+" = '"+newUser.lastName+
                "', "+USER_COL4+" = '"+newUser.email+
                "' WHERE "+USER_COL0+" = '"+newUser.id+"'"+
                " AND "+USER_COL0+" = '"+oldUser.id+"'";
        Log.d(TAG, "updateName: query: "+query);
        db.execSQL(query);
    }

    /**
     * Updates the fields of event
     * @param newEvent
     * @param oldEvent
     */
    public void updateEvent(Event newEvent, Event oldEvent){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE "+EVENT_TABLE+
                " SET "+EVENT_COL1+" = '"+newEvent.eventName+
                "', "+EVENT_COL2+" = '"+newEvent.date+
                "', "+EVENT_COL3+" = '"+newEvent.startTime+
                "', "+EVENT_COL4+" = '"+newEvent.endTime+
                "', "+EVENT_COL5+" = '"+newEvent.location+
                "', "+EVENT_COL6+" = '"+newEvent.description+
                "' WHERE "+EVENT_COL0+" = '"+newEvent.eventID+"'"+
                " AND "+EVENT_COL0+" = '"+oldEvent.eventID+"'";
        Log.d(TAG, "updateName: query: "+query);
        db.execSQL(query);
    }

    /**
     * Delete user from database
     * @param user
     */
    public void deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+USER_TABLE+" WHERE "
                +USER_COL0+" = '"+user.id+"' AND "+USER_COL1+" = '"+
                user.userName+"'";
        Log.d(TAG, "deleteName: query: "+query);
        Log.d(TAG, "deleteName: Deleteing "+user.userName+" from database.");
        db.execSQL(query);
    }

    /**
     * Delete event from database
     * @param event
     */
    public void deleteEvent(Event event){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+EVENT_TABLE+" WHERE "
                +EVENT_COL0+" = '"+event.eventID+"' AND "+EVENT_COL1+" = '"+
                event.eventName+"'";
        Log.d(TAG, "deleteName: query: "+query);
        Log.d(TAG, "deleteName: Deleting "+event.eventName+" from database.");
        db.execSQL(query);
    }
}

