package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import game.SoundEffect;
import game.SoundEffect.Volume;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Settings {
	public Volume s;
	 JFrame frame;
	 public int i = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public Settings() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 408, 323);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnMute = new JButton("\u0412\u044B\u043A\u043B\u044E\u0447\u0438\u0442\u044C \u0437\u0432\u0443\u043A");
		btnMute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 i = 1;
			}
		});
		btnMute.setBounds(30, 61, 314, 34);
		panel.add(btnMute);
		btnMute.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 i = 1;
			}
		});
		
		JButton buttonBack = new JButton("\u041D\u0430\u0437\u0430\u0434 \u0432 \u043C\u0435\u043D\u044E");
		buttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StartMenu menu = new StartMenu();
				menu.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		buttonBack.setBounds(30, 180, 314, 34);
		panel.add(buttonBack);
	}
}
