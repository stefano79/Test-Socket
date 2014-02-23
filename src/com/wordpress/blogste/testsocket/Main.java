package com.wordpress.blogste.testsocket;


import com.wordpress.blogste.testsocket.Controller.Subject;

public class Main {
	
	public static void main(String[] args) {
		ModelSocket server = new ModelSocket(Subject.serverData);
		ModelSocket client = new ModelSocket(Subject.clientData);
		
		View view = new View();
		
		@SuppressWarnings("unused")
		Controller controller = new Controller(server, client, view);

		view.setVisible(true);
	}

}
