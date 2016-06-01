package registration;
import java.io.DataInputStream;

import java.io.DataOutputStream;

import java.io.IOException;

import java.net.ServerSocket;

import java.net.Socket;

public class Server {

	private ServerSocket server = null;
	private Socket sock = null;
	private DataOutputStream out = null;
	private DataInputStream in = null;
	// запустить сервер
	Users user;
	DataBase database = new DataBase();

	public void start(int port) throws IOException
	{
		server = new ServerSocket(port);
		while (true)
		{
			// Принимаем соединение от нового клиента
			sock = server.accept();
			// Получаем потоки ввода-вывода
			in = new DataInputStream(sock.getInputStream());
			out = new DataOutputStream(sock.getOutputStream());
			// Пока соединение активно, обрабатываем запросы
			while (processQuery());
		}
	}
	// обработка запроса
	private boolean processQuery()
	{
		try

		{
			// Получаю запрос от клиента
			int oper = in.readInt();
			String login = in.readUTF(); 
			String email = in.readUTF();
			String password = in.readUTF();
			user = new Users(login, email, password);
			int result; // Результат операции
			switch (oper) {
			case 0:
				if (database.insert(user)) {
					result = 1;
				}
				else result = 3;
				break;
			case 1:
				//database.checkUserRegistry(login, password);
				if (database.checkUserRegistry(login, password)==true) {
					result = 1;
				}
				else 
				result = 3;
				break;
			default:
				System.out.println("Illegal command");
				result = 0;
			}
			// Отправляю результат клиенту
			out.writeInt(result);
			return true;
		}
		catch (IOException e)
		{
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	// главный метод
	public static void main(String[] args)
	{
		try

		{
			Server srv = new Server();
			srv.start(12345);
		}
		catch (IOException e)
		{
			System.out.println("Возникла ошибка");
		}
	}
}