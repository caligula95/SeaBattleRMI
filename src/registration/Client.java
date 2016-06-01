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
		// ������������� ����������

		sock = new Socket(ip, port);

		// �������� ������ �����-������

		in = new DataInputStream(sock.getInputStream());

		out = new DataOutputStream(sock.getOutputStream());

	}

	// ��������� ������ ������� � �������� �����

	 public int sendQuery(int oper, String login, String email, String password) throws IOException

	{
		// ��������� ������
		 out.writeInt(oper);
		out.writeUTF(login);
		out.writeUTF(email);
		out.writeUTF(password);
		// ������� �����
		int res = in.readInt();
		return res;
	}
	// ��������� ����� �����

	public void disconnect() throws IOException
	{
		sock.close();
	}
	// ������� �����
	public static void main(String[] args) {
	}
}