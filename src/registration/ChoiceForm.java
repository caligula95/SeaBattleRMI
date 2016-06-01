package registration;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import f.ConfServer;
import gui.CreatingGame;
import gui.Field;
import gui.Player1;
import gui.Player2;
import gui.StartMenu;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class ChoiceForm {

	public JFrame frame;
	public JTextField login;
	private JTextField password;
	Client client;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChoiceForm window = new ChoiceForm();
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
	public ChoiceForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 337, 276);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u0412\u043E\u0439\u0434\u0438\u0442\u0435 \u0438\u043B\u0438 \u0437\u0430\u0440\u0435\u0433\u0438\u0441\u0442\u0440\u0438\u0440\u0443\u0439\u0442\u0435\u0441\u044C");
		label.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label.setBounds(13, 31, 294, 34);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u041B\u043E\u0433\u0438\u043D");
		label_1.setBounds(31, 86, 56, 16);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C");
		label_2.setBounds(31, 130, 56, 16);
		panel.add(label_2);
		
		login = new JTextField();
		login.setBounds(126, 82, 157, 22);
		panel.add(login);
		login.setColumns(10);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(128, 126, 153, 22);
		panel.add(password);
		
		JButton btnEnter = new JButton("\u0412\u043E\u0439\u0442\u0438");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					client = new Client("localhost", 12345);
					//client.sendQuery(1, login.getText(), "", password.getText());
					if (client.sendQuery(1, login.getText(), "", password.getText())==3)
					JOptionPane.showMessageDialog(frame, "Неверный логин или пароль", "", 0);
					else {
						JOptionPane.showMessageDialog(frame, "OK", "", 1);
					//	s.createServer(login.getText());
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									CreatingGame window = new CreatingGame(login.getText());
									//window.textArea.setText(server.getClientsNames());
									window.frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Thread.sleep(300);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					client.disconnect();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEnter.setBounds(20, 180, 73, 25);
		panel.add(btnEnter);
		
		JButton btnRegistry = new JButton("\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044F");
		btnRegistry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							RegistryForm window = new RegistryForm();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frame.setVisible(false);
			}
		});
		btnRegistry.setBounds(105, 180, 110, 25);
		panel.add(btnRegistry);
		
		JButton btnCancel = new JButton("\u041E\u0442\u043C\u0435\u043D\u0430");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartMenu m = new StartMenu();
					m.frame.setVisible(true);
					frame.setVisible(false);
			}
		});
		btnCancel.setBounds(227, 180, 80, 25);
		panel.add(btnCancel);
	}
/*	Server server = new Server();
	public String startField(){
		
		String message = "Free";
		try {
			message = server.createRegistry(login.getText());
		} catch (RemoteException | MalformedURLException e) {
			System.out.println("Unable to create registry");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;	
	}*/
	/*public void sost(){
		startField();
			int count = server.getClients();
			
		if (count%2!=0) {
		//	AnotherWindow w = new AnotherWindow(login.getText());
			int stat = JOptionPane.showConfirmDialog(new Frame(), "К сожалению свободных игор нет. Вы можете создать игру и ждать подключения пользователей"
					+ "или пока поиграть с компьютером. Создать новую игру?");
			if (stat == 0) {
				EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Player2 window = new Player2(login.getText());
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			JOptionPane.showMessageDialog(new Frame(), "Created game with the url " + login.getText());
			}
			else if (stat == 1) {
				frame.setVisible(true);
			}
			
		}
		else if (count%2==0) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						CreatingGame window = new CreatingGame();
						window.textArea.setText(server.getClientsNames());
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		//	String url = JOptionPane.showInputDialog(new Frame(), "Enter url to connect to the game");
			//Window window = new Window(url);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Player1 window = new Player1(url);
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
	}*/
}
