package main;

import model.PoolGame;
import model.api.IPoolGame;
import view.GameFrame;

public class Play {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		IPoolGame game = new PoolGame();
		new GameFrame(game);
	}
}
