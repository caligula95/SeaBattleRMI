package game;

import java.io.Serializable;
import java.util.ArrayList;

public class Mas implements Serializable {
 private int [][] masPlayer;
 private int [][] masComputer;
 String message;
 String messageComp;
public Mas() {
	// TODO Auto-generated constructor stub
	masPlayer = new int[10][10];
	masComputer = new int[10][10];
	message = "";
	messageComp = "";
}

public String getMessage() {
	return message;
}
public int[][] getMasPlayer() {
	return masPlayer;
}
public void setMasPlayer(int[][] masPlayer) {
	this.masPlayer = masPlayer;
}
public int[][] getMasComputer() {
	return masComputer;
}
public void setMasComputer(int[][] masComputer) {
	this.masComputer = masComputer;
}
 
}
