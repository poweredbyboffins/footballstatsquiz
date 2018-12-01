package com.bnsnsports.fanstats;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ImageButton;
import java.lang.Thread;
import java.util.List;
import android.app.AlertDialog;
import android.util.Log;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.Resources;
import android.os.StrictMode;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.graphics.Typeface;
//import android.widget.GridViewCustomAdapter;



public class LevelsActivity extends Activity {

	
    TextView timerTextView;
    long startTime = 0;
    public String status;
    

    GridView gridView;
    GridViewCustomAdapter gridViewCustomeAdapter;
   
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levels);
        
        if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
        
        //Button btnnewgame = (Button) findViewById(R.id.level1);
        
                gridView=(GridView)findViewById(R.id.gridViewCustom);
                
                // Create the Custom Adapter Object
                gridViewCustomeAdapter = new GridViewCustomAdapter(this);
                // Set the Adapter to GridView
                gridView.setAdapter(gridViewCustomeAdapter);
                
                
                 
                // Handling touch/click Event on GridView Item
                  gridView.setOnItemClickListener(new OnItemClickListener() {

                   @Override
                   
                   public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                   String Locked;
                   
                   
                   if(position+1 > fanstatsapp.level)
                            Toast.makeText(getApplicationContext(),"Level is Locked", Toast.LENGTH_SHORT).show();
                    else
                    {
                    fanstatsapp.chosenlevel=position+1;
                    Intent t = new Intent(LevelsActivity.this, FrontActivity.class);
                    t.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            	t.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	     	    	startActivity(t);
                    }
                   }
                  });


           }

   /*     
        
        DatabaseHelper db = new DatabaseHelper(this);	
		 
		db.createDataBase();
		        		
		
		List<Level> levels = db.getLevels();
		
		for (Level lvl : levels)
		{
			int lvlid=lvl.getid();
			String statusid=lvl.getstatus();
			
			Button btn = new Button(this);
			btn.setId(lvlid);
			btn.setText("Level "+Integer.toString(lvlid));
			LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, 
                    LayoutParams.MATCH_PARENT);
			btn.setLayoutParams(lp);
	            
            lView.addView(btn,lp);
            
		
		}
		*/
        
       /* btnnewgame.setOnClickListener(new OnClickListener()                      
        	   {
        	             @Override
        	             public void onClick(View v)
        	             {
        	            	Intent t = new Intent(LevelsActivity.this, FrontActivity.class);
        	     	    	startActivity(t);
        	             	
        	             } 
        	   });
        
        
    }
    */

    
    
    
    
    
    private void messageBox(String method, String message)
	{
	    Log.d("EXCEPTION: " + method,  message);

	    AlertDialog.Builder messageBox = new AlertDialog.Builder(this);
	    messageBox.setTitle(method);
	    messageBox.setMessage(message);
	    messageBox.setCancelable(false);
	    messageBox.setNeutralButton("OK", null);
	    messageBox.show();
	}	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.front, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    switch (item.getItemId()) {
        case R.id.action_search:
        // search
        return true;
    case R.id.action_settings:
        // settings
        return true;
    default:
        return super.onOptionsItemSelected(item);
    }
    
    
    
    } 
    
}
