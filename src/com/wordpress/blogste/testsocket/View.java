package com.wordpress.blogste.testsocket;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.UIManager;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldPort_server;
	private JTextField textFieldSend_server;
	private JTextField textFieldSend_client;
	private JTextField textFieldPort_client;
	private JTextField textFieldAddress_client;
	private JCheckBox chckbxHexTextArea_server;
	private JCheckBox chckbxAutoscrollTextArea_server;
	private JCheckBox chckbxHexSend_server;
	private JCheckBox chckbxAutoscrollTextArea_client;
	private JCheckBox chckbxHexTextArea_client;
	private JCheckBox chckbxHexSend_client;
	private JTextArea textArea_server;
	private JTextArea textArea_client;
	private JButton btnListen_server;
	private JButton btnSend_server;
	private JButton btnConnect_client;
	private JButton btnSend_client;

	/**
	 * Create the application.
	 */
	public View() {
		
		// Abilitazione look oggetti come il sistema operativo
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Throwable e) {
					e.printStackTrace();
				}
		
		setTitle("Test Socket");
		setBounds(100, 100, 607, 409);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(null);
		tabbedPane.addTab("Server", null, panel_1, null);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
		panel_1.add(panel_1_1, BorderLayout.CENTER);

		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setAutoscrolls(true);
		scrollPane_1_1
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		chckbxHexTextArea_server = new JCheckBox("HEX");

		chckbxAutoscrollTextArea_server = new JCheckBox("Autoscroll");
		chckbxAutoscrollTextArea_server.setSelected(true);

		JButton btnClear_server = new JButton("Clear");
		btnClear_server.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_server.setText("");
			}
		});
		GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
		gl_panel_1_1
				.setHorizontalGroup(gl_panel_1_1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_1_1
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_1_1
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																scrollPane_1_1,
																GroupLayout.DEFAULT_SIZE,
																380,
																Short.MAX_VALUE)
														.addGroup(
																gl_panel_1_1
																		.createSequentialGroup()
																		.addComponent(
																				chckbxHexTextArea_server)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				chckbxAutoscrollTextArea_server)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				113,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnClear_server,
																				GroupLayout.PREFERRED_SIZE,
																				107,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		gl_panel_1_1
				.setVerticalGroup(gl_panel_1_1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_1_1
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(scrollPane_1_1,
												GroupLayout.DEFAULT_SIZE, 156,
												Short.MAX_VALUE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel_1_1
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																btnClear_server)
														.addGroup(
																gl_panel_1_1
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				chckbxAutoscrollTextArea_server)
																		.addComponent(
																				chckbxHexTextArea_server)))
										.addContainerGap()));

		textArea_server = new JTextArea();
		textArea_server.setLineWrap(true);
		textArea_server.setEditable(false);
		scrollPane_1_1.setViewportView(textArea_server);
		panel_1_1.setLayout(gl_panel_1_1);

		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
		panel_1.add(panel_1_2, BorderLayout.SOUTH);

		textFieldSend_server = new JTextField();
		textFieldSend_server.setColumns(10);

		btnSend_server = new JButton("Send");
		btnSend_server.setEnabled(false);

		chckbxHexSend_server = new JCheckBox("HEX");
		GroupLayout gl_panel_1_2 = new GroupLayout(panel_1_2);
		gl_panel_1_2.setHorizontalGroup(gl_panel_1_2.createParallelGroup(
				Alignment.TRAILING).addGroup(
				gl_panel_1_2
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(textFieldSend_server,
								GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(chckbxHexSend_server)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnSend_server).addContainerGap()));
		gl_panel_1_2
				.setVerticalGroup(gl_panel_1_2
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_1_2
										.createSequentialGroup()
										.addGap(5)
										.addGroup(
												gl_panel_1_2
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																btnSend_server)
														.addComponent(
																textFieldSend_server,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																chckbxHexSend_server))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		panel_1_2.setLayout(gl_panel_1_2);

		JPanel panel_1_3 = new JPanel();
		panel_1_3.setBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
		panel_1.add(panel_1_3, BorderLayout.NORTH);

		textFieldPort_server = new JTextField();
		textFieldPort_server.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldPort_server.setText("0");
		textFieldPort_server.setColumns(10);

		btnListen_server = new JButton("Listen");
		panel_1_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblPort_server = new JLabel("Port");
		panel_1_3.add(lblPort_server);
		panel_1_3.add(textFieldPort_server);
		panel_1_3.add(btnListen_server);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(null);
		tabbedPane.addTab("Client", null, panel_2, null);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
		panel_2.add(panel_2_1, BorderLayout.CENTER);

		JScrollPane scrollPane_2_1 = new JScrollPane();
		scrollPane_2_1
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		chckbxHexTextArea_client = new JCheckBox("HEX");

		chckbxAutoscrollTextArea_client = new JCheckBox("Autoscroll");
		chckbxAutoscrollTextArea_client.setSelected(true);

		JButton btnClear_client = new JButton("Clear");
		btnClear_client.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea_client.setText("");
			}
		});
		GroupLayout gl_panel_2_1 = new GroupLayout(panel_2_1);
		gl_panel_2_1
				.setHorizontalGroup(gl_panel_2_1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_2_1
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_2_1
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_2_1
																		.createSequentialGroup()
																		.addComponent(
																				chckbxHexTextArea_client)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				chckbxAutoscrollTextArea_client)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				283,
																				Short.MAX_VALUE)
																		.addComponent(
																				btnClear_client,
																				GroupLayout.PREFERRED_SIZE,
																				107,
																				GroupLayout.PREFERRED_SIZE))
														.addComponent(
																scrollPane_2_1,
																Alignment.TRAILING,
																GroupLayout.DEFAULT_SIZE,
																550,
																Short.MAX_VALUE))
										.addContainerGap()));
		gl_panel_2_1
				.setVerticalGroup(gl_panel_2_1
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panel_2_1
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(scrollPane_2_1,
												GroupLayout.DEFAULT_SIZE, 167,
												Short.MAX_VALUE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel_2_1
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																btnClear_client)
														.addGroup(
																gl_panel_2_1
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				chckbxAutoscrollTextArea_client)
																		.addComponent(
																				chckbxHexTextArea_client)))
										.addContainerGap()));

		textArea_client = new JTextArea();
		textArea_client.setLineWrap(true);
		scrollPane_2_1.setViewportView(textArea_client);
		panel_2_1.setLayout(gl_panel_2_1);

		JPanel panel_2_2 = new JPanel();
		panel_2_2.setBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
		panel_2.add(panel_2_2, BorderLayout.SOUTH);

		textFieldSend_client = new JTextField();
		textFieldSend_client.setColumns(10);

		chckbxHexSend_client = new JCheckBox("HEX");

		btnSend_client = new JButton("Send");
		btnSend_client.setEnabled(false);
		GroupLayout gl_panel_2_2 = new GroupLayout(panel_2_2);
		gl_panel_2_2.setHorizontalGroup(gl_panel_2_2.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_2_2
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(textFieldSend_client,
								GroupLayout.PREFERRED_SIZE, 400,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(chckbxHexSend_client)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnSend_client).addContainerGap()));
		gl_panel_2_2
				.setVerticalGroup(gl_panel_2_2
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								gl_panel_2_2
										.createSequentialGroup()
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												gl_panel_2_2
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_2_2
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				chckbxHexSend_client)
																		.addComponent(
																				btnSend_client))
														.addGroup(
																Alignment.TRAILING,
																gl_panel_2_2
																		.createSequentialGroup()
																		.addComponent(
																				textFieldSend_client,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addContainerGap()))));
		panel_2_2.setLayout(gl_panel_2_2);

		JPanel panel_2_3 = new JPanel();
		panel_2_3.setBorder(UIManager.getBorder("InsetBorder.aquaVariant"));
		panel_2.add(panel_2_3, BorderLayout.NORTH);

		JLabel lblIp_client = new JLabel("IP");

		textFieldAddress_client = new JTextField();
		textFieldAddress_client.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldAddress_client.setText("0.0.0.0");
		textFieldAddress_client.setColumns(10);

		JLabel lblPort_client = new JLabel("Port");

		textFieldPort_client = new JTextField();
		textFieldPort_client.setText("0");
		textFieldPort_client.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldPort_client.setColumns(10);

		btnConnect_client = new JButton("Connect");
		panel_2_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_2_3.add(lblIp_client);
		panel_2_3.add(textFieldAddress_client);
		panel_2_3.add(lblPort_client);
		panel_2_3.add(textFieldPort_client);
		panel_2_3.add(btnConnect_client);
	}

	/*
	 * Metodi getter e setter per il server
	 */
	// Conessione
	public String getPort_Server() {
		try {
			Integer.parseInt(textFieldPort_server.getText());
			return textFieldPort_server.getText();
		} catch (NumberFormatException e) {
			this.writeConsole_Server("Error: Can't assign requested port"
					+ "\n");
			return null;
		}

	}

	public void setStateBtnConnect_Server(boolean state) {
		if (state) {
			btnListen_server.setText("Stop listen");
		} else {
			btnListen_server.setText("Listen");
		}
	}

	// Console
	public void writeConsole_Server(String string) {
		textArea_server.append(string);
		if (chckbxAutoscrollTextArea_server.isSelected())
			textArea_server.setCaretPosition(textArea_server.getDocument()
					.getLength());
	}

	public boolean getHexConsole_Server() {
		return chckbxHexTextArea_server.isSelected();
	}

	public void setHexConsole_Server(boolean state) {
		chckbxHexTextArea_server.setSelected(state);
	}

	public void setAutoscrollConsole_Server(boolean state) {
		chckbxAutoscrollTextArea_server.setSelected(state);
	}

	// Invio dati
	public String getStringForSend_Server() {
		return textFieldSend_server.getText();
	}

	public boolean getHexSend_Server() {
		return chckbxHexSend_server.isSelected();
	}

	public void setHexSend_Server(boolean state) {
		chckbxHexSend_server.setSelected(state);
	}

	public void setEnableBtnSend_Server(boolean state) {
		btnSend_server.setEnabled(state);
	}

	/*
	 * Metodi getter e setter del client
	 */

	// Conessione

	public String getAddress_Client() {
		return textFieldAddress_client.getText();
	}

	public String getPort_Client() {
		try {
			Integer.parseInt(textFieldPort_client.getText());
			return textFieldPort_client.getText();
		} catch (NumberFormatException e) {
			this.writeConsole_Client("Error: Can't assign requested port"
					+ "\n");
			return null;
		}
	}

	public void setStateBtnConnect_Client(boolean state) {
		if (state) {
			btnConnect_client.setText("Disconnect");
		} else {
			btnConnect_client.setText("Connect");
		}
	}

	// Console
	public void writeConsole_Client(String string) {
		textArea_client.append(string);
		if (chckbxAutoscrollTextArea_client.isSelected())
			textArea_client.setCaretPosition(textArea_client.getDocument()
					.getLength());
	}

	public boolean getHexConsole_Client() {
		return chckbxHexTextArea_client.isSelected();
	}

	public void setHexConsole_Client(boolean state) {
		chckbxHexTextArea_client.setSelected(state);
	}

	public void setAutoscrollConsole_Client(boolean state) {
		chckbxAutoscrollTextArea_client.setSelected(state);
	}

	// Invio dati
	public String getStringForSend_Client() {
		return textFieldSend_client.getText();
	}

	public boolean getHexSend_Client() {
		return chckbxHexSend_client.isSelected();
	}

	public void setHexSend_Client(boolean state) {
		chckbxHexSend_client.setSelected(state);
	}

	public void setEnableBtnSend_Client(boolean state) {
		btnSend_client.setEnabled(state);
	}

	/*
	 * Assegnazione listener del server
	 */

	public void addListenerButtonListen_Server(ActionListener listener) {
		btnListen_server.addActionListener(listener);
	}

	public void addListenerButtonSend_Server(ActionListener listener) {
		btnSend_server.addActionListener(listener);
	}

	/*
	 * Assegnazione listener del client
	 */
	public void addListenerButtonConnect_Client(ActionListener listener) {
		btnConnect_client.addActionListener(listener);
	}

	public void addListenerButtonSend_Client(ActionListener listener) {
		btnSend_client.addActionListener(listener);
	}

}
