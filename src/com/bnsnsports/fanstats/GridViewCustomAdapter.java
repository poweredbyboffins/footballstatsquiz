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
import android.widget.ArrayAdapter;
import android.content.Context;
import android.graphics.Typeface;
import android.widget.ImageView;

public class GridViewCustomAdapter extends ArrayAdapter
{
         Context context;
         private Typeface face;
   

     public GridViewCustomAdapter(Context context)
     {
             super(context, 0);
             this.context=context;
             face = Typeface.createFromAsset(context.getAssets(),
                     "fontd/Mega.otf");
            
     }
   
     public int getCount()
        {
                     return 11;
        }

     @Override
     public View getView(int position, View convertView, ViewGroup parent)
     {
             View row = convertView;
             
                         
             if (row == null)
             {
                     LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                     row = inflater.inflate(R.layout.grid_row, parent, false);


                     TextView textViewTitle = (TextView) row.findViewById(R.id.textView);
                     ImageView imageViewIte = (ImageView) row.findViewById(R.id.imageView);
                    
                    
                    textViewTitle.setText("Level"+String.valueOf(position+1));
                    
                    textViewTitle.setTypeface(face);
                    
                    if(position+1 > fanstatsapp.level)
                    	
                    imageViewIte.setImageResource(R.drawable.levellock);
                    
                    else
                    	imageViewIte.setImageResource(R.drawable.level1);
                    	
                    
             }

   
     
      return row;

     }

}
