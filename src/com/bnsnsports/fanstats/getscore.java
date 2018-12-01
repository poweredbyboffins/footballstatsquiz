package com.bnsnsports.fanstats;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import android.content.Context;
import android.app.Application;

public class getscore extends AsyncTask<Void, Void, Double> {

//int result;

	    //ProgressDialog dialog;
	    Context c;
	    public getscore(Context context)
	    {
	    	c=context;
	    }
	    
	DatabaseHelper db = new DatabaseHelper(c);	
	 
	

	    @Override
	    protected Double doInBackground(Void... params) {
	        
	    	Level level;
	    	Integer retval;
	    	double  score=0.0;
	    	
	    	DatabaseHelper db = new DatabaseHelper(c);	
   		    
	    	try
    		{
    		 db.createDataBase();
    		
    		 db.getScore();
    		 
    		}
	    	catch(Exception e)
    	    {
    	        //messageBox("Answer is wrong", e.getMessage());
            	throw new Error( e.getMessage());
    	    }
    		
	    	//retval=Integer.level._id;
	    	
	    	// auto boxing convert int to Integer
	    	 
	    	Log.e("DB", "score is " + Double.toString(score));
	    	 
	        return score;
	    }

	    protected void onPreExecute() {
	        
	    }

	    protected void onPostExecute(Double result) {
	        
	    	if (result.equals(0))
	    		fanstatsapp.scoretotal=0;
	    	else
	    		fanstatsapp.scoretotal = result;
	        
	    }
	}

