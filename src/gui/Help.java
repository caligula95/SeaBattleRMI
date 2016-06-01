package gui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Help {

	 JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the application.
	 */
	public Help() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 344, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblHelp = new JLabel("\u041F\u043E\u043C\u043E\u0449\u044C");
		lblHelp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHelp.setBounds(40, 31, 231, 28);
		panel.add(lblHelp);
		
		JButton button = new JButton("\u041D\u0430\u0437\u0430\u0434 \u0432 \u043C\u0435\u043D\u044E");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartMenu menu = new StartMenu();
				menu.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		button.setBounds(12, 274, 291, 39);
		panel.add(button);
	}
}
