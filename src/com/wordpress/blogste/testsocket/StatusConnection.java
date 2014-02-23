package com.wordpress.blogste.testsocket;

import java.util.Observable;

import com.wordpress.blogste.testsocket.Controller.Subject;

public class StatusConnection extends Observable{
	
	private String address;
	private String port;
	private boolean connected;
	private Subject subject;
	private String error;
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public StatusConnection(Subject subject){
		this.subject = subject;
	}
	
	public String getAddress(){
		if (address != null){
		int lenght = address.length();
		return address.substring(1, lenght);
		} else {
			return null;
		}
	}
	
	public String getPort(){
		return port;
	}
	
	public String getDataConnection(){
		return address + ":" + port;
	}
	
	public boolean getConnected(){
		return connected;
	}
	
	public void setConnected(boolean arg){
		this.connected = arg;
		setChanged();
		this.notifyObservers(subject);
	}
	
	public void setDataConnection(String address, String port){
		this.address = address;
		this.port = port;
		//setChanged();
		//this.notifyObservers(subject);
	}

}
