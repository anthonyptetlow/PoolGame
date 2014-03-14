package model.api;

import java.awt.Color;
import java.util.Observer;
import java.util.Set;

public interface IPoolGame {

	IPoolTable getPoolTable();

	Set<IPlayer> getPlayers();

	IPlayer getCurrentPlayer();

	void switchPlayer();

	boolean isShotInHand();

	void setShotInHand(boolean shotInHand);

	boolean isFoulShot();

	void setFoulShot(boolean foulShot);

	void setPlayerColors(Color currentPlayer);

	void addObserver(Observer o);

}