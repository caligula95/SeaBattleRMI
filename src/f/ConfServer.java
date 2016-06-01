package f;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConfServer extends Remote {
	void start() throws RemoteException;
	void playerShot(int i, int j) throws RemoteException;
	int[][] getMasPl() throws RemoteException;
	int[][] getMasComp() throws RemoteException;
	int getEndGame() throws RemoteException;
	boolean computerShot(int i, int j) throws RemoteException;
	void autoFilling() throws RemoteException;
	void rasstanVruchnu(int i, int j) throws RemoteException;
	void clearField() throws RemoteException;
	boolean compTurn() throws RemoteException;
	String getMessageComp() throws RemoteException;
	String getMessage() throws RemoteException;
	void setMessageComp(String message) throws RemoteException;
	void setMessage(String message) throws RemoteException;
}
