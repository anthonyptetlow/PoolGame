package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.api.IPoolGame;

public class TurnPanel extends JPanel implements Observer {

	private JLabel turn;
	private IPoolGame game;

	public TurnPanel(IPoolGame game) {
		super();
		this.game = game;
		this.game.addObserver(this);
		turn = new JLabel();
		turn.setText("Player " + game.getCurrentPlayer().getId() + "'s Turn");
		add(turn);

	}

	@Override
	public void update(Observable o, Object arg) {
		turn.setText("Player " + game.getCurrentPlayer().getId() + "'s Turn");

		repaint();
	}
}
