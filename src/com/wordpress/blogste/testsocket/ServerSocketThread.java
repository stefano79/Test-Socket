package com.wordpress.blogste.testsocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketThread extends Thread {

	private int id;
	private int port;
	private ServerSocket server;
	private Socket socket;
	private SocketThread client;
	private boolean stop;
	private ModelSocket dataInput = null;
	private StatusConnection serverConnection = null;
	private StatusConnection clientConnection = null;

	public ServerSocketThread(int port, StatusConnection server, StatusConnection client, ModelSocket data) {
		this.port = port;
		this.serverConnection = server;
		this.clientConnection = client;
		this.dataInput = data;
	}

	public void run() {

		stop = false;

		try {
			server = new ServerSocket(port);
		} catch (IOException e1) {
			serverConnection.setError("Error: " + e1.getMessage());
			serverConnection.setConnected(false);
			e1.printStackTrace();
		} catch (IllegalArgumentException e2) {
			serverConnection.setError("Error: " + e2.getMessage());
			serverConnection.setConnected(false);
			e2.printStackTrace();
		}
		if (serverConnection != null) {
			serverConnection.setDataConnection(server.getLocalSocketAddress()
					.toString(), Integer.toString(server.getLocalPort()));
			serverConnection.setError(null);
			serverConnection.setConnected(true);
		}
		System.out.println("Server partito sulla porta "
				+ server.getLocalPort());
		System.out.println("In attesa di connessioni...");
		while (stop != true) {
			try {
				socket = server.accept();

				if (clientConnection != null) {
					clientConnection.setDataConnection(socket.getInetAddress()
							.toString(), null);
					clientConnection.setConnected(true);
				}
				System.out.println("Richiesta di connessione da "
						+ socket.getInetAddress());
				client = new SocketThread(id, socket, dataInput);
				client.start();
				id++;
			} catch (IOException e) {
				if (e.getMessage().equals("Socket closed")) {
					serverConnection.setError(null);
					serverConnection.setConnected(false);
					System.out.println("Server chiuso");
				} else {
					serverConnection.setError(e.getMessage());
					serverConnection.setConnected(false);
					e.printStackTrace();
				}
			}
		}
	}

	public void stopServer() {
		try {
			stop = true;
			if (socket != null)socket.close();
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void write(byte[] data) {
		client.write(data);
	}

	/*
	 * Inner class per ricevere dati dal client connesso
	 */
	

}