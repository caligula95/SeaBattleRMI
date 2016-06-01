package registration;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class RegistryForm {

	public JFrame frame;
	private JTextField login;
	private JTextField email;
	private JTextField password;
	private JTextField textConfirm;
	Client client;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the application.
	 */
	public RegistryForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ChoiceForm ch = new ChoiceForm();
		frame = new JFrame();
		frame.setTitle("\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044F");
		frame.setBounds(100, 100, 358, 299);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u041B\u043E\u0433\u0438\u043D");
		label.setBounds(24, 31, 56, 16);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u0415\u043C\u0435\u0439\u043B");
		label_1.setBounds(24, 74, 56, 16);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u041F\u0430\u0440\u043E\u043B\u044C");
		label_2.setBounds(24, 117, 56, 16);
		panel.add(label_2);
		
		login = new JTextField();
		login.setBounds(115, 28, 210, 22);
		panel.add(login);
		login.setColumns(10);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(115, 71, 210, 22);
		panel.add(email);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(115, 114, 210, 22);
		panel.add(password);
		
		JButton button = new JButton("\u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044F");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (password.getText().equals(textConfirm.getText())){
				try {
					client = new Client("localhost", 12345);
					if (client.sendQuery(0, login.getText(), email.getText(), password.getText())==1){
						JOptionPane.showMessageDialog(frame, "Регистрация успешна", "", 1);
						Thread.sleep(200);
						frame.setVisible(false);
						ch.frame.setVisible(true);
					}
					else if (client.sendQuery(0, login.getText(), email.getText(), password.getText())==3){
						JOptionPane.showMessageDialog(frame, "Логин занят", "", 2);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Error occured");
					JOptionPane.showMessageDialog(frame, "Something went wrong", "", 3);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				else {
					JOptionPane.showMessageDialog(frame, "Пароли не совпадают", "", 0);
				}
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					client.disconnect();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(185, 202, 125, 25);
		panel.add(button);
		
		JButton button_1 = new JButton("\u041D\u0430\u0437\u0430\u0434");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ch.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		button_1.setBounds(79, 202, 97, 25);
		panel.add(button_1);
		
		JLabel label_3 = new JLabel("\u041F\u043E\u0434\u0442\u0432. \u043F\u0430\u0440\u043E\u043B\u044C");
		label_3.setBounds(24, 159, 152, 16);
		panel.add(label_3);
		
		textConfirm = new JTextField();
		textConfirm.setColumns(10);
		textConfirm.setBounds(115, 150, 210, 22);
		panel.add(textConfirm);
	}
}
