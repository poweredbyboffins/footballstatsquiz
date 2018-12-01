package com.bnsnsports.fanstats;

import android.app.Application;
import android.util.Log;
import android.os.AsyncTask;

public class fanstatsapp extends Application {
	
	private int countcorrect;
	private int errorcount;
	public static boolean levelcompleted;
	public static boolean toomanyerrors;
	
	public static boolean wronganswer;
	public static int level=1;
	public static int chosenlevel;
	public static Level leveldata;
	public static String globallang;
	public static double completedtime;
	public static double scoretotal;
	
	
	private static fanstatsapp instance = null;
	 
	 @Override
	 public void onCreate() {
	  super.onCreate();
	  instance = this;
	  Log.d("APPLICATION","constructor background start");
	  getappdbdata task = new  getappdbdata(this);
	  Log.d("APPLICATION","constructor background end");
	  task.execute();
	  Log.d("APPLICATION","execute finished");
	  
	  /*if (leveldata==null)*/
	      //leveldata._id=level;
	      
	      Log.d("APPLICATION","level data");
	      
	      fanstatsapp.globallang=this.getString(R.string.language);  

	 }
	 
	 public static fanstatsapp getInstance() {
	  return instance;
	 }
	
	 //public static final int countcorrect=0;
	 public void initcc ()
	 {
	    	this.countcorrect=0;
	 }
	
	public int getcc() {
		Log.d("APPLICATION","count is"+Integer.toString(countcorrect));
        return countcorrect;
    }

    public void setcc(int countcorrect) {
        this.countcorrect++;
        Log.d("APPLICATION","set count is"+Integer.toString(countcorrect));
    }
    
    public void initec ()
    {
    	this.errorcount=0;
    }

    
    public int getec() {
    	Log.d("APPLICATION","error count is"+Integer.toString(errorcount));
        return errorcount;
    }

    public void setec(int errorcount) {
        this.errorcount++;
        Log.d("APPLICATION","error count is"+Integer.toString(errorcount));
    }
    
    public void setct(double completedtime) {
        fanstatsapp.completedtime=completedtime;
        Log.d("APPLICATION","completedtime"+Double.toString(fanstatsapp.completedtime));
    }
    
    public void setst(double scoretotal) {
        fanstatsapp.scoretotal=scoretotal;
        Log.d("APPLICATION","scoretotal"+Double.toString(fanstatsapp.scoretotal));
    }
		  private MyStateManager myStateManager = new MyStateManager();

		  public MyStateManager getStateManager(){
		    return myStateManager ;
		  }
		}

		class MyStateManager {

		  MyStateManager() {
		    /* this should be fast */
		  }

		  String getState() {
		    /* if necessary, perform blocking calls here */
		    /* make sure to deal with any multithreading/synchronicity issues */
            String state;
            
            state="test";
		    

		    return state;
		  }
}
		


