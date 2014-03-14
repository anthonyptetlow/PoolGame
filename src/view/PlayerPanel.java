package view;

import javax.swing.JPanel;

import model.api.IPlayer;
import model.api.IPoolGame;

public class PlayerPanel extends JPanel {

	private IPoolGame model;

	public PlayerPanel(IPoolGame model) {
		this.model = model;
		for (IPlayer player : model.getPlayers()) {
			add(new PlayerLabel(player));
		}

	}

}
