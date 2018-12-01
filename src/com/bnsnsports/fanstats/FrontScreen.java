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
import android.content.res.Resources;
import android.os.StrictMode;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.graphics.Typeface;
import com.appflood.AppFlood;
import com.appflood.AppFlood.AFEventDelegate;
import com.appflood.AppFlood.AFRequestDelegate;



public class FrontScreen extends Activity {

	
    TextView timerTextView;
    long startTime = 0;
    public String status;
    

    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frontscreen);
        
        if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
        
        //App Key=KcOyblJXoH2wKgzu
        //Secret Key=CCyP1AeI495aL53ca7b3d

        AppFlood.initialize(this, "KcOyblJXoH2wKgzu", "CCyP1AeI495aL53ca7b3d", AppFlood.AD_FULLSCREEN);
        
        Typeface face = Typeface.createFromAsset(getAssets(),
                    "fontd/Mega.otf");
        
        Button btnnewgame = (Button) findViewById(R.id.newgame);
        btnnewgame.setTypeface(face);
        
        btnnewgame.setOnClickListener(new OnClickListener()                      
        	   {
        	             @Override
        	             public void onClick(View v)
        	             {
        	            	Intent t = new Intent(FrontScreen.this, LevelsActivity.class);
        	     	    	startActivity(t);
        	             	
        	             } 
        	   });
        
        /*Button btnpred = (Button) findViewById(R.id.prediction);
        btnpred.setTypeface(face);
        
        Button btnsettings = (Button) findViewById(R.id.settings);
        btnsettings.setTypeface(face);
        */
    }

    
    
    
    
    
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
