package com.bnsnsports.fanstats;

public class Choice {
	
	String _correct;
	String _team;

	public Choice()
	{
		
	}
	
	public  String getcorrect(){
        return this._correct;
    }
     
    // setting name
    public void setcorrect(String correct){
        this._correct = correct;
    } 
    
    public  String getteam(){
        return this._team;
    }
     
    // setting name
    public void setteam(String team){
        this._team = team;
    }
}
