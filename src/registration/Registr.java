package registration;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
public class Registr implements ActionListener, WindowListener{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Registr r = new Registr();
		r.launchFrame();

	}
	Frame f,f1;
	Panel p1, p2, p3, p4, p5, p6;
	Label login, password, res, enres, enlogin, enpassword, enconfirm, enmailing;
	Button enter, cansel, registry, encansel, enregistry;
	TextField log, pasw, enlog, enpasw, enconf, enmail;
	String login1 = "abrakadabra";
	String password1 = "1234abrakadabra";
	public Registr() {
		f1 = new Frame("Регистрация");
		p1 = new Panel();
		p2 = new Panel();
		p3 = new Panel();
		p4 = new Panel();
		p5 = new Panel();
		p6 = new Panel();
		f = new Frame("Вход");
		res = new Label("");
		enres = new Label("");
		enmailing = new Label("Ел. Почта                ");
		enmail = new TextField(15);
		enconf = new TextField(15);
		enconfirm = new Label("Повторите пароль");
		login = new Label("Логин        ");
		password = new Label("Пароль     ");
		enter = new Button("Войти");
		cansel = new Button("Отмена");
		registry = new Button("Регистрация");
		log = new TextField(15);
		pasw = new TextField(15);
		enlogin = new Label("         Логин                ");
		enpassword = new Label("   Пароль                  ");
		encansel = new Button("Отмена");
		enregistry = new Button("Регистрация");
		enlog = new TextField(15);
		enpasw = new TextField(15);
	}
	private void launchFrame() {
		f1.setSize(300, 200);
		f1.setLocation(400, 300);
		f1.setLayout(new BorderLayout());
		f1.add(p4, BorderLayout.CENTER);
		f1.add(p6, BorderLayout.NORTH);
		p6.add(enres);
		p4.setLayout(new FlowLayout());
		p4.add(enlogin);
		p4.add(enlog);
		p4.add(enpassword);
		p4.add(enpasw);
		p4.add(enconfirm);
		p4.add(enconf);
		p4.add(enmailing);
		p4.add(enmail);
		f1.add(p5, BorderLayout.SOUTH);
		p5.add(encansel);
		p5.add(enregistry);
		f.repaint();
		enregistry.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (enmail.getText().equals("")||enconf.getText().equals("")||enlog.getText().equals("")||enpasw.getText().equals("")) {
					enres.setText("Заполните все поля");
				}
				else {
					if (enpasw.getText().equals(enconf.getText())) {
						password1 = enpasw.getText();
						login1 = enlog.getText();
						enres.setText("Регистрация успешна");
						f1.setVisible(false);
						f.setVisible(true);
					}
					else {
						enres.setText("пароли не совпадают");
						enpasw.setText("");
						enconf.setText("");
					}
				}
			}
		});
		encansel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				f.setVisible(true);
				f1.setVisible(false);
				
			}
		});
		
		
		f.setSize(260, 200);
		f.setLocation(400, 300);
		f.setLayout(new BorderLayout());
		f.add(p1, BorderLayout.CENTER);
		p1.setLayout(new FlowLayout());
		p1.add(login);
		p1.add(log);
		p1.add(password);
		p1.add(pasw);
		f.add(p2, BorderLayout.SOUTH);
		f.add(p3, BorderLayout.NORTH);
		p3.add(res);
		p2.add(enter);
		p2.add(cansel);
		p2.add(registry);
		f.setVisible(true);
		f.addWindowListener(this);
		enter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Войти")) {
					if (log.getText().equals(login1)&&pasw.getText().equals(password1)) {
						res.setText("Вход выполнен");
					}
					else {
						res.setText("Неверный логин или пароль");
					}
				
			}
			}
		});
		cansel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				windowClosing(this);
				
			}
		});
		registry.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				f.setVisible(false);
				f1.setVisible(true);
			}
		});
	}
	
	
	public void actionPerformed(ActionEvent e) {
		}
		
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void windowClosing(ActionListener actionListener) {
		// TODO Auto-generated method stub
		f.dispose();
		f1.dispose();
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		f.dispose();
		
	}
}
