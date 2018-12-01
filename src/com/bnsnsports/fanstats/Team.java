package com.bnsnsports.fanstats;

public class Team {
    
    //private variables
    int _id;
    String _name;
    String _group;
    int _ranking;
    int _rankpts;
    int _points;
    int _played; 
    int _won;
    int _lost; 
    int _drawn; 
    int _gf;
    int _ga;
    int _pw;
    int _pr;
    
    // Empty constructor
    public Team(){
         
    }
    // constructor
    public Team(int id, String name, String _group, int _ranking, int _rankpts,int _points,
    		int _played, int _won, int _lost, int _drawn, int _gf, int _ga, int _pw , int _pr
    		){
        this._id = id;
        this._name = name;
        this._group = _group;
        this._ranking = _ranking;
        this._rankpts = _rankpts;
        this._points = _points;
        this._played = _played;
        this._won    = _won;
        this._lost   = _lost;
        this._drawn  = _drawn;
        this._gf     = _gf; 
        this._ga     = _ga;
        this._pw     = _pw;
        this._pr     = _pr;
        
    }
     
    // constructor
    public Team(String name, String _group){
        this._name = name;
        this._group = _group;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
     
    // setting id
    public void setID(int id){
        this._id = id;
    }
     
    // getting name
    public String getName(){
        return this._name;
    }
     
    // setting name
    public void setName(String name){
        this._name = name;
    }
     
    // getting phone number
    public String getGroup(){
        return this._group;
    }
     
    // setting phone number
    public void setGroup(String group){
    	this._group = group;
    }
    
 // getting ID
    public int getranking(){
        return this._ranking;
    }
     
    // setting id
    public void setranking(int ranking){
        this._ranking = ranking;
    }
    
 // getting ID
    public int getrankpts(){
        return this._rankpts;
    }
     
    // setting id
    public void setrankpts(int rankpts){
        this._rankpts = rankpts;
    }
    
 // getting ID
    public int getpoints(){
        return this._points;
    }
     
    // setting id
    public void setpoints(int points){
        this._points = points;
    }
 // getting ID
    public int getplayed(){
        return this._played;
    }
 // setting id
    public void setplayed(int played){
        this._played = played;
    }
    
 // getting ID
    public int getwon(){
        return this._won;
    }
 // setting id
    public void setwon(int won){
        this._won = won;
    }
    
 // getting ID
    public int getdrawn(){
        return this._drawn;
    }
 // setting id
    public void setdrawn(int drawn){
        this._drawn = drawn;
    }
    
 // getting ID
    public int getlost(){
        return this._lost;
    }
 // setting id
    public void setlost(int lost){
        this._lost = lost;
    }
    
    
 // getting ID
    public int getgf(){
        return this._gf;
    }
 // setting id
    public void setgf(int gf){
        this._gf = gf;
    }
    
 // getting ID
    public int getga(){
        return this._ga;
    }
 // setting id
    public void setga(int ga){
        this._ga = ga;
    }
    
    public  int getpw(){
        return this._pw;
    }
     
    // setting name
    public void setpw(int pw){
        this._pw = pw;
    }
    
    public  int getpr(){
        return this._pr;
    }
     
    // setting name
    public void setpr(int pr){
        this._pr = pr;
    }
}



