package com.wordpress.blogste.testsocket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {

	private ServerSocketThread serverSocket;
	private SocketThread clientSocket;
	private View view;
	private ModelSocket serverModel;
	private ModelSocket clientModel;

	public enum Subject {
		serverData, serverConnection, serverClient, clientData, clientConnection
	};

	private StatusConnection connectionServer;
	private StatusConnection clientServer;
	private StatusConnection connectionClient;

	public Controller(ModelSocket serverModel, ModelSocket clientModel,
			View view) {
		this.serverModel = serverModel;
		this.clientModel = clientModel;
		this.view = view;

		// Assegnazione model osservati
		serverModel.addObserver(this);
		connectionServer = new StatusConnection(Subject.serverConnection);
		connectionServer.addObserver(this);
		clientServer = new StatusConnection(Subject.serverClient);
		clientServer.addObserver(this);

		clientModel.addObserver(this);
		connectionClient = new StatusConnection(Subject.clientConnection);
		connectionClient.addObserver(this);

		// Assegnazione listener
		view.addListenerButtonListen_Server(new InizializeServer());
		view.addListenerButtonSend_Server(new SendDataServer());

		view.addListenerButtonConnect_Client(new ConnectClient());
		view.addListenerButtonSend_Client(new SendDataClient());
	}

	/*
	 * Controllo input dati delle classi socket e connection
	 */
	@Override
	public void update(Observable o, Object arg) {
		Subject subject = (Subject) arg;
		switch (subject) {
		case serverData:
			if (view.getHexConsole_Server()) {
				StringBuilder stringa = new StringBuilder();
				byte[] data = serverModel.getData();
				for (int i = 0; i < data.length; i++) {
					int n = data[i] & 0xFF;
					String s = Integer.toHexString(n);
					stringa.append("(");
					if (s.equals("0"))
						stringa.append(s);
					stringa.append(s);
					stringa.append(")");
				}
				view.writeConsole_Server(stringa + "\n");
			} else {
				view.writeConsole_Server(new String(serverModel.getData())
						+ "\n");
			}
			System.out.println("dati ricevuti = " + serverModel.getData());
			break;
		case serverConnection:
			if (connectionServer.getConnected()) {
				view.setStateBtnConnect_Server(true);
				view.writeConsole_Server("Server is listining on port: "
						+ connectionServer.getPort() + "\n");
			} else {
				view.setEnableBtnSend_Server(false);
				view.writeConsole_Server("Server is closed" + "\n");
			}
			if (connectionServer.getError() != null) view.writeConsole_Server(connectionServer.getError() + "\n");
			break;
		case serverClient:
			view.writeConsole_Server("Connected to "
					+ clientServer.getAddress() + "\n");
			view.setEnableBtnSend_Server(true);
			break;
		case clientData:
			if (view.getHexConsole_Client()) {
				StringBuilder stringa = new StringBuilder();
				byte[] data = clientModel.getData();
				for (int i = 0; i < data.length; i++) {
					int n = data[i] & 0xFF;
					String s = Integer.toHexString(n);
					stringa.append("(");
					if (s.equals("0"))
						stringa.append(s);
					stringa.append(s);
					stringa.append(")");
				}
				view.writeConsole_Client(stringa + "\n");
			} else {
				view.writeConsole_Client(new String(clientModel.getData())
						+ "\n");
			}
			System.out.println("dati ricevuti = " + clientModel.getData());
			break;
		case clientConnection:
			if (connectionClient.getConnected()) {
				view.setEnableBtnSend_Client(true);
				view.setStateBtnConnect_Client(true);
				view.writeConsole_Client("Connected to "
						+ connectionClient.getAddress() + "\n");
			} else {
				clientSocket = null;
				view.setEnableBtnSend_Client(false);
				view.setStateBtnConnect_Client(false);
				view.writeConsole_Client("Connection closed" + "\n");
			}
			if (connectionClient.getError() != null)
				view.writeConsole_Client(connectionClient.getError() + "\n");
			break;
		default:
			break;
		}

	}

	/*
	 * Classi per gestire i listener della view
	 */
	private class InizializeServer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String port;
			port = view.getPort_Server();
			if (port != null) {
				if (arg0.getActionCommand().equals("Listen")) {
					serverSocket = new ServerSocketThread(Integer.parseInt(port),
							connectionServer, clientServer, serverModel);
					serverSocket.start();
				} else if (arg0.getActionCommand().equals("Stop listen")) {
					serverSocket.stopServer();
					serverSocket = null;
					view.setStateBtnConnect_Server(false);
					view.setEnableBtnSend_Server(false);
				}
			}
		}

	}

	private class SendDataServer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			byte[] data = null;
			String s = view.getStringForSend_Server();

			if (view.getHexSend_Server()) {
				// Elimino gli spazi dalla stringa prelevata
				StringBuilder stringData = new StringBuilder();
				for (int i = 0; i < s.length(); i++) {
					String substring = s.substring(i, i + 1);
					if (substring.equals(" ")) {
						// nothing
					} else {
						stringData.append(substring);
					}
				}
				// Converto la stringa in byte
				int lenght = stringData.length() / 2;
				data = new byte[lenght];
				for (int i = 0; i < lenght; i++) {
					int cursor = i * 2;
					String stringa1 = stringData.substring(cursor, cursor + 1);
					String stringa2 = stringData.substring(cursor + 1,
							cursor + 2);
					Byte b1 = Byte.parseByte(stringa2, 16);
					Byte b2 = (byte) (Byte.parseByte(stringa1, 16) * 16);
					data[i] = (byte) (b1 + b2);
				}
				serverSocket.write(data);
			} else {
				data = s.getBytes();
				serverSocket.write(data);
			}

		}
	}

	private class ConnectClient implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand().equals("Connect")) {
				String address;
				String port;
				address = view.getAddress_Client();
				port = view.getPort_Client();

				if (address != null && port != null) {
					InetAddress inetAddress;
					try {
						inetAddress = InetAddress.getByName(address);
						clientSocket = new SocketThread(0, inetAddress, Integer.parseInt(port),
								connectionClient, clientModel);
						clientSocket.start();
						view.writeConsole_Client("Connection to "
								+ view.getAddress_Client() + "\n");
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}

				}
			} else if (arg0.getActionCommand().equals("Disconnect")) {

				clientSocket.stopConnection();
				clientSocket = null;
				view.setStateBtnConnect_Client(false);
				view.setEnableBtnSend_Client(false);
			}

		}

	}

	private class SendDataClient implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			byte[] data = null;
			String s = view.getStringForSend_Client();

			if (view.getHexSend_Client()) {
				// Elimino gli spazi dalla stringa prelevata
				StringBuilder stringData = new StringBuilder();
				for (int i = 0; i < s.length(); i++) {
					String substring = s.substring(i, i + 1);
					if (substring.equals(" ")) {
						// nothing
					} else {
						stringData.append(substring);
					}
				}
				// Converto la stringa in byte
				int lenght = stringData.length() / 2;
				data = new byte[lenght];
				for (int i = 0; i < lenght; i++) {
					int cursor = i * 2;
					String stringa1 = stringData.substring(cursor, cursor + 1);
					String stringa2 = stringData.substring(cursor + 1,
							cursor + 2);
					Byte b1 = Byte.parseByte(stringa2, 16);
					Byte b2 = (byte) (Byte.parseByte(stringa1, 16) * 16);
					data[i] = (byte) (b1 + b2);
				}
				clientSocket.write(data);
			} else {
				data = s.getBytes();
				clientSocket.write(data);
			}

		}
	}

}
