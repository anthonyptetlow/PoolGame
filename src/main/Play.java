package main;

import java.util.Timer;

import model.Environment;
import model.PoolGame;
import model.api.IPoolGame;
import view.GameFrame;
import controller.PoolTimerTask;

public class Play {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IPoolGame game = new PoolGame();
		new GameFrame(game);
		Timer timer = new Timer();
		// TODO Move this to a more appropriate place
		timer.schedule(new PoolTimerTask(game), 0,
				(long) (Environment.timeStep * 100.0f));
	}
}
