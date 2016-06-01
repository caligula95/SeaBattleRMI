package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.SoundEffect;
import registration.ChoiceForm;
import singlePlayer.D;

public class StartMenu  {

	 public JFrame frame;
	JButton btnWithComp;
	Settings window1 = new Settings();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartMenu window = new StartMenu();
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
	public StartMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 425, 414);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		 btnWithComp = new JButton("\u041E\u0434\u0438\u043D\u043E\u0447\u043D\u0430\u044F \u0438\u0433\u0440\u0430");
		btnWithComp.setIcon(new ImageIcon("java\\buttonSingle.png"));
		btnWithComp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							D window = new D();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frame.setVisible(false);
			}
		});
		btnWithComp.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	try {
						play();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			});
		btnWithComp.setBounds(30, 83, 361, 43);
		panel.add(btnWithComp);
		
		JButton butnMultipl = new JButton("\u041C\u0443\u043B\u044C\u0442\u0438\u043F\u043B\u0435\u0435\u0440");
		butnMultipl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				frame.setVisible(false);
			}
		});
		butnMultipl.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	try {
						play();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			});
		butnMultipl.setIcon(new ImageIcon("java\\buttonMulti.png"));
		butnMultipl.setBounds(30, 137, 361, 43);
		panel.add(butnMultipl);
		
		JButton butnHelp = new JButton("\u041F\u043E\u043C\u043E\u0449\u044C");
		butnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Help window = new Help();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frame.setVisible(false);
			}
		});
		butnHelp.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	try {
						play();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			});
		butnHelp.setIcon(new ImageIcon("java\\buttonHelp.png"));
		butnHelp.setBounds(30, 245, 361, 43);
		panel.add(butnHelp);
		
		JButton butnExit = new JButton("\u0412\u044B\u0439\u0442\u0438");
		butnExit.setIcon(new ImageIcon("java\\buttonExit.png"));
		butnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0);
			}
		});
		butnExit.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
			    	try {
						play();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			});
		butnExit.setBounds(30, 299, 361, 43);
		panel.add(butnExit);
		
		JButton button = new JButton("\u041D\u0430\u0441\u0442\u0440\u043E\u0439\u043A\u0438");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							window1.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				frame.setVisible(false);
			}
		});
		button.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	try {
					play();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
		button.setIcon(new ImageIcon("java\\buttoSet.png"));
		button.setBounds(30, 191, 361, 43);
		panel.add(button);
		
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("java\\mainPicture.jpg"));
		lblNewLabel_1.setBounds(0, 0, 444, 386);
		panel.add(lblNewLabel_1);
	}
	public void play() throws Exception{
		if (window1.i==0) {
		SoundEffect.EXPLODE.play();
		}
		else System.out.println("Helo");
	}

}
