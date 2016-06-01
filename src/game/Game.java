package game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import f.ConfServer;

public class Game extends UnicastRemoteObject implements ConfServer  {
	
	Mas m = new Mas();
	public boolean compTurn; // ������� ���� ���������� (false - ����� �����)
	// ������� ����� ����
	public Game() throws RemoteException {
	}
	// (0-���� ����, 1-������� �����,2-������� ���������)
	public int endGame;
	public void start() throws RemoteException {
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				m.getMasPlayer()[i][j] = 0;
				m.getMasComputer()[i][j] = 0;
			}
		}
		endGame = 0;
		compTurn = false;
		brawl(m.getMasPlayer());
		brawl(m.getMasComputer());
		
		
	}
	public void autoFilling(){
		brawl(m.getMasPlayer());
		brawl(m.getMasComputer());
	}

	// ������ ������� �������
	private void analysKilled(int[][] mas, int i, int j, int kolPalub) {
		// ���������� ������� �����
		int kolRanen = 0;
		// ��������� ������� ������� �����
		for (int k = i - (kolPalub - 1); k <= i + (kolPalub - 1); k++) {
			for (int g = j - (kolPalub - 1); g <= j + (kolPalub - 1); g++) {
				// ���� ��� ������ �������� �������
				if (testMasPosition(k, g) && (mas[k][g] == kolPalub + 7))
					kolRanen++;
			}
		}
		// ���� ���������� ������� ����� ��������� � ����������� �����
		// �������, �� �� ���� -���������� ����� 7
		if (kolRanen == kolPalub) {
			for (int k = i - (kolPalub - 1); k <= i + (kolPalub - 1); k++) {
				for (int g = j - (kolPalub - 1); g <= j + (kolPalub - 1); g++) {
					// ���� ��� ������ �������� �������
					if (testMasPosition(k, g) && (mas[k][g] == kolPalub + 7)) {
						// �������� ������� ������� �������
						mas[k][g] += 7;
						// �������� ������ ������� �������
						circleShooted(mas, k, g);
					}
				}
			}
		}
	}

	// �������� ���� �� �������
	private void testKilled(int[][] mas, int i, int j) {
		// ���� ������������
		if (mas[i][j] == 8)
		{
			// ������ �������
			mas[i][j] += 7;
			// �������� ������ �������
			circleShooted(mas, i, j);
		}
		// ���� ������������
		else if (mas[i][j] == 9)
			analysKilled(mas, i, j, 2);
		// ���� ������������
		else if (mas[i][j] == 10)
			analysKilled(mas, i, j, 3);
		// ���� ���������������
		else if (mas[i][j] == 11)
			analysKilled(mas, i, j, 4);
	}

	// ����������� ��������
	private void brawl(int[][] mas) {
		// ������� ���� ��������������� �������
		make4P(mas, 4);
		// ������� ��� ������������ �������
		for (int i = 1; i <= 2; i++)
			make4P(mas, 3);
		// ������� ��� ������������ �������
		for (int i = 1; i <= 3; i++)
			make4P(mas, 2);
		// ������� ������ ������������ �������
		make1P(mas);
	}

	// ������� ������
	public void playerShot(int i, int j) {
		// ��� �������� ���������� ����� 7
		m.getMasComputer()[i][j] += 7;
		// ��������� ���� �� �������
		testKilled(m.getMasComputer(), i, j);
		// ��������� ����� ����
		testEndGame();
		// ���� ��� ������ - �������� ��� ����������
		if (m.getMasComputer()[i][j] < 8) {
			compTurn = true; // �������� ��� ����������
			// ����� ���������- ���� �������� � ����
//			while (compTurn == true)
//				compTurn = computerShot(i, j);
		}
	}

	// ������� ���������� -
	// ���������� ������ - ���� �����
	public boolean computerShot(int i, int j) {
		// ������� ��������� � ����
		boolean res = false;
		// ��� �������� ���������� ����� 7
				m.getMasPlayer()[i][j] += 7;
				// ��������� ���� �� �������
				testKilled(m.getMasPlayer(), i, j);
				// ��������� ����� ����
				testEndGame();
				if (m.getMasPlayer()[i][j] < 8) {
					compTurn = false; // �������� ��� ����������
					// ����� ���������- ���� �������� � ����
//					while (compTurn == false)
//						compTurn = computerShot(i, j);
				}
		// ������� �������� � �������
		// �������
		
					
		return res;
	}

	// ���������� ���� ������� ��������� ��������� �������
	private void setCircleHit(int[][] mas, int i, int j) {
		// ���� �� ���������� ����� �� ������� �������
		// � � ������ ������� ��������
		if (testMasPosition(i, j) == true) {
			// ������������� ����������� ��������
			if ((mas[i][j] == -1) || (mas[i][j] == 6))
				mas[i][j]--;
		}
	}

	// ��������� ����� ������ ��������� ������
	private void circleShooted(int[][] mas, int i, int j) {
		setCircleHit(mas, i - 1, j - 1); // ������ �����
		setCircleHit(mas, i - 1, j); // ������
		setCircleHit(mas, i - 1, j + 1); // ������ ������
		setCircleHit(mas, i, j + 1); // ������
		setCircleHit(mas, i + 1, j + 1); // ����� ������
		setCircleHit(mas, i + 1, j); // �����
		setCircleHit(mas, i + 1, j - 1); // ����� �����
		setCircleHit(mas, i, j - 1); // �����
	}

	// �������� ��������� ����
	private void testEndGame() {
		// �������� ����� = 15*4+16*2*3+17*3*2+18*4
		// ��������, ����� ��� ������� �����
		int testNumber = 330;
		int kolComp = 0; // ����� ������ ����� ����������
		int kolPlay = 0; // ����� ������ ����� ������
		// ���������� ��� �������� ����� ���� ��������
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				// ��������� �������� ������ ������
				if (m.getMasPlayer()[i][j] >= 15)
					kolPlay += m.getMasPlayer()[i][j];
				// ��������� �������� ������ ����������
				if (m.getMasComputer()[i][j] >= 15)
					kolComp += m.getMasComputer()[i][j];
			}
		}
		if (kolPlay == testNumber)
			endGame = 2; // ���� ������� �����
		else if (kolComp == testNumber)
			endGame = 1; // ���� ������� ���������
	}

	// �������� �� ������ �� ������� �������
	private boolean testMasPosition(int i, int j) {
		if (((i >= 0) && (i <= 9)) && ((j >= 0) && (j <= 9))) {
			return true;
		} else
			return false;
	}

	// ������ �������� � ������ � ��������� ������ �������
	private void setMasValue(int[][] mas, int i, int j, int val) {
		if (testMasPosition(i, j) == true) {
			mas[i][j] = val;
		}
	}

	// ���������� ���� ������� ���������
	private void setCircle(int[][] mas, int i, int j, int val) {
		if (testMasPosition(i, j) && (mas[i][j] == 0))
			setMasValue(mas, i, j, val);
	}

	// ��������� ����� ������ ������
	private void okrBegin(int[][] mas, int i, int j, int val) {
		setCircle(mas, i - 1, j - 1, val); // ������ �����
		setCircle(mas, i - 1, j, val); // ������
		setCircle(mas, i - 1, j + 1, val); // ������ ������
		setCircle(mas, i, j + 1, val); // ������
		setCircle(mas, i + 1, j + 1, val); // ����� ������
		setCircle(mas, i + 1, j, val); // �����
		setCircle(mas, i + 1, j - 1, val); // ����� �����
		setCircle(mas, i, j - 1, val); // �����
	}

	// �������� ���������
	private void finalCircle(int[][] mas) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (mas[i][j] == -2)
					mas[i][j] = -1;
			}
		}
	}

	// �������� ������� ������������ ��������
	private void make1P(int[][] mas) {
		for (int k = 1; k <= 4; k++) {
			while (true) {
				int i = (int) (Math.random() * 10);
				int j = (int) (Math.random() * 10);
				if (mas[i][j] == 0) {
					// ��������� ������������ �������
					mas[i][j] = 1;
					okrBegin(mas, i, j, -1);
					break;
				}
			}
		}
	}

	// �������� ������ ��� ����������� ���������� � ��� ������ �������
	private boolean testNewDeck(int[][] mas, int i, int j) {
		if (testMasPosition(i, j) == false)
			return false;
		// ���� � ���� ������ 0 ��� -2, �� ��� ��� ��������
		if ((mas[i][j] == 0) || (mas[i][j] == -2))
			return true;
		return false;
	}

	// �������� ������� � ����������� �������� �� 2-� �� 4-�
	private void make4P(int[][] mas, int kolPaluba) {
		while (true) {
			boolean flag = false;
			int i = 0, j = 0;
			i = (int) (Math.random() * 10);
			j = (int) (Math.random() * 10);
			// �������� ��������� ����������� ���������� �������
			// 0 - �����, 1 -������, 2 - ����, 3 - �����
			int napr = (int) (Math.random() * 4);
			if (testNewDeck(mas, i, j) == true) {
				if (napr == 0) // �����
				{
					// ���� ����� ����������� ������
					if (testNewDeck(mas, i - (kolPaluba - 1), j) == true)
						flag = true;
				} else if (napr == 1) // ������
				{
					// ���� ����� ����������� ������
					if (testNewDeck(mas, i, j + (kolPaluba - 1)) == true)
						flag = true;
				} else if (napr == 2) // ����
				{
					// ���� ����� ����������� ������
					if (testNewDeck(mas, i + (kolPaluba - 1), j) == true)
						flag = true;
				} else if (napr == 3) // �����
				{
					// ���� ����� ����������� ������
					if (testNewDeck(mas, i, j - (kolPaluba - 1)) == true)
						flag = true;
				}
			}
			if (flag == true) {
				// �������� � ������ ����� �����
				mas[i][j] = kolPaluba;
				// �������� ����� ��������
				okrBegin(mas, i, j, -2);
				if (napr == 0) // �����
				{
					for (int k = kolPaluba - 1; k >= 1; k--) {
						// �������� � ������ ����� �����
						mas[i - k][j] = kolPaluba;
						// �������� ����� ��������
						okrBegin(mas, i - k, j, -2);
					}
				} else if (napr == 1) // ������
				{
					for (int k = kolPaluba - 1; k >= 1; k--) {
						// �������� � ������ ����� �����
						mas[i][j + k] = kolPaluba;
						// �������� ����� ��������
						okrBegin(mas, i, j + k, -2);
					}
				} else if (napr == 2) // ����
				{
					for (int k = kolPaluba - 1; k >= 1; k--) {
						// �������� � ������ ����� �����
						mas[i + k][j] = kolPaluba;
						// �������� ����� ��������
						okrBegin(mas, i + k, j, -2);
					}
				} else if (napr == 3) // �����
				{
					for (int k = kolPaluba - 1; k >= 1; k--) {
						// �������� � ������ ����� �����
						mas[i][j - k] = kolPaluba;
						// �������� ����� ��������
						okrBegin(mas, i, j - k, -2);
					}
				}
				break;
			}
		}
		finalCircle(mas);
	}
	
	public int[][] getMasPl(){
		return m.getMasPlayer();
	}
	public int[][] getMasComp(){
		return m.getMasComputer();
	}
	
	public void clearField() {
		for (int  i = 0; i < 10; i++) {
			for (int  j = 0; j < 10; j++) {
				m.getMasPlayer()[i][j] = 0;
				m.getMasComputer()[i][j] = 0;
			}
		}
	}
	public void rasstanVruchnu(int i, int j) {
		endGame = 0;
		compTurn = false;
		m.getMasPlayer()[i][j] = 1;
	}
	@Override
	public int getEndGame() throws RemoteException {
		// TODO Auto-generated method stub
		return endGame;
	}
	@Override
	public boolean compTurn() throws RemoteException {
		// TODO Auto-generated method stub
		return compTurn;
	}
	public String getMessage(){
		return m.message;
	}
	public String getMessageComp() {
		return m.messageComp;
	}
	public void setMessage(String mess) {
		m.message = mess;
	}
	public void setMessageComp(String mes) {
		m.messageComp = mes;
	}

}
