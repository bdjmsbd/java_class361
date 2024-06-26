package my.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import lombok.Data;
import program.Program;

@Data
public class G_Manager implements Program {

	private List<String> list = new ArrayList<>();
	private static Scanner sc = new Scanner(System.in);
//	private final static String row_init = "   0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15";
//	private final static String row = " ─── ─── ─── ─── ─── ─── ─── ─── ─── ─── ─── ─── ─── ─── ─── ";
//	private final static String col = "   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │   │";

	private final static String row_init = "   0 1 2 3 4 5 6 7 8 9 A B C D E F";
	private final static String row = "□ □ □ □ □ □ □ □ □ □ □ □ □ □ □ □";

	public static void clearConsoleScreen() {
//		System.out.print("Everything on the console will cleared");
		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
	}

	public void resetBoard() {

//		String row = " ";
//		String col = "   │";
//		for (int i = 0; i < 15; i++) {
//			row += "─── ";
//			col += "   │";
//		}

		if (list.size() == 0) {
			for (int i = 0; i < 16; i++) {
				list.add(row);
			}
		}

		System.out.println(row_init);
		for (int i = 0x0; i < 16; i++) {
			System.out.printf(" %X %s\n", i, list.get(i));
//			if (i != 15)
//				System.out.println(col);
		}
	}

	@Override
	public void printMenu() {
		// TODO Auto-generated method stub

	}

	@Override
	public void runMenu(int menu) {
		// TODO Auto-generated method stub

	}

	public boolean checkWin(int[][] stone, int curRow, int curCol) {
		int count = 0;
		int tmpRow;
		int tmpCol;

		tmpRow = curRow;
		tmpCol = curCol;
		while (tmpRow > 0) { // 좌 탐색
			if (stone[tmpRow][tmpCol] != 0) {
				count++;
				tmpRow--;
			} else if (stone[tmpRow][tmpCol] == 0) {
				tmpRow = curRow;
				break;
			}
		}
		while (tmpRow < 16) { // 우 탐색
			if (stone[tmpRow][tmpCol] != 0) {
				count++;
				tmpRow++;
			} else if (stone[tmpRow][tmpCol] == 0) {
				tmpRow = curRow;
				break;
			}
		}

		if (count >= 5) {
			System.out.println("승리");
			return true;
		} else {
			count = 0;
		}

		while (tmpCol > 0) { // 위 탐색
			if (stone[tmpRow][tmpCol] != 0) {
				count++;
				tmpCol--;
			} else if (stone[tmpRow][tmpCol] == 0) {
				tmpCol = curCol;
				break;
			}
		}

		while (tmpCol < 16) { // 아래 탐색
			if (stone[tmpRow][tmpCol] != 0) {
				count++;
				tmpCol++;
			} else if (stone[tmpRow][tmpCol] == 0) {
				tmpCol = curCol;
				break;
			}
		}

		if (count >= 5) {
			System.out.println("승리");
			return true;
		} else {
			count = 1;
		}

		while (tmpRow > 0 && tmpCol > 0) { // 위좌 탐색
			if (stone[tmpRow][tmpCol] != 0) {
				count++;
				tmpRow--;
				tmpCol--;
			} else if (stone[tmpRow][tmpCol] == 0) {
				tmpRow = curRow;
				tmpCol = curCol;
				break;
			}
		}
		while (tmpRow < 16 && tmpCol < 16) { // 아래우 탐색
			if (stone[tmpRow][tmpCol] != 0) {
				count++;
				tmpRow++;
				tmpCol++;
			} else if (stone[tmpRow][tmpCol] == 0) {
				tmpRow = curRow;
				tmpCol = curCol;
				break;
			}
		}

		if (count >= 5) {
			System.out.println("승리");
			return true;
		} else {
			count = 0;
		}

		tmpRow = curRow;
		tmpCol = curCol;
		while (tmpCol > 0 && tmpRow < 16) { // 위우 탐색
			if (stone[tmpRow][tmpCol] != 0) {
				count++;
				tmpRow++;
				tmpCol--;
			} else if (stone[tmpRow][tmpCol] == 0) {
				tmpRow = curRow;
				tmpCol = curCol;
				break;
			}
		}

		while (tmpCol < 16 && tmpRow > 16) { // 아래좌 탐색
			if (stone[tmpRow][tmpCol] != 0) {
				count++;
				tmpRow--;
				tmpCol++;
			} else if (stone[tmpRow][tmpCol] == 0) {
				tmpRow = curRow;
				tmpCol = curCol;
				break;
			}
		}

		if (count >= 5) {
//			System.out.println("승리");
			return true;
		} else {
			count = 1;
		}

		return false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		resetBoard();

		List<String> select = new ArrayList<String>();
		int sel_r, sel_c;
		int[][] whiteStone = new int[16][16];
		int[][] blackStone = new int[16][16];
		int count = 0;

		while (true) {
//			if (count % 2 == 0) // 흑돌 차례
			String nowTurn = (count++ % 2 == 0) ? "흑돌" : "백돌";
			System.out.print(nowTurn + " 위치 선택 (종료: exit): ");
			select = Arrays.asList(sc.nextLine().split(" "));
			if (select.get(0).equals("exit")) {
				System.out.println("종료합니다.");
				break;
			}

//			sel_r = Integer.parseInt(select.get(0));
//			sel_c = Integer.parseInt(select.get(1));
			sel_r = Integer.parseInt(select.get(0), 16);
			sel_c = Integer.parseInt(select.get(1), 16);

			if (whiteStone[sel_r][sel_c] != 0 || blackStone[sel_r][sel_c] != 0) {
				System.out.println("이미 선택한 위치입니다. " + nowTurn + " 패배<반칙패>");
				return;
			}
			char[] c = list.get(sel_c).toCharArray();

			if (nowTurn.equals("흑돌")) {
				blackStone[sel_r][sel_c]++;
				c[sel_r * 2] = 'B';
				if (checkWin(blackStone, sel_r, sel_c)) {
					System.out.println("흑돌 승리");
					return;
				}
			} else { // 백돌
				whiteStone[sel_r][sel_c]++;
				c[sel_r * 2] = 'W';
				if (checkWin(whiteStone, sel_r, sel_c)) {
					System.out.println("백돌 승리");
					return;
				}
			}
			// Integer.decode

//			c[sel_r * 4] = '○';
//			c[sel_r * 2] = 'X';
			list.set(sel_c, new String(c));
			clearConsoleScreen();
			resetBoard();
			System.out.println("선택한 위치: " + sel_r + ", " + sel_c);
//		String tmp = list.get(sel_c);
//		tmp.substring(, sel_c)
//		list.get(sel_c).charAt(sel_r * 4);
		}

	}

	@Override
	public void save(String fileName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(String fileName) {
		// TODO Auto-generated method stub

	}

}
