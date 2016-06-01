package f;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import game.Clients;
import game.Game;

public class Serv {
	public ClientDriver client = null;
	Registry r = null;
	public Serv() {
	}
	public ClientDriver getClientDriver() {
		if (client == null) {
			try {
				client = new ClientDriver();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return client;
	}
	public Registry getRegistry() {
		if (r == null) {
			try {
				r = LocateRegistry.createRegistry(1099);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return r;
	}
	
	public void createRegistry(String url) throws RemoteException, MalformedURLException{
		//if (client.getSost()==1) {
		Registry r = getRegistry();
		getClientDriver().addClient(url);
		Naming.rebind(url, new Game());
		System.out.println("Created game with the url " + url);
		//}
	}
	public static void main(String[] args) {
		Serv s = new Serv();
		while(true){
			try {
				s.createRegistry("d");
			} catch (RemoteException | MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
