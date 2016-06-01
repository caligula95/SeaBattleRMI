package registration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataBase {
	private Connection getConnection() throws Exception {
		// Подгрузка драйвера БД Derby
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		// Получение соединения с БД
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/user", "root", "1");
	}
	public	boolean insert(Users user) throws Exception {
		Connection con = getConnection();
		if (checkUsers(user.getLogin())){
		PreparedStatement pre = con.prepareStatement("Insert into users " + "(login, email, password) " + "values (?, ?, ?)");
		pre.setString(1, user.getLogin());
		pre.setString(2, user.getEmail());
		pre.setString(3, user.getPassword());
		pre.executeUpdate();
		pre.close();
		return true;
		}
		return false;
	}
	public boolean checkUsers(String login) throws Exception {
		Connection con = getConnection();
		PreparedStatement pre = con.prepareStatement("Select * from users " + "where login=?");
		pre.setString(1, login);
		ResultSet rs = pre.executeQuery();
		int count = 0;
		while(rs.next())
			count++;
		if (count>0) {
			System.out.println("User exist");
			return false;
		}
		return true;
	}
	public boolean checkUserRegistry(String login, String password) throws Exception {
		Connection con = getConnection();
		PreparedStatement pre = con.prepareStatement("Select * from users " + "where login=? and password=?");
		pre.setString(1, login);
		pre.setString(2, password);
		ResultSet rs = pre.executeQuery();
		int count = 0;
		while(rs.next())
			count++;
		if (count>0) {
			System.out.println("User found");
			return true;
		}
		return false;
		
	}

}
