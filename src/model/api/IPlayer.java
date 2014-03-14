package model.api;

import java.awt.Color;
import java.util.Observer;

public interface IPlayer {

	// TODO change this
	String getTeam();

	void setColor(Color color);

	Color getColor();

	int getId();

	void addObserver(Observer o);

}