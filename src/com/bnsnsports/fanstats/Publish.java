package com.bnsnsports.fanstats;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.appflood.AppFlood;
import com.appflood.AppFlood.AFEventDelegate;
import com.appflood.AppFlood.AFRequestDelegate;

public class Publish extends Activity{
	
	private String servertext;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.publish);
		
		
		//setContentView(R.layout.main);

        //login = (Button) findViewById(R.id.login);
        //username = (EditText) findViewById(R.id.username);
        //password = (EditText) findViewById(R.id.password);

        //login.setOnClickListener(new OnClickListener() {

           // @Override
          //  public void onClick(View v) {

                String   mUsername = ""; //username.getText().toString();
                String  mPassword = ""; //password.getText().toString();

                tryLogin(mUsername, mPassword);
                
                TextView servertextview = (TextView) findViewById(R.id.servertext);
                
                
                //fanstatsapp.scoretotal = fanstatsapp.completedtime;
                Typeface face = Typeface.createFromAsset(getAssets(),
                        "fontd/Mega.otf");
                servertextview.setTypeface(face);
                
                servertextview.setText(servertext);
                
                Button levels = (Button) findViewById(R.id.levels);
                levels.setTypeface(face);
                
                                
                levels.setOnClickListener(new OnClickListener()                      
                	   {
                	             @Override
                	             public void onClick(View v)
                	             {
                	            	
                	            	Intent t = new Intent(Publish.this, LevelsActivity.class);
                	            	t.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                	            	t.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                	            	fanstatsapp.levelcompleted=false;
                	     	    	startActivity(t);
                	             	//finish();
                	             } 
                	   });
            //}
        //});
                AppFlood.showFullScreen(this);
                
                //AppFlood.showBanner(activity,AppFlood.BANNER_POSITION_BOTTOM);
                AppFlood.showBanner(this, AppFlood.BANNER_POSITION_BOTTOM, AppFlood.BANNER_SMALL);
	}

	protected void tryLogin(String mUsername, String mPassword)
    {           
        HttpURLConnection connection;
       OutputStreamWriter request = null;

            URL url = null;   
            String response = null;         
            String parameters = "username="+mUsername+"&password="+mPassword;   

            try
            {
                url = new URL("http://www.stat-tastic.com/hs.php?hs="+Double.toString(fanstatsapp.scoretotal));
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestMethod("POST");    

                request = new OutputStreamWriter(connection.getOutputStream());
                //  request.write(parameters);
                request.flush();
                request.close();            
                String line = "";               
                InputStreamReader isr = new InputStreamReader(connection.getInputStream());
                BufferedReader reader = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                while ((line = reader.readLine()) != null)
                {
                    sb.append(line + "\n");
                }
                // Response from server after login process will be stored in response variable.                
                response = sb.toString();
                // You can perform UI operations here
                servertext = response;
                //Toast.makeText(this,"Message from Server: \n"+ response, 0).show();             
                isr.close();
                reader.close();

            }
            catch(IOException e)
            {
                // Error
            }
    }
}
