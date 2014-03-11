package model;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import model.api.IPlayer;
import model.api.IPoolGame;
import model.api.IPoolTable;

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
		currentPlayer = new Player(1);
		players.add(currentPlayer);
		players.add(new Player(2));
		shotInHand = false;
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
