package com.bnsnsports.fanstats;

public class Level {
	
	int _id;
	int noquest;	
	int timelimit;
	String status;
	String theme;

	
	public Level()
	{
		
	}
	
	public  int getid(){
        return this._id;
    }
     
    // setting name
    public void setid(int id){
        this._id = id;
    } 
    
    public  String getstatus(){
        return this.status;
    }
     
    // setting name
    public void setstatus(String status){
        this.status = status;
    }
}
