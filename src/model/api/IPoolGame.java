package model.api;

import java.util.Observer;
import java.util.Set;

public interface IPoolGame {

	IPoolTable getPoolTable();

	Set<IPlayer> getPlayers();

	IPlayer getCurrentPlayer();

	void switchPlayer();

	void addObserver(Observer o);

	boolean isShotInHand();

	void setShotInHand(boolean shotInHand);

	boolean isFoulShot();

	void setFoulShot(boolean foulShot);
}