
package com.bnsnsports.fanstats;

import java.util.ArrayList;
import java.util.List;

public class Answer {
	
	int _id;
	String _question;
	String _result;
	String _next;
	String _answer;
	List<Choice> _choices = new ArrayList<Choice>();
	
	
	public Answer() {
		
	}

	public  int getid(){
        return this._id;
    }
     
    // setting name
    public void setid(int id){
        this._id = id;
    } 
    
    public  String getquestion(){
        return this._question;
    }
     
    // setting name
    public void setquestion(String question){
        this._question = question;
    } 

    public  String getanswer(){
        return this._answer;
    }
     
    // setting name
    public void setanswer(String answer){
        this._answer = answer;
    } 
    public  String getresult(){
        return this._result;
    }
     
    // setting name
    public void setresult(String result){
        this._result = result;
    } 
    
    public  List<Choice>  getchoices(){
        return this._choices;
    }
     
    // setting name
    public void setchoices(List<Choice>  choices){
        this._choices = choices;
    }
}
