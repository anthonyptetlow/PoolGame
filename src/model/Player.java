package model;

import java.awt.Color;
import java.util.Observable;

import model.api.IPlayer;

public class Player extends Observable implements IPlayer {

	private Color color;
	private int id;

	public Player(int id) {
		color = null;
		this.id = id;
	}

	public Color getColor() {
		return color;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.api.IPlayer#setColor(java.awt.Color)
	 */
	@Override
	public void setColor(Color color) {
		this.color = color;
		setChanged();
		notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.api.IPlayer#getId()
	 */
	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getTeam() {

		if (color != null)
			if (color.equals(Color.RED) || color.equals(Color.red))
				return "Red";
			else if (color.equals(Color.YELLOW) || color.equals(Color.yellow))
				return "Yellow";
		return null;

	}
}
