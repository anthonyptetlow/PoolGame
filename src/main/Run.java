package main;

import java.util.Timer;

import model.Environment;
import model.PoolGame;
import view.AppWindow;
import controller.PoolTimerTask;

public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PoolGame p = new PoolGame();
		AppWindow window = new AppWindow(p.getPoolTable());
		Timer timer = new Timer();
		// Set at 10ms
		timer.schedule(new PoolTimerTask(p.getPoolTable()), 0,
				(long) (Environment.timeStep * 100.0f));
	}
}
