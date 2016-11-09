package f;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import game.Clients;

public class ClientDriver extends UnicastRemoteObject implements ClientInt {
	
	Clients clients = null;
	public ClientDriver() throws RemoteException {
		clients = getClients();
	}
	public Clients getClients() {
		if (clients == null) {
			clients = new Clients();
			return clients;
		}
		return clients;
	}
	//added some comments

	@Override
	public void addClient(String url) throws RemoteException {
		// TODO Auto-generated method stub
		clients.getClients().add(url);
	}
	public String printClientName(){
		for (int i = 0; i < clients.getClients().size(); i++) {
			return clients.getClients().get(i);
		}
		return "";
	}

	@Override
	public void removeClient(String url) throws RemoteException {
		for (int i = 0; i < clients.getClients().size(); i++) {
			if (clients.getClients().get(i).equals(url)) {
				clients.getClients().remove(i);
			}
		}
		
	}
	
	public int getSost() {
		return clients.getSost();
	}
	public void setSost(int s){
		clients.setSost(s);
	}

	@Override
	public ConfServer registerClient(String url) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
