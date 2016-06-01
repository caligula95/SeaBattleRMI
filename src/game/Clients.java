package game;

import java.io.Serializable;
import java.util.ArrayList;

public class Clients implements Serializable {
	private ArrayList<String> clients;
	private int sostoyanie;
	public Clients() {
		// TODO Auto-generated constructor stub
		clients = new ArrayList<String>();
		sostoyanie = 0;
	}
	public int getSost() {
		 return sostoyanie;
	}
	public void setSost(int s) {
		sostoyanie = s;
	}
	public ArrayList<String> getClients() {
		return clients;
	}
}
