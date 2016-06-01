package game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import f.ConfServer;

public class Game extends UnicastRemoteObject implements ConfServer  {
	
	Mas m = new Mas();
	public boolean compTurn; // Признак хода компьютера (false - ходит игрок)
	// Признак конца игры
	public Game() throws RemoteException {
	}
	// (0-игра идет, 1-победил игрок,2-победил компьютер)
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

	// Анализ убитого корабля
	private void analysKilled(int[][] mas, int i, int j, int kolPalub) {
		// Количество раненых палуб
		int kolRanen = 0;
		// Выполняем подсчет раненых палуб
		for (int k = i - (kolPalub - 1); k <= i + (kolPalub - 1); k++) {
			for (int g = j - (kolPalub - 1); g <= j + (kolPalub - 1); g++) {
				// Если это палуба раненого корабля
				if (testMasPosition(k, g) && (mas[k][g] == kolPalub + 7))
					kolRanen++;
			}
		}
		// Если количество раненых палуб совпадает с количеством палуб
		// корабля, то он убит -прибавляем число 7
		if (kolRanen == kolPalub) {
			for (int k = i - (kolPalub - 1); k <= i + (kolPalub - 1); k++) {
				for (int g = j - (kolPalub - 1); g <= j + (kolPalub - 1); g++) {
					// Если это палуба раненого корабля
					if (testMasPosition(k, g) && (mas[k][g] == kolPalub + 7)) {
						// помечаем палубой убитого корабля
						mas[k][g] += 7;
						// окружаем палубу убитого корабля
						circleShooted(mas, k, g);
					}
				}
			}
		}
	}

	// Проверка убит ли корабль
	private void testKilled(int[][] mas, int i, int j) {
		// Если однопалубный
		if (mas[i][j] == 8)
		{
			// делаем выстрел
			mas[i][j] += 7;
			// окружаем убитый корабль
			circleShooted(mas, i, j);
		}
		// Если двухпалубный
		else if (mas[i][j] == 9)
			analysKilled(mas, i, j, 2);
		// Если трехпалубный
		else if (mas[i][j] == 10)
			analysKilled(mas, i, j, 3);
		// Если четырехпалубный
		else if (mas[i][j] == 11)
			analysKilled(mas, i, j, 4);
	}

	// Расстановка кораблей
	private void brawl(int[][] mas) {
		// Создаем один четырехпалубный корабль
		make4P(mas, 4);
		// Создаем два трехпалубных корабля
		for (int i = 1; i <= 2; i++)
			make4P(mas, 3);
		// Создаем три двухпалубных корабля
		for (int i = 1; i <= 3; i++)
			make4P(mas, 2);
		// Создаем четыре однопалубных корабля
		make1P(mas);
	}

	// Выстрел игрока
	public void playerShot(int i, int j) {
		// При выстреле прибавляем число 7
		m.getMasComputer()[i][j] += 7;
		// Проверяем убит ли корабль
		testKilled(m.getMasComputer(), i, j);
		// Проверяем конец игры
		testEndGame();
		// Если был промах - передаем ход компьютеру
		if (m.getMasComputer()[i][j] < 8) {
			compTurn = true; // передаем ход компьютеру
			// Ходит компьютер- пока попадает в цель
//			while (compTurn == true)
//				compTurn = computerShot(i, j);
		}
	}

	// Выстрел компьютера -
	// возвращает истину - если попал
	public boolean computerShot(int i, int j) {
		// Признак попадания в цель
		boolean res = false;
		// При выстреле прибавляем число 7
				m.getMasPlayer()[i][j] += 7;
				// Проверяем убит ли корабль
				testKilled(m.getMasPlayer(), i, j);
				// Проверяем конец игры
				testEndGame();
				if (m.getMasPlayer()[i][j] < 8) {
					compTurn = false; // передаем ход компьютеру
					// Ходит компьютер- пока попадает в цель
//					while (compTurn == false)
//						compTurn = computerShot(i, j);
				}
		// Признак выстрела в раненый
		// корабль
		
					
		return res;
	}

	// Установить один элемент окружения подбитого корабля
	private void setCircleHit(int[][] mas, int i, int j) {
		// Если не происходит выход за пределы массива
		// и в ячейке нулевое значение
		if (testMasPosition(i, j) == true) {
			// Устанавливаем необходимое значение
			if ((mas[i][j] == -1) || (mas[i][j] == 6))
				mas[i][j]--;
		}
	}

	// Окружение одной ячейки подбитого вокруг
	private void circleShooted(int[][] mas, int i, int j) {
		setCircleHit(mas, i - 1, j - 1); // сверху слева
		setCircleHit(mas, i - 1, j); // сверху
		setCircleHit(mas, i - 1, j + 1); // сверху справа
		setCircleHit(mas, i, j + 1); // справа
		setCircleHit(mas, i + 1, j + 1); // снизу справа
		setCircleHit(mas, i + 1, j); // снизу
		setCircleHit(mas, i + 1, j - 1); // снизу слева
		setCircleHit(mas, i, j - 1); // слева
	}

	// Проверка окончания игры
	private void testEndGame() {
		// Тестовое число = 15*4+16*2*3+17*3*2+18*4
		// Ситуация, когда все корабли убиты
		int testNumber = 330;
		int kolComp = 0; // Сумма убитых палуб компьютера
		int kolPlay = 0; // Сумма убитых палуб игрока
		// Перебираем все элементы сразу двух массивов
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				// Суммируем подбитые палубы игрока
				if (m.getMasPlayer()[i][j] >= 15)
					kolPlay += m.getMasPlayer()[i][j];
				// Суммируем подбитые палубы компьютера
				if (m.getMasComputer()[i][j] >= 15)
					kolComp += m.getMasComputer()[i][j];
			}
		}
		if (kolPlay == testNumber)
			endGame = 2; // Если победил игрок
		else if (kolComp == testNumber)
			endGame = 1; // Если победил компьютер
	}

	// Проверка не выхода за границы массива
	private boolean testMasPosition(int i, int j) {
		if (((i >= 0) && (i <= 9)) && ((j >= 0) && (j <= 9))) {
			return true;
		} else
			return false;
	}

	// Запись значения в массив с проверкой границ массива
	private void setMasValue(int[][] mas, int i, int j, int val) {
		if (testMasPosition(i, j) == true) {
			mas[i][j] = val;
		}
	}

	// Установить один элемент окружения
	private void setCircle(int[][] mas, int i, int j, int val) {
		if (testMasPosition(i, j) && (mas[i][j] == 0))
			setMasValue(mas, i, j, val);
	}

	// Окружение одной ячейки вокруг
	private void okrBegin(int[][] mas, int i, int j, int val) {
		setCircle(mas, i - 1, j - 1, val); // сверху слева
		setCircle(mas, i - 1, j, val); // сверху
		setCircle(mas, i - 1, j + 1, val); // сверху справа
		setCircle(mas, i, j + 1, val); // справа
		setCircle(mas, i + 1, j + 1, val); // снизу справа
		setCircle(mas, i + 1, j, val); // снизу
		setCircle(mas, i + 1, j - 1, val); // снизу слева
		setCircle(mas, i, j - 1, val); // слева
	}

	// Конечное окружение
	private void finalCircle(int[][] mas) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (mas[i][j] == -2)
					mas[i][j] = -1;
			}
		}
	}

	// Создание четырех однопалубных кораблей
	private void make1P(int[][] mas) {
		for (int k = 1; k <= 4; k++) {
			while (true) {
				int i = (int) (Math.random() * 10);
				int j = (int) (Math.random() * 10);
				if (mas[i][j] == 0) {
					// Размещаем однопалубный корабль
					mas[i][j] = 1;
					okrBegin(mas, i, j, -1);
					break;
				}
			}
		}
	}

	// Проверка ячейки для возможности размещения в ней палубы корабля
	private boolean testNewDeck(int[][] mas, int i, int j) {
		if (testMasPosition(i, j) == false)
			return false;
		// Если в этой ячейке 0 или -2, то она нам подходит
		if ((mas[i][j] == 0) || (mas[i][j] == -2))
			return true;
		return false;
	}

	// Создание корабля с несколькими палубами от 2-х до 4-х
	private void make4P(int[][] mas, int kolPaluba) {
		while (true) {
			boolean flag = false;
			int i = 0, j = 0;
			i = (int) (Math.random() * 10);
			j = (int) (Math.random() * 10);
			// Выбираем случайное направление построения корабля
			// 0 - вверх, 1 -вправо, 2 - вниз, 3 - влево
			int napr = (int) (Math.random() * 4);
			if (testNewDeck(mas, i, j) == true) {
				if (napr == 0) // вверх
				{
					// Если можно расположить палубу
					if (testNewDeck(mas, i - (kolPaluba - 1), j) == true)
						flag = true;
				} else if (napr == 1) // вправо
				{
					// Если можно расположить палубу
					if (testNewDeck(mas, i, j + (kolPaluba - 1)) == true)
						flag = true;
				} else if (napr == 2) // вниз
				{
					// Если можно расположить палубу
					if (testNewDeck(mas, i + (kolPaluba - 1), j) == true)
						flag = true;
				} else if (napr == 3) // влево
				{
					// Если можно расположить палубу
					if (testNewDeck(mas, i, j - (kolPaluba - 1)) == true)
						flag = true;
				}
			}
			if (flag == true) {
				// Помещаем в ячейку число палуб
				mas[i][j] = kolPaluba;
				// Окружаем минус двойками
				okrBegin(mas, i, j, -2);
				if (napr == 0) // вверх
				{
					for (int k = kolPaluba - 1; k >= 1; k--) {
						// Помещаем в ячейку число палуб
						mas[i - k][j] = kolPaluba;
						// Окружаем минус двойками
						okrBegin(mas, i - k, j, -2);
					}
				} else if (napr == 1) // вправо
				{
					for (int k = kolPaluba - 1; k >= 1; k--) {
						// Помещаем в ячейку число палуб
						mas[i][j + k] = kolPaluba;
						// Окружаем минус двойками
						okrBegin(mas, i, j + k, -2);
					}
				} else if (napr == 2) // вниз
				{
					for (int k = kolPaluba - 1; k >= 1; k--) {
						// Помещаем в ячейку число палуб
						mas[i + k][j] = kolPaluba;
						// Окружаем минус двойками
						okrBegin(mas, i + k, j, -2);
					}
				} else if (napr == 3) // влево
				{
					for (int k = kolPaluba - 1; k >= 1; k--) {
						// Помещаем в ячейку число палуб
						mas[i][j - k] = kolPaluba;
						// Окружаем минус двойками
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
