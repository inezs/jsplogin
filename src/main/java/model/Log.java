package model;

import java.io.Serializable;

import properties.CONST;

public class Log implements Serializable{
	static final long serialVersionUID = CONST.serialVersionUID;
	
	private String username;
	private String log;
	private String time;
	
	public Log(String username, String log) {
		this.username=username;
		this.log=log;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getTime() {
		return time;
	}
	
	public String toString() {
		return username+" "+log+" "+time;
	}
}
