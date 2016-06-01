package singlePlayer;

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

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import gui.StartMenu;


public class D {
	StartMenu menu = new StartMenu();
	private Timer tmDraw;
	private Image fon, paluba, ubit, ranen, end1, end2, bomba;
	private JButton start, quit;
	private Game myGame;
	private int mX, mY;
	public JFrame frame;
	private JPanel p;
	boolean vruchnu = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the application.
	 */
	public D() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Field pan = new Field();
		Container cont = frame.getContentPane();
		
		// Создаем объект новой игры
		myGame = new Game();
		myGame.clearField();
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
		// Создаем, настраиваем и запускаем таймер
		// для отрисовки игрового поля
		tmDraw = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				p.repaint();
			}
		});
		tmDraw.start();
		
		p = new JPanel(){
			public void paintComponent(Graphics gr) {
				// Очищение игрового поля
				super.paintComponent(gr);
				gr.drawImage(fon, 0, 0, 900, 600, null);
				gr.setFont(new Font("serif", 3, 40));
				gr.setColor(Color.BLUE);
				gr.drawString("Компьютер", 150, 50);
				gr.drawString("Игрок", 590, 50);
				
				// Отрисовка игровых полей Компьютера
				// и Игрока на основании массивов
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						// Игровое поле компьютера
						if (myGame.masComputer[i][j] != 0) {
							// Если это подбитая палуба корабля
							if ((myGame.masComputer[i][j] >= 8) && (myGame.masComputer[i][j] <= 11)) {
								gr.drawImage(ranen, 100 + j * 30, 100 + i * 30, 30, 30, null);
							}
							// Если это палуба полностью подбитого корабля
							else if (myGame.masComputer[i][j] >= 15) {
								gr.drawImage(ubit, 100 + j * 30, 100 + i * 30, 30, 30, null);
							}
							// Если был выстрел
							if (myGame.masComputer[i][j] >= 5) {
								gr.drawImage(bomba, 100 + j * 30, 100 + i * 30, 30, 30, null);
							}
						}
						// Игровое поле игрока
						if (myGame.masPlayer[i][j] != 0) {
							// Если это палуба корабля
							if ((myGame.masPlayer[i][j] >= 1) && (myGame.masPlayer[i][j] <= 4)) {
								gr.drawImage(paluba, 500 + j * 30, 100 + i * 30, 30, 30, null);
							}
							// Если это подбитая палуба корабля
							else if ((myGame.masPlayer[i][j] >= 8) && (myGame.masPlayer[i][j] <= 11)) {
								gr.drawImage(ranen, 500 + j * 30, 100 + i * 30, 30, 30, null);
							}
							// Если это палуба полностью подбитого корабля
							else if (myGame.masPlayer[i][j] >= 15) {
								gr.drawImage(ubit, 500 + j * 30, 100 + i * 30, 30, 30, null);
							}
							// Если был выстрел
							if (myGame.masPlayer[i][j] >= 5) {
								gr.drawImage(bomba, 500 + j * 30, 100 + i * 30, 30, 30, null);
							}
						}
					}
				}
				gr.setColor(Color.RED); // Красный цвет
				// Если курсор мыши внутри игрового поля компьютера
				if ((mX > 100) && (mY > 100) && (mX < 400) && (mY < 400)) {
					// Если не конец игры и ход игрока
					if ((myGame.endGame == 0) && (myGame.compTurn == false)) {
						// Вычисляем номер строки в массиве
						int i = (mY - 100) / 30;
						// Вычисляем номер элемента в строке в массиве
						int j = (mX - 100) / 30;
						// Если ячйека подходит для выстрела
						if (myGame.masComputer[i][j] <= 4)
							// Рисуем квадрат с заливкой
							gr.fillRect(100 + j * 30, 100 + i * 30, 30, 30);
					}
				}
				// Отрисовка сетки игрового поля из синих линий
				gr.setColor(Color.BLUE);
				for (int i = 0; i <= 10; i++) {
					// Рисование линий сетки игрового поля Компьютера
					gr.drawLine(100 + i * 30, 100, 100 + i * 30, 400);
					gr.drawLine(100, 100 + i * 30, 400, 100 + i * 30);
					// Рисование линий сетки игрового поля Человека
					gr.drawLine(500 + i * 30, 100, 500 + i * 30, 400);
					gr.drawLine(500, 100 + i * 30, 800, 100 + i * 30);
				}

				gr.setFont(new Font("serif", 0, 20));
				gr.setColor(Color.RED);
				// Введение цифр и букв слева и сверху от игровых полей
				for (int i = 1; i <= 10; i++) {
					// Вывод цифр
					gr.drawString("" + i, 73, 93 + i * 30);
					gr.drawString("" + i, 473, 93 + i * 30);
					// Вывод букв
					gr.drawString("" + (char) ('A' + i - 1), 78 + i * 30, 93);
					gr.drawString("" + (char) ('A' + i - 1), 478 + i * 30, 93);
				}
				// Вывод изображения конца игры - при окончании игры
				if (myGame.endGame == 1) // Если победил Игрок
				{
					gr.drawImage(end1, 300, 200, 300, 100, null);
				} else if (myGame.endGame == 2) // Если победил Компьютер
				{
					gr.drawImage(end2, 300, 200, 300, 100, null);
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
			myGame.start();
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
	
	JButton button_2 = new JButton("\u0417\u0430\u043D\u043E\u0432\u043E");
	button_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			int b = JOptionPane.showConfirmDialog(new Frame(), "You lost your progress. You really want start new game?");
			if (b==0) {
			myGame.clearField();
			}
			
		}
	});
	button_2.setBounds(376, 13, 315, 35);
	panel.add(button_2);
	}
	public class myMouse2 implements MouseMotionListener {
		public void mouseDragged(MouseEvent e) {
		}

		// При перемещении курсора мыши
		public void mouseMoved(MouseEvent e) {
			// Получаем координаты курсора
			mX = e.getX();
			mY = e.getY();
			// Если курсор в области поля игрока
			if ((mX >= 100) && (mY >= 100) && (mX <= 400) && (mY <= 400))
				p.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			else
				p.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	public class myMouse1 implements MouseListener {
		public void mouseClicked(MouseEvent e) {
		}

		// При нажатии кнопки мыши
		public void mousePressed(MouseEvent e) {
			// Если сделано одиночное нажатие левой клавишей мыши
			if ((e.getButton() == 1) && (e.getClickCount() == 1)) {
				if (vruchnu == true){
				rastanovka();
				}
				// Получаем текущие координаты курсора мыши
				mX = e.getX();
				mY = e.getY();
				// Если курсор мыши внутри игрового поля компьютера
				if ((mX > 100) && (mY > 100) && (mX < 400) && (mY < 400)) {
					// Если не конец игры и ход игрока
					if ((myGame.endGame == 0) && (myGame.compTurn == false)) {
						// Вычисляем номер строки в массиве
						int i = (mY - 100) / 30;
						// Вычисляем номер элемента в строке в массиве
						int j = (mX - 100) / 30;
						// Если ячейка подходит для выстрела
						if (myGame.masComputer[i][j] <= 4)
							// Производим выстрел
							myGame.playerShot(i, j);
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
	public void rastanovka(){
		int x = (mY-90) / 30;
		// Вычисляем номер элемента в строке в массиве
		int y = (mX-480) / 30;
		myGame.rasstanVruchnu(x, y);
		
	}
}
