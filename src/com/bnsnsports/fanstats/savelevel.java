package com.bnsnsports.fanstats;

import android.os.AsyncTask;
import android.widget.Toast;
import android.content.Context;
import android.app.Application;

public class savelevel extends AsyncTask<Void, Void, Boolean> {

//int result;

	    //ProgressDialog dialog;
	    Context c;
	    public savelevel(Context context)
	    {
	    	c=context;
	    }
	    
	DatabaseHelper db = new DatabaseHelper(c);	
	 
	

	    @Override
	    protected Boolean doInBackground(Void... params) {
	        
	    	Level level;
	    	Integer retval;
	    	double  score=0.0;
	    	
	    	DatabaseHelper db = new DatabaseHelper(c);	
   		    
	    	try
    		{
    		 db.createDataBase();
    		
    		 db.savelevel();
    		 
    		}
	    	catch(Exception e)
    	    {
    	        //messageBox("Answer is wrong", e.getMessage());
            	throw new Error( e.getMessage());
    	    }
    		
	    	//retval=Integer.level._id;
	    	
	    	// auto boxing convert int to Integer
	    	 
	    	 
	        return true;
	    }

	    protected void onPreExecute() {
	        
	    }

	    protected void onPostExecute(Boolean result) {
	        
	    	result=true;
	    }
	}


