package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import f.ClientDriver;
import f.ClientInt;
import f.ConfServer;
import f.Serv;
import game.Clients;
import game.Game;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CreatingGame{

	public JFrame frame;
	private JTextField login;
	public JTextArea textArea;
	String url="d";
	JButton btnCreateOwnGame;
	JButton OK;
	//Server server = new Server();
	//ClientDriver client;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreatingGame window = new CreatingGame("d");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreatingGame(String url) {
		initialize();
		this.url = url;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		/*try {
			client = new ClientDriver();
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}*/
		frame.setBounds(100, 100, 437, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u0421\u043F\u0438\u0441\u043E\u043A \u0441\u0432\u043E\u0431\u043E\u0434\u043D\u044B\u0445 \u0438\u0433\u0440\u043E\u043A\u043E\u0432");
		label.setBounds(85, 26, 209, 37);
		panel.add(label);
		
		 textArea = new JTextArea();
		textArea.setBounds(40, 76, 301, 142);
		panel.add(textArea);
		
		JLabel label_1 = new JLabel("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043B\u043E\u0433\u0438\u043D \u0438\u0433\u0440\u043E\u043A\u0430, \u0447\u0442\u043E\u0431\u044B \u043D\u0430\u0447\u0430\u0442\u044C \u0438\u0433\u0440\u0443");
		label_1.setBounds(62, 231, 301, 16);
		panel.add(label_1);
		
		login = new JTextField();
		login.setBounds(72, 272, 169, 22);
		panel.add(login);
		login.setColumns(10);
		
		 OK = new JButton("OK");
		OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				client.setSost(2);
				if (!login.getText().equals("")) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Player1 window = new Player1(login.getText());
								window.frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		OK.setBounds(260, 272, 67, 25);
		panel.add(OK);
		 btnCreateOwnGame = new JButton("Create own game");
		btnCreateOwnGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				client.setSost(1);
				
				try {
					Naming.rebind(url, new Game());
				} catch (RemoteException | MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
					EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Player2 window = new Player2(url);
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				
				});
					print();
			}
		});
		btnCreateOwnGame.setBounds(75, 321, 169, 25);
		panel.add(btnCreateOwnGame);
	}
	public void print(){
		
	}

	
}
