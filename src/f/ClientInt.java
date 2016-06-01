package f;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ClientInt extends Remote {
	void addClient(String url) throws RemoteException;
	void removeClient(String url) throws RemoteException;
	ConfServer registerClient(String url) throws RemoteException;
}
