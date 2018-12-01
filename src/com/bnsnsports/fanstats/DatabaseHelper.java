package com.bnsnsports.fanstats;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Hashtable;

import java.util.Iterator;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.sql.SQLException;
import android.util.Log;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteException;
import android.app.AlertDialog;
import java.util.Random;


public class DatabaseHelper extends SQLiteOpenHelper{
    
//The Android's default system path of your application database.
private static String DB_PATH = "/data/data/com.bnsnsports.fanstats/databases/";
                                            
private static String DB_NAME = "stats.db";

//Table names
private static final String TABLE_TEAMS = "teams";
private static final String TABLE_STATS = "stats";
private static final String TABLE_LEVELS = "levels";
private static final String TABLE_STATVALUES = "stat_values";
private static final String TABLE_STATPIVOT = "statpivot";
private static final String TABLE_STATLEVEL = "statlevel";





// Contacts Table Columns names
private static final String KEY_ID = "_id";
private static final String KEY_NAME = "name";
private static final String KEY_GRP = "group_id";
 
private SQLiteDatabase myDataBase;
 
private final Context myContext;
 
/**
  * Constructor
  * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
  * @param context
  */
public DatabaseHelper(Context context) {
 
super(context, DB_NAME, null, 12);
this.myContext = context;
}	
 
/**
  * Creates a empty database on the system and rewrites it with your own database.
  * */
public void createDataBase() throws IOException{


       //myContext.deleteDatabase(DB_NAME);
	
 boolean dbExist = checkDataBase();
 
if(dbExist){
//do nothing - database already exist
	this.getWritableDatabase();
}else{
 
//By calling this method and empty database will be created into the default system path
//of your application so we are gonna be able to overwrite that database with our database.
this.getWritableDatabase();
 
try {
 
copyDataBase();
 
} catch (IOException e) {
 
throw new Error("Error copying database");
 
}
}
 
}
 
/**
  * Check if the database already exist to avoid re-copying the file each time you open the application.
  * @return true if it exists, false if it doesn't
  */
public boolean checkDataBase(){
 
SQLiteDatabase checkDB = null;
 
try{
String myPath = DB_PATH + DB_NAME;
checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
 
}catch(SQLiteException e){
 
//database does't exist yet.
 
}
 
if(checkDB != null){
 
checkDB.close();
 
}
 
return checkDB != null ? true : false;
}
 
/**
  * Copies your database from your local assets-folder to the just created empty database in the
  * system folder, from where it can be accessed and handled.
  * This is done by transfering bytestream.
  * */
private void copyDataBase() throws IOException{
 
//Open your local db as the input stream
InputStream myInput = myContext.getAssets().open(DB_NAME);
 
// Path to the just created empty db
String outFileName = DB_PATH + DB_NAME;
 
//Open the empty db as the output stream
OutputStream myOutput = new FileOutputStream(outFileName);
 
//transfer bytes from the inputfile to the outputfile
byte[] buffer = new byte[1024];
int length;
while ((length = myInput.read(buffer))>0){
myOutput.write(buffer, 0, length);
}
 
//Close the streams
myOutput.flush();
myOutput.close();
myInput.close();
 
}
 
public void openDataBase() throws SQLException{
 
//Open the database
String myPath = DB_PATH + DB_NAME;
myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
myDataBase.execSQL( "PRAGMA encoding = \"UTF-16\"" );
 
}

@Override
public synchronized void close() {
 
if(myDataBase != null)
myDataBase.close();
 
super.close();
 
}
 
@Override
public void onCreate(SQLiteDatabase db) {
	
}
 
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	
	 myContext.deleteDatabase(DB_NAME);
 
}
 
// Add your public helper methods to access and get content from the database.
// You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
// to you to create adapters for your views.
 



// Getting All Teams
public List<Team> getAllTeams() {
    List<Team> teamList = new ArrayList<Team>();
    // Select All Query
    String selectQuery = "SELECT  _id,name,group_id,points " +
    		",played,won,lost,drawn,gf,ga,predicted_win,predicted_ru " +
    		" FROM " + TABLE_TEAMS;
    
    String myPath = DB_PATH + DB_NAME;
    
    myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    //SQLiteDatabase db = this.getWritableDatabase();
    
    Cursor cursor = myDataBase.rawQuery(selectQuery, null);

    // looping through all rows and adding to list
    if (cursor.moveToFirst()) {
        do {
            Team team = new Team();
            team.setID(Integer.parseInt(cursor.getString(0)));
            
            //team.setID(cursor.getInt(0));
            
            team.setName(cursor.getString(1));
            team.setGroup(cursor.getString(2));
            team.setpoints(Integer.parseInt(cursor.getString(3)));
            team.setplayed(Integer.parseInt(cursor.getString(4)));
            team.setwon(Integer.parseInt(cursor.getString(5)));
            team.setlost(Integer.parseInt(cursor.getString(6)));
            team.setdrawn(Integer.parseInt(cursor.getString(7)));
            team.setgf(Integer.parseInt(cursor.getString(8)));
            team.setga(Integer.parseInt(cursor.getString(9)));
            team.setpw(Integer.parseInt(cursor.getString(10)));
            team.setpr(Integer.parseInt(cursor.getString(11)));
            
            // Adding contact to list
            teamList.add(team);
        } while (cursor.moveToNext());
    }
    cursor.close();
    // return teams
    return teamList;
}



//Getting All Teams
public List<Team> getAllTeamsRanking() {
 List<Team> teamList = new ArrayList<Team>();
 // Select All Query
 String selectQuery = "SELECT  name,ranking,rank_pts FROM " + TABLE_TEAMS + " ORDER BY ranking";
 
 String myPath = DB_PATH + DB_NAME;
 
 myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

 //SQLiteDatabase db = this.getWritableDatabase();
 
 Cursor cursor = myDataBase.rawQuery(selectQuery, null);

 // looping through all rows and adding to list
 if (cursor.moveToFirst()) {
     do {
         Team team = new Team();
         //team.setID(Integer.parseInt(cursor.getString(0)));
         
         //team.setID(cursor.getInt(0));
         
         team.setName(cursor.getString(0));
         team.setranking(cursor.getInt(1));
         team.setrankpts(cursor.getInt(2));
         
         // Adding contact to list
         teamList.add(team);
     } while (cursor.moveToNext());
 }
 cursor.close();
 // return teams
 return teamList;
}

public Answer getQnA(int Level)
{
	List<Choice> choices = new ArrayList<Choice>();
	
    Answer answer = new Answer();
    
    String statid;
    String statlevelid="";
    String lang;
    
    String statLevel="SELECT statlevel FROM " + TABLE_STATLEVEL + " WHERE level=" + Level;
           		
    
    String myPath = DB_PATH + DB_NAME;
    
    myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    
  //SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor_statlevel = myDataBase.rawQuery(statLevel, null);
    
    cursor_statlevel.moveToFirst();
    statlevelid=cursor_statlevel.getString(0);
       
    cursor_statlevel.close();
    
    Log.d("database statlevel",statlevelid);
    
    lang=fanstatsapp.globallang;
    
    String statQuery = "SELECT stat,question " +
            " FROM " + TABLE_STATS + " WHERE statlevel='" + statlevelid + "'" +
            " AND language='" + lang + "'" +
            " ORDER BY random() limit 1";

    //SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor_stat = myDataBase.rawQuery(statQuery, null);
    
    cursor_stat.moveToFirst();
    statid=cursor_stat.getString(0);
    answer.setquestion(cursor_stat.getString(1));
    
    cursor_stat.close();
    
    Log.d("database statid",statid);
    
    String selectQuery = "SELECT team ,value" +
            " FROM " + TABLE_STATPIVOT + 
            " WHERE Stat= '" + statid + "'" +
            " ORDER BY random() limit 4";

    
    Cursor cursor = myDataBase.rawQuery(selectQuery, null);
    
    String values;
    String prevvalues;
    

    int highest=0;
    
    // looping through all rows and adding to list
    //
    // get highest score
    if (cursor.moveToFirst()) {
        do {
        	
        	
        	
        	String team=cursor.getString(0).toLowerCase();
        	
        	Log.d("DB","Team is " + team);
        	
        	Choice choice1 = new Choice();
        	
        	values=Integer.toString(cursor.getInt(1));
        	prevvalues=Integer.toString(highest);
        	
        	Log.d("DB","Value " + values + " " + prevvalues);
        	if ( cursor.getInt(1) > highest ) 
        	{
        	    choice1.setcorrect("Y");
        	    answer.setanswer(team);
        	    Log.d("DB","Greater than Team " + team);
        	    highest = cursor.getInt(1);
        	}
        	else 
        	{
        		choice1.setcorrect("N");
        	}
        	    
        	choice1.setteam(cursor.getString(0).toLowerCase());
        	
        	
        	
        	choices.add(choice1);
        } while (cursor.moveToNext());
    }
    cursor.close();
	myDataBase.close();    
	answer.setchoices(choices);
	return answer;
}
  
public Level getLevel()
{
	List<Level> levels = new ArrayList<Level>();
	
	
    
    String lvlQuery = "SELECT MAX(_id)+1 " +
            " FROM " + TABLE_STATLEVEL + " WHERE complete=1 ";
    		
    
    String myPath = DB_PATH + DB_NAME;
    
    myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    
    Level level = new Level();

    //SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = myDataBase.rawQuery(lvlQuery, null);
    
    if (cursor.moveToFirst()) {
        do {
        	       	
            
        	
            level.setid(cursor.getInt(0));
            //level.setstatus(cursor.getString(1));
        	
        	//levels.add(level);
        	
        } while (cursor.moveToNext());
    }
    cursor.close();
	    
    myDataBase.close(); 
    
	return level;
}

/* save level need to change the type
 * to match DB and add a new get procedure
 * 
 */
public void savelevel() {
	 
	 int    id;
	 String team1;
	 int team1_score;
	 String team2;
	 int team2_score;
	 String fixturedate;
	 //Fixture fixture;
	 Cursor cursor;
	 
	 
	  
	 String updateQuery= "update " + TABLE_STATLEVEL + " SET COMPLETE=1 "
			             + ",HIGHSCORE=" + fanstatsapp.completedtime
			             + " WHERE level = " + Integer.toString(fanstatsapp.chosenlevel);
			             //+ " AND (COMPLETE IS NULL OR HIGHSCORE < " + fanstatsapp.completedtime + ")";
	 
	 String myPath = DB_PATH + DB_NAME;
	 
	 Log.e("DB", updateQuery);
	 
	 Log.e("DB", "Open Database");
	 myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
	 //SQLiteDatabase myDataBase = this.getWritableDatabase();
	 
	 Log.e("DB", "Opened Database");

	 //SQLiteDatabase db = this.getWritableDatabase();
	 
	 

	 //Iterator<Fixture> it = fixtureList.iterator();
	 
	 final ContentValues values = new ContentValues();
	 		 
	// values.put("complete",1);
	       
	        try {
	        	
	        	myDataBase.beginTransaction();
	       	    myDataBase.execSQL(updateQuery);
	       	    myDataBase.setTransactionSuccessful();
	       	    myDataBase.endTransaction();
	       	 
	       	 /*
	        	myDataBase.beginTransaction();
	            boolean state = myDataBase.update(TABLE_STATLEVEL, values, 
	            		" level = " + Integer.toString(levelinfo._id),null)>0;
	            myDataBase.setTransactionSuccessful();
	          */
	            
	        } 
	        catch(Exception e)
    	    {
    	        //messageBox("Answer is wrong", e.getMessage());
	        	Log.e("DB",e.getMessage());
    	    }
	 finally {
	        	//myDataBase.endTransaction();
	        	//myDataBase.close();
	        	
	        }
		 
		 

		 
	 
	 }
public void getScore()
{
	
	
	//double score=0.0;
    
    String lvlQuery = "SELECT SUM(ROUND(highscore,2)) as highscore " +
            " FROM " + TABLE_STATLEVEL + " WHERE highscore IS NOT NULL";
	
	//String lvlQuery = "SELECT count(*) as highscore " +
	//	           " FROM " + TABLE_STATLEVEL + " WHERE highscore <>'' ";
    		
    
    String myPath = DB_PATH + DB_NAME;
    
    myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    
    

    //SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = myDataBase.rawQuery(lvlQuery, null);
    
    if (cursor.moveToFirst()) {
        do {
        	       	
        	fanstatsapp inst = fanstatsapp.getInstance();
        	
        	inst.setst(cursor.getDouble(0));
        	
            //level.setstatus(cursor.getString(1));
            Log.d("DB","score is "+fanstatsapp.scoretotal );
        	
        	//levels.add(level);
        	
        } while (cursor.moveToNext());
    }
    cursor.close();
	    
    //myDataBase.close(); 
    
	//return score;
}
	}


