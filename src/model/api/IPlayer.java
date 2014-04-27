package model.api;

import java.awt.Color;

public interface IPlayer {

	String getTeam();

	void setColor(Color color);

	Color getColor();

	int getId();

}