package view;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import model.api.IPlayer;

public class PlayerLabel extends JLabel implements Observer {

	private IPlayer player;

	public PlayerLabel(IPlayer player) {
		super("Player " + player.getId());
		this.player = player;
		player.addObserver(this);
		update(null, null);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (player.getColor() != null) {
			String color;
			if (player.getColor() == Color.RED) {
				color = "Red";
			} else {
				color = "Yellow";
			}
			setText("Player " + player.getId() + ": " + color);
		}
	}
}
