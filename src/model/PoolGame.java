package model;

import java.awt.Color;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;
import java.util.Timer;

import model.api.IPlayer;
import model.api.IPoolGame;
import model.api.IPoolTable;
import controller.PoolTimerTask;

public class PoolGame extends Observable implements IPoolGame {

	private IPoolTable poolTable;
	private Set<IPlayer> players;
	private IPlayer currentPlayer;
	private boolean shotInHand;
	private boolean foulShot;
	private Environment environment;

	public PoolGame() {
		rackTable();
		players = new HashSet<IPlayer>();

		currentPlayer = new Player(2);
		currentPlayer.setColor(Color.YELLOW);
		players.add(currentPlayer);
		currentPlayer = new Player(1);
		currentPlayer.setColor(Color.RED);
		players.add(currentPlayer);
		shotInHand = false;
		// Start the physics loop
		Timer timer = new Timer();
		timer.schedule(new PoolTimerTask(this), 0,
				(long) (Environment.timeStep * 100.0f));
	}

	private void rackTable() {
		environment = new Environment(this);
		poolTable = new PoolTable(environment);
	}

	@Override
	public IPoolTable getPoolTable() {
		return poolTable;
	}

	@Override
	public Set<IPlayer> getPlayers() {
		return players;
	}

	public IPlayer getCurrentPlayer() {
		return currentPlayer;

	}

	public void setPlayerColors(Color currentPlayerColor) {

		for (IPlayer player : players) {
			if (player.equals(currentPlayer)) {
				player.setColor(currentPlayerColor);
			} else {
				if (currentPlayerColor.equals(Color.RED))
					player.setColor(Color.YELLOW);
				if (currentPlayerColor.equals(Color.YELLOW))
					player.setColor(Color.RED);

			}
		}

		setChanged();
		notifyObservers();
	}

	@Override
	public void switchPlayer() {
		for (IPlayer player : players) {
			if (!player.equals(currentPlayer)) {
				currentPlayer = player;
				break;
			}
		}
		setChanged();
		notifyObservers();
	}

	public boolean isShotInHand() {
		return shotInHand;
	}

	public void setShotInHand(boolean shotInHand) {
		this.shotInHand = shotInHand;
	}

	public boolean isFoulShot() {
		return foulShot;
	}

	public void setFoulShot(boolean foulShot) {
		this.foulShot = foulShot;
	}
}
