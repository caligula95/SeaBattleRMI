package gui;

import java.awt.event.*;
import javax.swing.*;

import f.ConfServer;
import game.Game;
import game.SoundEffect;

import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Field extends JPanel {
	private Timer tmDraw;
	ConfServer server;
	private Image fon, paluba, ubit, ranen, end1, end2, bomba;
	private JButton start, quit, auto, vruchn;
	private Game myGame;
	private int mX, mY;
	String url;
	public Field(String url) {
		this.url = url;
		try {
			server = (ConfServer) Naming.lookup("//192.168.0.104/" + url);
		} catch (MalformedURLException | RemoteException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		addMouseListener(new myMouse1());
		addMouseMotionListener(new myMouse2());
		setFocusable(true); 
		// ������� ������ ����� ����
		try {
			myGame = new Game();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	myGame.start();
		try {
			fon = ImageIO.read(new File("java\\fon.png"));
			paluba = ImageIO.read(new File("java\\paluba.gif"));
			ranen = ImageIO.read(new File("java\\ranen.gif"));
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
				repaint();
			}
		});
		tmDraw.start();
		setLayout(null);
		start = new JButton();
		start.setText("����� ����");
		start.setForeground(Color.BLUE);
		start.setFont(new Font("serif", 0, 30));
		start.setBounds(130, 450, 200, 80);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//myGame.start();
				try {
					server.start();
					//JOptionPane.showMessageDialog(new Frame(), "Game started. Select ship filling", "", 1);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		add(start);
		auto = new JButton("Auto Filling");
		auto.setBounds(130, 450, 200, 80);
		auto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					server.autoFilling();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add(auto);
		vruchn = new JButton("Fill the ships");
		vruchn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				rastanovka();
			}
		});
		add(vruchn);
		quit = new JButton();
		quit.setText("�����");
		quit.setForeground(Color.RED);
		quit.setFont(new Font("serif", 0, 30));
		quit.setBounds(530, 450, 200, 80);
		quit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		add(quit);
	}


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
		gr.drawImage(ranen, mX, mY, 30, 30, null);
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				// ������� ���� ����������
				try {
					if (server.getMasComp()[i][j] != 0) {
						try {
							
							// ���� ��� �������� ������ �������
							 if ((server.getMasComp()[i][j] >= 8) && (server.getMasComp()[i][j] <= 11)) {
								gr.drawImage(ranen, 100 + j * 30, 100 + i * 30, 30, 30, null);
							}
							// ���� ��� ������ ��������� ��������� �������
							else if (server.getMasComp()[i][j] >= 15) {
								gr.drawImage(ubit, 100 + j * 30, 100 + i * 30, 30, 30, null);
							}
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
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
				// ������� ���� ������
				try {
					if (server.getMasPl()[i][j] != 0) {
						// ���� ��� ������ �������
					if ((server.getMasPl()[i][j] >= 1) && (server.getMasPl()[i][j] <= 4)) {
						gr.drawImage(paluba, 500 + j * 30, 100 + i * 30, 30, 30, null);
					}
						// ���� ��� �������� ������ �������
					else if ((server.getMasPl()[i][j] >= 8) && (server.getMasPl()[i][j] <= 11)) {
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
			}
		}
		gr.setColor(Color.RED); // ������� ����
		// ���� ������ ���� ������ �������� ���� ����������
		if ((mX > 100) && (mY > 100) && (mX < 400) && (mY < 400)) {
			// ���� �� ����� ���� � ��� ������
			if ((myGame.endGame == 0) && (myGame.compTurn == false)) {
				// ��������� ����� ������ � �������
				int i = (mY - 100) / 30;
				// ��������� ����� �������� � ������ � �������
				int j = (mX - 100) / 30;
				// ���� ������ �������� ��� ��������
				try {
					if (server.getMasComp()[i][j] <= 4)
						// ������ ������� � ��������
						gr.fillRect(100 + j * 30, 100 + i * 30, 30, 30);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		if (myGame.endGame == 1) // ���� ������� �����
		{
			gr.drawImage(end1, 300, 200, 300, 100, null);
		} else if (myGame.endGame == 2) // ���� ������� ���������
		{
			gr.drawImage(end2, 300, 200, 300, 100, null);
		}
	}
	public class myMouse1 implements MouseListener {
		public void mouseClicked(MouseEvent e) {
		}

		// ��� ������� ������ ����
		public void mousePressed(MouseEvent e) {
			// ���� ������� ��������� ������� ����� �������� ����
			SoundEffect.MIMO.play();
			if ((e.getButton() == 1) && (e.getClickCount() == 1)) {
				// �������� ������� ���������� ������� ����
				mX = e.getX();
				mY = e.getY();
				
				// ���� ������ ���� ������ �������� ���� ����������
				if ((mX > 100) && (mY > 100) && (mX < 400) && (mY < 400)) {
					// ���� �� ����� ���� � ��� ������
					if ((myGame.endGame == 0) && (myGame.compTurn == false)) {
						// ��������� ����� ������ � �������
						int i = (mY - 100) / 30;
						// ��������� ����� �������� � ������ � �������
						int j = (mX - 100) / 30;
						// ���� ������ �������� ��� ��������
						try {
							if (server.getMasPl()[i][j] <= 4)
								try {
									server.playerShot(i, j);
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
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
			if ((mX >= 100) && (mY >= 100) && (mX <= 400) && (mY <= 400))
				setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			else
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	public void rastanovka(){
		int x = (mY-90) / 30;
		// ��������� ����� �������� � ������ � �������
		int y = (mX-480) / 30;
		myGame.rasstanVruchnu(x, y);
	}
}

