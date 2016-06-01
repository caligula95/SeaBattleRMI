package gui;



import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import f.ConfServer;
import game.Game;
import gui.StartMenu;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Player1 {
	StartMenu menu = new StartMenu();
	private Timer tmDraw;
	private Image fon, paluba, ubit, ranen, end1, end2, bomba;
	private JButton start, quit;
	ConfServer server;
	private int mX, mY;
	public JFrame frame;
	private JPanel p;
	boolean vruchnu = false;
	private JTextField textField;
	JTextArea textArea;
	String url;
	String message = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Player1 window = new Player1("qwe");
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
	public Player1(String url) {
		this.url = url;
		try {
			server = (ConfServer) Naming.lookup("//192.168.31.148/" + url);
		} catch (MalformedURLException | RemoteException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			initialize();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws RemoteException 
	 */
	private void initialize() throws RemoteException {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Field pan = new Field();
		Container cont = frame.getContentPane();
		
		// ������� ������ ����� ����
		server.clearField();
		try {
			fon = ImageIO.read(new File("java\\fon.png"));
			paluba = ImageIO.read(new File("java\\paluba.png"));
			ranen = ImageIO.read(new File("java\\ranen.png"));
			ubit = ImageIO.read(new File("java\\ubit.png"));
			end1 = ImageIO.read(new File("java\\end1.png"));
			end2 = ImageIO.read(new File("java\\end2.png"));
			bomba = ImageIO.read(new File("java\\bomba.png"));
		} catch (Exception ex) {
		}
		// �������, ����������� � ��������� ������
		// ��� ��������� �������� ����
		tmDraw = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				p.repaint();
			}
		});
		tmDraw.start();
		
		p = new JPanel(){
			public void paintComponent(Graphics gr) {
				// �������� �������� ����
				super.paintComponent(gr);
				gr.drawImage(fon, 0, 0, 900, 600, null);
				gr.setFont(new Font("serif", 3, 40));
				gr.setColor(Color.BLUE);
				gr.drawString("���������", 150, 50);
				gr.drawString("�����", 590, 50);
				
				// ��������� ������� ����� ����������
				// � ������ �� ��������� ��������
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						// ������� ���� ����������
						try {
							if (server.getMasPl()[i][j] != 0) {
								// ���� ��� �������� ������ �������
								if ((server.getMasPl()[i][j] >= 8) && (server.getMasPl()[i][j] <= 11)) {
									gr.drawImage(ranen, 500 + j * 30, 100 + i * 30, 30, 30, null);
								}
								// ���� ��� ������ ��������� ��������� �������
								else if (server.getMasPl()[i][j] >= 15) {
									gr.drawImage(ubit, 500 + j * 30, 100 + i * 30, 30, 30, null);
								}
								// ���� ��� �������
								if (server.getMasPl()[i][j] >= 5) {
									gr.drawImage(bomba, 500 + j * 30, 100 + i * 30, 30, 30, null);
								}
							}
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// ������� ���� ������
						try {
							if (server.getMasComp()[i][j] != 0) {
								// ���� ��� ������ �������
								if ((server.getMasComp()[i][j] >= 1) && (server.getMasComp()[i][j] <= 4)) {
									gr.drawImage(paluba, 100 + j * 30, 100 + i * 30, 30, 30, null);
								}
								// ���� ��� �������� ������ �������
								else if ((server.getMasComp()[i][j] >= 8) && (server.getMasComp()[i][j] <= 11)) {
									gr.drawImage(ranen, 100 + j * 30, 100 + i * 30, 30, 30, null);
								}
								// ���� ��� ������ ��������� ��������� �������
								else if (server.getMasComp()[i][j] >= 15) {
									gr.drawImage(ubit, 100 + j * 30, 100 + i * 30, 30, 30, null);
								}
								// ���� ��� �������
								if (server.getMasComp()[i][j] >= 5) {
									gr.drawImage(bomba, 100 + j * 30, 100 + i * 30, 30, 30, null);
								}
							}
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				 // ������� ����
				// ���� ������ ���� ������ �������� ���� ����������
				if ((mX > 500) && (mY > 100) && (mX < 800) && (mY < 400)) {
					// ���� �� ����� ���� � ��� ������
					try {
						if ((server.getEndGame() == 0) && (server.compTurn() == false)) {
							// ��������� ����� ������ � �������
							int i = (mY - 100) / 30;
							// ��������� ����� �������� � ������ � ���-����
							int j = (mX - 500) / 30;
							// ���� ������ �������� ��� ��������
							//EDIT<----------------------------------------------------------------------------
							if (server.getMasPl()[i][j] <= 4)
								// ������ ������� � ��������
								gr.fillRect(500 + j * 30, 100 + i * 30, 30, 30);
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// ��������� ����� �������� ���� �� ����� �����
				gr.setColor(Color.BLUE);
				for (int i = 0; i <= 10; i++) {
					// ��������� ����� ����� �������� ���� ����������
					gr.drawLine(100 + i * 30, 100, 100 + i * 30, 400);
					gr.drawLine(100, 100 + i * 30, 400, 100 + i * 30);
					// ��������� ����� ����� �������� ���� ��������
					gr.drawLine(500 + i * 30, 100, 500 + i * 30, 400);
					gr.drawLine(500, 100 + i * 30, 800, 100 + i * 30);
				}

				gr.setFont(new Font("serif", 0, 20));
				gr.setColor(Color.RED);
				// �������� ���� � ���� ����� � ������ �� ������� �����
				for (int i = 1; i <= 10; i++) {
					// ����� ����
					gr.drawString("" + i, 73, 93 + i * 30);
					gr.drawString("" + i, 473, 93 + i * 30);
					// ����� ����
					gr.drawString("" + (char) ('A' + i - 1), 78 + i * 30, 93);
					gr.drawString("" + (char) ('A' + i - 1), 478 + i * 30, 93);
				}
				// ����� ����������� ����� ���� - ��� ��������� ����
				try {
					if (server.getEndGame() == 1) // ���� ������� �����
					{
						gr.drawImage(end1, 300, 200, 300, 100, null);
					} else
						try {
							if (server.getEndGame() == 2) // ���� ������� ������-���
							{
								gr.drawImage(end2, 300, 200, 300, 100, null);
							}
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		};
	};
	cont.add(p);
	p.addMouseListener(new myMouse1());
	p.addMouseMotionListener(new myMouse2());
	p.setFocusable(true); 
	p.setLayout(null);
	
	JPanel panel = new JPanel();
	panel.setBounds(0, 442, 882, 211);
	p.add(panel);
	panel.setLayout(null);
	
	JButton btnNewGame = new JButton("\u0420\u0430\u0441\u043F\u043E\u043B\u043E\u0436\u0438\u0442\u044C \u043A\u043E\u0440\u0430\u0431\u043B\u0438 \u0432\u0440\u0443\u0447\u043D\u0443\u044E");
	btnNewGame.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(new Frame(), "Raspoloshite korabli soglasno s pravilami: 1 - 4 yarysa, 2 - 3 yarusa, 3 - 2 yarysa i 4 - 1 yarys");
			vruchnu = true;
		}
	});
	btnNewGame.setBounds(32, 13, 315, 35);
	panel.add(btnNewGame);
	
	JButton button = new JButton("\u0420\u0430\u0441\u043F\u043E\u043B\u043E\u0436\u0438\u0442\u044C \u043A\u043E\u0440\u0430\u0431\u043B\u0438 \u0430\u0432\u0442\u043E\u043C\u0430\u0442\u0438\u0447\u0435\u0441\u043A\u043C");
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				server.start();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	button.setBounds(32, 71, 315, 35);
	panel.add(button);
	
	JButton button_1 = new JButton("\u0412\u0435\u0440\u043D\u0443\u0442\u044C\u0441\u044F \u0432 \u043C\u0435\u043D\u044E");
	button_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int b = JOptionPane.showConfirmDialog(new Frame(), "You lost your progress. You really want exit the game?");
			if (b==0) {
			menu.frame.setVisible(true);
			frame.setVisible(false);
			}
			else if (b==1) {
				
			}
		}
	});
	button_1.setBounds(32, 131, 315, 35);
	panel.add(button_1);
	
	 textArea = new JTextArea();
	textArea.setBounds(425, 24, 389, 135);
	panel.add(textArea);
	
	textField = new JTextField();
	textField.setBounds(425, 163, 287, 35);
	panel.add(textField);
	textField.setColumns(10);
	
	JButton button_2 = new JButton("\u041E\u0442\u043F\u0440\u0430\u0432\u0438\u0442\u044C");
	button_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				getMessage();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});
	button_2.setBounds(717, 165, 97, 30);
	panel.add(button_2);
	}
	public class myMouse2 implements MouseMotionListener {
		public void mouseDragged(MouseEvent e) {
		}

		// ��� ����������� ������� ����
		public void mouseMoved(MouseEvent e) {
			// �������� ���������� �������
			mX = e.getX();
			mY = e.getY();
			// ���� ������ � ������� ���� ������
			if ((mX >= 500) && (mY >= 100) && (mX <= 800) && (mY <= 400))
				p.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			else
				p.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	public class myMouse1 implements MouseListener {
		public void mouseClicked(MouseEvent e) {
		}

		// ��� ������� ������ ����
		public void mousePressed(MouseEvent e) {
			// ���� ������� ��������� ������� ����� �������� ����
			if ((e.getButton() == 1) && (e.getClickCount() == 1)) {
				if (vruchnu == true){
				rastanovka();
				}
				// �������� ������� ���������� ������� ����
				mX = e.getX();
				mY = e.getY();
				// ���� ������ ���� ������ �������� ���� ����������
				if ((mX > 500) && (mY > 100) && (mX < 800) && (mY < 400)) {
					// ���� �� ����� ���� � ��� ������
					try {
						if ((server.getEndGame() == 0) && (server.compTurn() == true)) { //EDIT<---------- false
							// ��������� ����� ������ � �������
							int i = (mY - 100) / 30;
							// ��������� ����� �������� � ������ � ���-����
							int j = (mX - 500) / 30;
							// ���� ������ �������� ��� ��������
							if (server.getMasPl()[i][j] <= 4) //EDIT <------------- Comp
								// ���������� �������
								server.computerShot(i, j); //EDIT <--------Player
						}
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	
	public void rastanovka(){
		int x = (mY-100) / 30;
		// ��������� ����� �������� � ������ � �������
		int y = (mX-500) / 30;
		try {
			server.rasstanVruchnu(x, y);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
	public void rastanovka(){
		int x = (mY-90) / 30;
		// ��������� ����� �������� � ������ � �������
		int y = (mX-480) / 30;
		try {
			server.rasstanVruchnu(x, y);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getMessage() throws RemoteException {		
		 message = url + ": "+ textField.getText();
		server.setMessageComp(message);
		 textField.setText(new String(""));
		// textArea.setText(textArea.getText() + message+ "\n");
		 String msg = server.getMessage();
		 message = msg;
			// if console mode print the message and add back the prompt
			textArea.setText(textArea.getText() + msg+ "\n");
	      
	}
	public void printMessage(){
		if (!message.equals("")) {
			textArea.setText(textArea.getText()+ message + "\n");
		}
	}
}

