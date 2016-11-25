package com.ggw.gameoflife;

import java.util.concurrent.ThreadLocalRandom;

public class GameWith5x5 {

	public boolean[][] map = new boolean[5][5];
	public boolean[][] nextmap = new boolean[5][5];
	
	public GameWith5x5() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j ++) {
				map[i][j] = ThreadLocalRandom.current().nextBoolean();
			}
		}
	}
	
	public void print() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j ++) {
				System.out.print((map[i][j] ? 1 : 0) + "	");
			}
			System.out.println();
		}
	}
	
	private void copy(boolean[][] org, boolean[][] dist) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j ++) {
				dist[i][j] = org[i][j];
			}
		}
	}
	
	public void next() {
		copy(map, nextmap);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j ++) {
				boolean current = nextmap[i][j];
				int n = getCnt(i, j);
				if (current) {
					if (n < 2 || n > 3)
						nextmap[i][j] = false;
				} else if (n == 3) {
					nextmap[i][j] = true;
				}
			}
		}
		copy(nextmap, map);
	}
	
	private int getCnt(int i, int j) {
		int cnt = 0;
		if (chkNode(i - 1, j - 1)) cnt ++;
		if (chkNode(i - 1, j)) cnt ++;
		if (chkNode(i - 1, j + 1)) cnt ++;
		if (chkNode(i, j - 1)) cnt ++;
		if (chkNode(i, j + 1)) cnt ++;
		if (chkNode(i + 1, j - 1)) cnt ++;
		if (chkNode(i + 1, j)) cnt ++;
		if (chkNode(i + 1, j + 1)) cnt ++;
		return cnt;
	}
	
	private boolean chkNode(int i, int j) {
		try {
			return map[i][j];
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		GameWith5x5 app = new GameWith5x5();
		
		app.print();
		System.out.println("#################################");
		
		while (true) {
			app.next();
			app.print();
			System.out.println("#################################");
			Thread.sleep(500);
		}
		
	}
	
}
