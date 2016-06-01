package registration;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
	private Socket sock = null;
	private DataOutputStream out = null;
	private DataInputStream in = null;
	public Client(String ip, int port) throws IOException
	{
		// Устанавливаем соединение

		sock = new Socket(ip, port);

		// Получаем потоки ввода-вывода

		in = new DataInputStream(sock.getInputStream());

		out = new DataOutputStream(sock.getOutputStream());

	}

	// отправить запрос серверу и получить ответ

	 public int sendQuery(int oper, String login, String email, String password) throws IOException

	{
		// отправляю запрос
		 out.writeInt(oper);
		out.writeUTF(login);
		out.writeUTF(email);
		out.writeUTF(password);
		// получаю ответ
		int res = in.readInt();
		return res;
	}
	// посчитать сумму чисел

	public void disconnect() throws IOException
	{
		sock.close();
	}
	// главный метод
	public static void main(String[] args) {
	}
}