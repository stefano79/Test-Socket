package com.wordpress.blogste.testsocket;

import java.util.Observable;

import com.wordpress.blogste.testsocket.Controller.Subject;

public class ModelSocket extends Observable {
	
	private byte[] data;
	private Subject subject;

	public ModelSocket(Subject subject) {
		this.subject = subject;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] dataIn) {
		this.data = dataIn;
		setChanged();
		this.notifyObservers(subject);
	}
	
	public String toString(){
		return new String(data);
	}
	
}
