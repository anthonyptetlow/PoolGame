package main;

import model.PoolTable;
import view.AppWindow;

public class Main {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		PoolTable model = new PoolTable();
		model.createBall();

		new AppWindow(model);

		for (int i = 0; i < 60; i++) {
			model.passTime();
			Thread.sleep(100);
		}
	}
}
