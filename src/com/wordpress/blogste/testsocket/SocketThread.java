package com.wordpress.blogste.testsocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SocketThread extends Thread {

	private int id;
	private Socket socket;
	private InetAddress address;
	private int port;
	private boolean stop;
	private InputStream i = null;
	private OutputStream o = null;
	private ModelSocket dataInput;
	private StatusConnection connection;

	SocketThread(int id, Socket socket, ModelSocket dataInput) {
		this.id = id;
		this.socket = socket;
		this.dataInput = dataInput;
	}
	
	SocketThread(int id, InetAddress address, int port, StatusConnection connection, ModelSocket dataInput) {
		this.id = id;
		this.address = address;
		this.port = port;
		this.connection = connection;
		this.dataInput = dataInput;
	}

	public void stopConnection() {
		stop = true;
		connection.setConnected(false);
	}

	public void write(byte[] data) {
		try {
			o.write(data, 0, data.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		if (socket == null){
			try {
				socket = new Socket(address, port);
				connection.setError(null);
				connection.setDataConnection(address.toString(), String.valueOf(port));
				connection.setConnected(true);
			} catch (IOException e) {
				connection.setError("Error: "  + e.getMessage());
				connection.setConnected(false);
				e.printStackTrace();
			}
		}
		

		System.out.println("ThreadSocket " + id + " start");
		try {
			i = socket.getInputStream();
			o = socket.getOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		int available = 0;

		while (true) {
			try {
				available = i.available(); // Creazione variabile che indica
											// il
											// numero di byte nel buffer di
											// input
			} catch (IOException e) {
				stopConnection();
				e.printStackTrace();
			}

			try {
				Thread.sleep(50); // ritardo il thread per consetire al
									// buffer
									// di ingresso di riempirsi
			} catch (InterruptedException e2) {
				e2.printStackTrace();
			}

			if (available > 0) {

				try {
					available = i.available(); // Aggiornamento variabile
												// che
												// indica il numero di byte
												// nel
												// buffer di input
				} catch (IOException e) {
					e.printStackTrace();
				}

				byte dataIn[] = new byte[available]; // Creazione array di
														// byte
														// con indice uguale
														// al
														// numero di byte
														// nel buffer
														// di input
				try {
					i.read(dataIn, 0, available); // Popolamento array chunk
													// con
													// i byte presenti nel
													// buffer del socket
				} catch (IOException e) {
					e.printStackTrace();
				}
				dataInput.setData(dataIn);
				if (stop) {
					try {
						i.close();
						o.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}

			}
		}
	}

}
