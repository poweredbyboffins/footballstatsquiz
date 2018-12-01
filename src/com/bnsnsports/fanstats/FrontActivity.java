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
import java.util.Locale;


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
import android.app.Application;
import android.speech.tts.TextToSpeech;
import android.media.MediaPlayer;
import android.content.Context;
import com.appflood.AppFlood;
import com.appflood.AppFlood.AFEventDelegate;
import com.appflood.AppFlood.AFRequestDelegate;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import android.widget.RelativeLayout;
import android.view.Gravity;



public class FrontActivity extends Activity implements
TextToSpeech.OnInitListener {

	private Context context;
    TextView timerTextView;
    long startTime = 0;
    public String status;
    double score;
    
    private AdView adView;
    private static final String AD_UNIT_ID = "ca-app-pub-4398753860272137/4096016609";
    
    
    public static String text;
    //EditText et;
    TextToSpeech tts;
    
    private int result=0;
    
    //double scorevalue; 
    

    //runs without a timer by reposting this handler at the end of the runnable
    //public void delayedStart(final int delay){
        //final    
        Handler timerHandler = new Handler();
     //final 
     Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
        	
            long  millis = System.currentTimeMillis() - startTime;
            score = (double) millis / 1000 ;
            int seconds = (int) (millis / 1000);
            double remainder = score - (double) seconds;
            int hundreds = (int) remainder*1000;
            int minutes = seconds / 60;
            seconds = seconds % 60;
            double completedtime;

            if ((seconds == 0 ) && ( minutes ==1 ))
            {
            	timerHandler.removeCallbacks(timerRunnable);
            	//timerHandler.removeCallbacks(this);
            	getFragmentManager().beginTransaction()
                .replace(R.id.container, new TimeoutFragment())
                .commit();
            	
            }
            else if  (fanstatsapp.toomanyerrors)
            {
            	timerHandler.removeCallbacks(timerRunnable);
            	//timerHandler.removeCallbacks(timerRunnable);
            	getFragmentManager().beginTransaction()
                .replace(R.id.container, new WrongFragment())
                .commit();
            }
            else if (fanstatsapp.levelcompleted)
            {
            	/* only increment if latest level */
            	if (fanstatsapp.level==fanstatsapp.chosenlevel)
            	    fanstatsapp.level++;
            	
            	timerHandler.removeCallbacks(timerRunnable);
            	//timerHandler.removeCallbacks(this);
            	
                fanstatsapp inst = fanstatsapp.getInstance();
            	
            	// save score - less than a minute
            	completedtime=60-score;
            	
            	completedtime = Math.round(completedtime * 100);
            	completedtime = completedtime / 100;
            	inst.setct(completedtime);
            	
            	
            	//Log.d("D","SaveLevel Call Start");
            	//db.savelevel(fanstatsapp.completedtime);
            	//
            	//savelevel leveltask = new  savelevel(context);
            	try
                {
                 DatabaseHelper db = new DatabaseHelper(context);	
       		 
        		 //db.createDataBase();
        		         		
        		 db.savelevel();
        		 db.getScore();
                }
                catch(Exception e)
        	    {
        	        messageBox("Error in save level", e.getMessage());
                	//throw new Error( e.getMessage());
        	    }
            	finally
            	{
            	getFragmentManager().beginTransaction()
                .replace(R.id.container, new ClearedFragment())
                .commit();
            	}
                
            }
            
            else
            {
            	/*if (fanstatsapp.wronganswer)
                {
                	seconds=seconds+3;
                	fanstatsapp.wronganswer=false;
                	
                }	*/	
            //timerTextView.setText(String.format("%d:%02d:%02d", minutes, seconds, hundreds));
            	score = Math.round(score * 100);
            	score = score / 100;
            	timerTextView.setText(String.format("%.02f", score));

            timerHandler.postDelayed(this, 50);
            
            }
        }
     };
    //}
    @Override
    protected void onPause()
    {
    	super.onPause();
    	//delayedStart(1);
    	timerHandler.removeCallbacks(timerRunnable);
    	finish();
    }
    protected void onStop()
    {
    	super.onStop();
    	timerHandler.removeCallbacks(timerRunnable);
    	finish();
    }
    @Override
    protected void onResume()
    {
    	super.onResume();
    	//delayedStart(1);
    	//startTime = System.currentTimeMillis();
    	//timerHandler.postDelayed(timerRunnable, 0);
    	
    	//Intent t = new Intent(this, FrontActivity.class);
    	
	    //	startActivity(t);
    	//timerTextView.setText(String.format("%.02f", score));
    	timerTextView = (TextView) findViewById(R.id.timerTextView);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fontd/Mega.otf");
        timerTextView.setTypeface(face);
        	
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
    }
    
    protected void onRestart()
    {
    	super.onResume();
    	//delayedStart(1);
    	//startTime = System.currentTimeMillis();
    	//timerHandler.postDelayed(timerRunnable, 0);
    	
    	//Intent t = new Intent(this, FrontActivity.class);
    	
	    //	startActivity(t);
    	//timerTextView.setText(String.format("%.02f", score));
    	timerTextView = (TextView) findViewById(R.id.timerTextView);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fontd/Mega.otf");
        timerTextView.setTypeface(face);
        	
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
    }
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	
        try
        {
        	
        
        setContentView(R.layout.activity_front);
        
        context = this;
        
        Log.d("MAIN_ACTIVITY ","MAIN CALL");
        
        if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
        
     // Create an ad.
	    adView = new AdView(this);
	    adView.setAdSize(AdSize.BANNER);
	    adView.setAdUnitId(AD_UNIT_ID);
	    
        /*
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        Typeface face = Typeface.createFromAsset(getAssets(),
                "fontd/Mega.otf");
        timerTextView.setTypeface(face);
        	
        startTime = System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
        //delayedStart(1);
        */
	 // Add the AdView to the view hierarchy. The view will have no size
	    // until the ad is loaded.
	    RelativeLayout layout = (RelativeLayout) findViewById(R.id.rlay);
	    //layout.setGravity(Gravity.BOTTOM);
	    layout.addView(adView);
	    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) adView.getLayoutParams();
	    params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
	    adView.setLayoutParams(params);
	    
	    // Create an ad request. Check logcat output for the hashed device ID to
	    // get test ads on a physical device.
	    AdRequest adRequest = new AdRequest.Builder()
	        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
	//        .addTestDevice("INSERT_YOUR_HASHED_DEVICE_ID_HERE")
	        .build();
	
	    // Start loading the ad in the background.
	    adView.loadAd(adRequest);
        
        //+fanstatsapp.globallang=this.getString(R.string.language);
        
        getFragmentManager().popBackStack();
        //if (savedInstanceState == null) {
        	
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
            
         // Start loading the ad in the background.
    	    adView.loadAd(adRequest);
        //}
        }
        
            //ConvertTextToSpeech();   
        	catch(Exception e)
    	    {
    	        messageBox("Error", e.getMessage());
            	//throw new Error( e.getMessage());
    	    }
        
            //tts = new TextToSpeech(this, this);
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
    
    

    

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

    	public String tagval1;
        public String tagval2;
        public String tagval3;
        public String tagval4;
        public boolean result;
        public String status; 
        
    	//public int    countcorrect=0;
        
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_front, container, false);
            
            
            try
    		{
            DatabaseHelper db = new DatabaseHelper(getActivity());	
    		
            boolean dbExist = db.checkDataBase();
            
            if(dbExist)
            {
            	
            }
            else	
    		    db.createDataBase();
    		         		
    		/* need to get teams and question
    		 * create a bundle
    		 * pass this to the fragment
    		 */
    		Answer answer = db.getQnA(fanstatsapp.chosenlevel);
    		
    		TextView question = (TextView) rootView.findViewById(R.id.question);
    		text = answer.getquestion();
    		//getActivity().ConvertTextToSpeech();
            question.setText(answer.getquestion());
            Typeface face = Typeface.createFromAsset(getActivity().getAssets(),
                    "fontd/Mega.otf");
            question.setTypeface(face);
            
            List<Choice> choicelist = answer.getchoices();
            
            int cnt=0;
           
            Resources res = getResources();
            int resId;
            
            
            
        	for (Choice teamchoice : choicelist)
        	{
        	    cnt++;	
        	    
        	    String teamid = teamchoice.getteam();
        	    //teamid.replaceAll("\\s+","");
        	    String teamnospace=teamid.replaceAll("\\s+","");
        		
        		resId = res.getIdentifier("Button0"+Integer.toString(cnt), "id","com.bnsnsports.fanstats" );
        		ImageButton ibtn1 = (ImageButton) rootView.findViewById(resId);
        		
        		resId = res.getIdentifier(teamnospace, "drawable","com.bnsnsports.fanstats" );
        		
        		ibtn1.setBackgroundResource(resId);
        		
        		Log.d("DB","Team comparison " + answer.getanswer() + " " + teamid);
        		if (answer.getanswer().equals( teamid))
        		{
        			Log.d("TAG","Match "+teamid);
        		    ibtn1.setTag(teamid);
        		}
        		else
        			ibtn1.setTag("NOMATCH");
        		
        		
                      
        	}
        	
        	fanstatsapp inst = fanstatsapp.getInstance();
        	
            //rootView = getActivity().findViewById(R.id.container);
        	
        	//TextView question = (TextView) rootView.findViewById(R.id.question);
            //question.setText("Who scored the most goooals");
        	ImageButton btn1 = (ImageButton) rootView.findViewById(R.id.Button01);
            tagval1=(String) btn1.getTag();
            btn1.setOnClickListener(new OnClickListener()
            	   {
            	             @Override
            	             public void onClick(View v)
            	             {
            	            	 
            	             	Log.d("TAG",tagval1);       
            	             	fanstatsapp inst = fanstatsapp.getInstance();
            	             	/* not equals no match ie OK*/
            	             	//fanstatsapp inst = fanstatsapp.getInstance();
            	             	if ( !tagval1.equals("NOMATCH"))
            	                 {
            	             		status="Correct";
            	             	// set
            	             		fanstatsapp.wronganswer=false;
            	             		
            	             		inst.setcc(inst.getcc());
            	             		
            	             		displayfrag("OK");
            	             		displayfrag("NQ");
            	                 }
            	             	else
            	             	{
            	             		inst.setec(inst.getec());
            	             		fanstatsapp.wronganswer=true;
            	             		status="Wrong";
            	             	}
            	             } 
            	   });
            ImageButton btn2 = (ImageButton) rootView.findViewById(R.id.Button02);
            tagval2=(String) btn2.getTag();
            
            btn2.setOnClickListener(new OnClickListener()
            	   {
            	             @Override
            	             public void onClick(View v)
            	             {
            	            	 
            	             	Log.d("TAG",tagval2);       
            	             	//fanstatsapp inst = fanstatsapp.getInstance();
            	             	fanstatsapp inst = fanstatsapp.getInstance();
            	             	if ( !tagval2.equals("NOMATCH"))
            	                 {
            	             		status="Correct";
            	             		fanstatsapp.wronganswer=false;
            	             		
            	             		inst.setcc(inst.getcc());
            	             		displayfrag("OK");
            	             		displayfrag("NQ");
            	                 }
            	             	else
            	             	{
            	             		fanstatsapp.wronganswer=true;
            	             		inst.setec(inst.getec());
            	             		status="Wrong";
            	             	}
            	             } 
            	   });
            
            ImageButton btn3 = (ImageButton) rootView.findViewById(R.id.Button03);
            tagval3=(String) btn3.getTag();
            btn3.setOnClickListener(new OnClickListener()
            	   {
            	             @Override
            	             public void onClick(View v)
            	             {
            	            	 
            	             	Log.d("TAG",tagval3);       
            	             	
            	             	fanstatsapp inst = fanstatsapp.getInstance();
            	             	if ( !tagval3.equals("NOMATCH"))
            	                 {
            	             		status="Correct";
            	             		fanstatsapp.wronganswer=false;
            	             		
            	             		inst.setcc(inst.getcc());
            	             		displayfrag("OK");
            	             		displayfrag("NQ");
            	                 }
            	             	else
            	             	{
            	             		inst.setec(inst.getec());
            	             		fanstatsapp.wronganswer=true;
            	             		status="Wrong";
            	             	}
            	             } 
            	   });
            
            ImageButton btn4 = (ImageButton) rootView.findViewById(R.id.Button04);
            tagval4=(String) btn4.getTag();
            btn4.setOnClickListener(new OnClickListener()
            	   {
            	             @Override
            	             public void onClick(View v)
            	             {
            	            	 
            	             	Log.d("TAG",tagval4);       
            	             	fanstatsapp inst = fanstatsapp.getInstance();
            	             	if ( !tagval4.equals("NOMATCH"))
            	                 {
            	             		status="Correct";
            	             		fanstatsapp.wronganswer=false;
            	             		
            	             		inst.setcc(inst.getcc());
            	             		displayfrag("OK");
            	             		displayfrag("NQ");
            	                 }
            	             	else
            	             	{
            	             		inst.setec(inst.getec());
            	             		fanstatsapp.wronganswer=true;
            	             		status="Wrong";
            	             	}
            	             } 
            	   });
            
            
            
            if (inst.getcc()>=10)
            {
            	inst.initcc();   
            	inst.initec(); 
            	fanstatsapp.levelcompleted=true;
            	
            	
            	//fanstatsapp.level++;
            	
            	
            	//db.savelevel(fanstatsapp.completedtime);
            	//savelevel leveltask = new  savelevel(getActivity());
            	//getscore scoretask = new  getscore(getActivity());
            	//fanstatsapp.scoretotal=db.getScore();
            	/*try
                {
                 //DatabaseHelper db = new DatabaseHelper(getActivity());	
       		 
        		 //db.createDataBase();
        		         		
        		 db.savelevel();
        		 db.getScore();
                }
                catch(Exception e)
        	    {
        	        //messageBox("Answer is wrong", e.getMessage());
                	throw new Error( e.getMessage());
        	    }
            	
            	displayfrag("LevelCleared");
            	*/
            }
            else if (inst.getec() >= 30)
            {
            	inst.initec(); 
            	inst.initcc();   
            	fanstatsapp.toomanyerrors=true;
            	
            	//displayfrag("Wrong");	
            }
            
    		}
            
            
            
            catch(Exception e)
    	    {
    	        //messageBox("Answer is wrong", e.getMessage());
            	throw new Error( e.getMessage());
    	    }
            
            return rootView;
        }
        public void displayfrag(String type)
        {
        
        try
        {
        result = true;
        	
        if (type == "OK") {
        	
        	getFragmentManager().beginTransaction()
            .replace(R.id.container, new StatusFragment())
            .commit();
        }
        
        if (type == "Wrong") {
        	getFragmentManager().beginTransaction()
            .replace(R.id.container, new WrongFragment())
            .commit();
        }
        
        if (type == "TimeOut") {
        	getFragmentManager().beginTransaction()
            .replace(R.id.container, new TimeoutFragment())
            .commit();
        }
        if (type == "NQ") {
        	
        	getFragmentManager().beginTransaction()
            .replace(R.id.container, new PlaceholderFragment())
            .commit();
           
        }
        if (type == "LevelCleared") {
        	getFragmentManager().beginTransaction()
            .replace(R.id.container, new ClearedFragment())
            .commit();
        }
        }
        
        catch (Exception e)
    	{
    		Log.e("displayfrag", "exception", e);
    	}
        } 
        
        
        
    }

    
    
    public static class StatusFragment extends Fragment {

        public StatusFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_correct, container, false);
            
            TextView txt = (TextView) rootView.findViewById(R.id.status);
            txt.setText("Correct");
            return rootView;
        }
    }
    
    public static class WrongFragment extends Fragment {

        public WrongFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
        	//AppFlood.showFullScreen(getActivity());
        	
            View rootView = inflater.inflate(R.layout.fragment_wrong, container, false);
            Button btnreset = (Button) rootView.findViewById(R.id.reset);
            TextView message = (TextView) rootView.findViewById(R.id.question);
            Typeface face = Typeface.createFromAsset(getActivity().getAssets(),
                    "fontd/Mega.otf");
            btnreset.setTypeface(face);
            message.setTypeface(face);
            
            btnreset.setOnClickListener(new OnClickListener()                      
            	   {
            	             @Override
            	             public void onClick(View v)
            	             {
            	            	//Intent t = new Intent(getActivity(), FrontActivity.class);
            	            	fanstatsapp.toomanyerrors=false;
            	            	//AppFlood.showFullScreen(getActivity());
            	            	Intent t = new Intent(getActivity(), LevelsActivity.class);
            	     	    	startActivity(t);
            	     	    	AppFlood.showFullScreen(getActivity());
            	             } 
            	   });
         
            
            return rootView;
            
        }
    }
    
    public static class TimeoutFragment extends Fragment {

        public TimeoutFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_timeout, container, false);
            Button btnreset = (Button) rootView.findViewById(R.id.reset);
            TextView message = (TextView) rootView.findViewById(R.id.question);
            Typeface face = Typeface.createFromAsset(getActivity().getAssets(),
                    "fontd/Mega.otf");
            btnreset.setTypeface(face);
            message.setTypeface(face);
            
            btnreset.setOnClickListener(new OnClickListener()                      
            	   {
            	             @Override
            	             public void onClick(View v)
            	             {
            	            	//Intent t = new Intent(getActivity(), FrontActivity.class);
            	            	Intent t = new Intent(getActivity(), LevelsActivity.class);
            	     	    	startActivity(t);
            	             	
            	             } 
            	   });
            fanstatsapp inst = fanstatsapp.getInstance();
            inst.initcc(); 
            inst.initec(); 
            
            return rootView;
        }
        
        
        
    }
    
    public static class ClearedFragment extends Fragment {
    	
    	
    double score;

        public ClearedFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_cleared, container, false);
            TextView message = (TextView) rootView.findViewById(R.id.question);
            //MediaPlayer mp = MediaPlayer.create(getActivity().getApplication(), R.raw.cheer);
            //mp.start();
            
            //Log.d("async","getscore");
            //getscore scoretask = new  getscore(getActivity());
            //Log.d("async","getscore finish");
            
           
            TextView scoreview = (TextView) rootView.findViewById(R.id.score);
            
            String textscore = scoreview.getText().toString();
            
            //fanstatsapp.scoretotal = fanstatsapp.completedtime;
            
            textscore = textscore + ": " + Double.toString(fanstatsapp.scoretotal);
            
            scoreview.setText(textscore);
            
            //fanstatsapp.scoretotal;
            
            Button btnreset = (Button) rootView.findViewById(R.id.next);
            Typeface face = Typeface.createFromAsset(getActivity().getAssets(),
                    "fontd/Mega.otf");
            btnreset.setTypeface(face);
            Button publish = (Button) rootView.findViewById(R.id.publish);
            publish.setTypeface(face);
            
            message.setTypeface(face);
            scoreview.setTypeface(face);
            
            btnreset.setOnClickListener(new OnClickListener()                      
            	   {
            	             @Override
            	             public void onClick(View v)
            	             {
            	            	
            	            	Intent t = new Intent(getActivity(), LevelsActivity.class);
            	            	t.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            	            	t.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            	            	fanstatsapp.levelcompleted=false;
            	     	    	startActivity(t);
            	             	//finish();
            	             } 
            	   });
         
            publish.setOnClickListener(new OnClickListener()                      
     	   {
     	             @Override
     	             public void onClick(View v)
     	             {
     	            	
     	            	Intent t = new Intent(getActivity(), Publish.class);
     	            	t.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
     	            	t.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
     	            	fanstatsapp.levelcompleted=false;
     	     	    	startActivity(t);
     	             	//finish();
     	             } 
     	   });
            
            return rootView;
        }
        
       /* public void OnAttach(Activity activity ){
            super.onAttach(activity );
            Log.d("Media","Media player");
            MediaPlayer mp = MediaPlayer.create(activity.getApplication(), R.raw.cheer);
            mp.start();
        }
        */
    }
    @Override
    public void onDestroy() {
        // Don't forget to shutdown!
    	//timerHandler.removeCallbacks(timerRunnable);
    	
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
     }
    //called when text to speech start
    @Override
    public void onInit(int status) {
        // TODO Auto-generated method stub
        if (status == TextToSpeech.SUCCESS) {
            //set Language
            result = tts.setLanguage(Locale.US);
            // tts.setPitch(5); // set pitch level
            // tts.setSpeechRate(2); // set speech speed rate
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            } else {
                //btnSpeak.setEnabled(true);
                ConvertTextToSpeech();
            }
        } else {
            Log.e("TTS", "Initilization Failed");
        }
    }
    private void ConvertTextToSpeech() {
        // TODO Auto-generated method stub
        //text = et.getText().toString();
        if(text==null||"".equals(text))
        {
            text = "Content not available";
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }else
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}
