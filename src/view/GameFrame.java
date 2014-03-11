package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.api.IPlayer;
import model.api.IPoolGame;
import controller.BallCreationListener;

public class GameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9222311713904614969L;
	private IPoolGame game;

	public GameFrame(IPoolGame model) {
		super();
		this.game = model;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		JPanel holder = new JPanel();
		holder.setLayout(new BorderLayout());
		this.setContentPane(holder);
		holder.add(setUpStats(), BorderLayout.NORTH);
		JPanel table = setupTable();
		table.addMouseListener(new BallCreationListener(game.getPoolTable()));
		holder.add(table, BorderLayout.CENTER);
		holder.add(createBallTray(), BorderLayout.SOUTH);
		setSize(table.getWidth(), table.getHeight());
	}

	private JPanel setUpStats() {
		// TODO Rework this panel to use a Layout manager
		JPanel stats = new JPanel();
		for (IPlayer player : game.getPlayers()) {
			String label = "Player " + player.getId();
			if (player.getTeam() != null) {
				label += ":\t" + player.getTeam();
			}
			stats.add(new JLabel(label));
		}
		return stats;
	}

	private JPanel setupTable() {
		return new PoolTablePanel(game.getPoolTable());
	}

	private JPanel createBallTray() {
		// JPanel tray = new JPanel();
		// tray.add(new JLabel("Player " + game.getCurrentPlayer().getId()
		// + "'s Turn"));
		//
		// // TODO Create A panel to display a list of pocketed pool balls
		// return tray;
		return new TurnPanel(game);
	}
}