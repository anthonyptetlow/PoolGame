package main;

import java.util.Timer;

import model.Environment;
import model.PoolTable;
import view.AppWindow;
import controller.PoolTimerTask;

public class BallCushionPrototype {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		PoolTable model = new PoolTable();
		model.createBall(30.0f, 40.0f);
		model.createBoarder();
		new AppWindow(model);

		Timer timer = new Timer();
		// Set at 10ms
		timer.schedule(new PoolTimerTask(model), 0,
				(long) (Environment.timeStep * 100.0f));

	}
}
