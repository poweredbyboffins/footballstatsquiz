package com.bnsnsports.fanstats;

import android.os.AsyncTask;
import android.widget.Toast;
import android.content.Context;
import android.app.Application;

public class getappdbdata extends AsyncTask<Void, Void, Integer> {

//int result;

	    //ProgressDialog dialog;
	    Context c;
	    public getappdbdata(Context context)
	    {
	    	c=context;
	    }
	    
	DatabaseHelper db = new DatabaseHelper(c);	
	 
	

	    @Override
	    protected Integer doInBackground(Void... params) {
	        
	    	Level level;
	    	Integer retval;
	    	double  score;
	    	
	    	DatabaseHelper db = new DatabaseHelper(c);	
   		    
	    	try
    		{
    		 db.createDataBase();
    		
    		 
    		 level = db.getLevel();
    		 
    		}
	    	catch(Exception e)
    	    {
    	        //messageBox("Answer is wrong", e.getMessage());
            	throw new Error( e.getMessage());
    	    }
    		
	    	//retval=Integer.level._id;
	    	
	    	// auto boxing convert int to Integer
	    	 
	    	 
	        return level._id;
	    }

	    protected void onPreExecute() {
	        
	    }

	    protected void onPostExecute(Integer result) {
	        
	    	if (result.equals(0))
	    		fanstatsapp.level=1;
	    	else
	    		fanstatsapp.level = result;
	        
	    }
	}

