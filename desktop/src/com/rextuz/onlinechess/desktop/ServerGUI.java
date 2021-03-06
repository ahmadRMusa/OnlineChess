package com.rextuz.onlinechess.desktop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.rextuz.onlinechess.server.AuthServerSocket;

public class ServerGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	JTextField portTextField;
	JButton startButton;
	JLabel statusLabel2;
	JLabel hostLabel;
	JLabel addressLabel;
	JLabel portLabel;
	JPanel infoPanel;

	private AuthServerSocket server;

	private boolean authRunning;

	public ServerGUI() {
		super("OnlineChess server");
		setVisible(true);
		setBounds(100, 100, 300, 150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(
				new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		JPanel startPanel = new JPanel();
		startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.LINE_AXIS));

		{
			portTextField = new JTextField("4242");
			startPanel.add(portTextField);
			portTextField.setMaximumSize(new Dimension(4000, 20));
			startButton = new JButton("Start");
			startButton.setMaximumSize(new Dimension(150, 20));
			startButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						if (!authRunning) {
							int port = Integer.parseInt(portTextField.getText());
							server = new AuthServerSocket(port);
							server.start();
							statusLabel2.setText("running");
							statusLabel2.setForeground(Color.green);
							infoPanel.setVisible(true);
							authRunning = true;
							startButton.setText("Stop");
						} else {
							server.terminate();
							statusLabel2.setText("idle");
							statusLabel2.setForeground(Color.gray);
							infoPanel.setVisible(false);
							authRunning = true;
							authRunning = false;
							startButton.setText("Start");
						}
						// AuthServer server = new AuthServer(port);
						// new MatchServerSocket(port + 1);
						// MatchServer matchServer = new MatchServer(port + 1);
						// matchServer.start();
						/*
						 * if (!server.start())
						 * JOptionPane.showMessageDialog(getContentPane(),
						 * "Server failed to start. Try changing port"); else {
						 * statusLabel2.setText("running");
						 * statusLabel2.setForeground(Color.green);
						 * startButton.setEnabled(false);
						 * infoPanel.setVisible(true); }
						 */
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(getContentPane(),
								"Incorrect port");
					}
				}
			});
			startPanel.add(startButton);
		}

		JPanel globalStatusPanel = new JPanel();
		globalStatusPanel.setLayout(new BoxLayout(globalStatusPanel,
				BoxLayout.PAGE_AXIS));

		{
			{
				JPanel statusPanel = new JPanel();
				statusPanel.setLayout(new BoxLayout(statusPanel,
						BoxLayout.LINE_AXIS));

				JLabel statusLabel1 = new JLabel("Server is ");
				statusPanel.add(statusLabel1);
				statusLabel2 = new JLabel("idle");
				statusLabel2.setForeground(Color.gray);
				statusPanel.add(statusLabel2);

				globalStatusPanel.add(statusPanel);
			}
			{
				infoPanel = new JPanel();
				infoPanel.setLayout(new BoxLayout(infoPanel,
						BoxLayout.PAGE_AXIS));

				String hostname = "unknown";
				String address = "unknown";
				String port = "unknown";
				try {
					hostname = InetAddress.getLocalHost().getHostName();
					address = InetAddress.getLocalHost().getHostAddress();
					port = portTextField.getText();
				} catch (Exception e) {
				}
				hostLabel = new JLabel("Hostname: " + hostname);
				addressLabel = new JLabel("Address: " + address);
				portLabel = new JLabel("Port: " + port);
				infoPanel.add(hostLabel);
				infoPanel.add(addressLabel);
				infoPanel.add(portLabel);
				infoPanel.setVisible(false);

				globalStatusPanel.add(infoPanel);
			}
		}

		add(startPanel);
		add(globalStatusPanel);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ServerGUI();
			}
		});
	}
}
