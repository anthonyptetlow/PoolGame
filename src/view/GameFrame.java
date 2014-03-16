package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.api.IPoolGame;
import controller.BallCreationListener;
import controller.MyMouseMotionListener;

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
		PoolTablePanel table = setupTable();
		table.addMouseListener(new BallCreationListener(game.getPoolTable()));
		table.addMouseMotionListener(new MyMouseMotionListener(table));
		holder.add(table, BorderLayout.CENTER);
		holder.add(createBallTray(), BorderLayout.SOUTH);
		setSize(table.getWidth(), table.getHeight());
	}

	private JPanel setUpStats() {
		return new TurnPanel(game);
	}

	private PoolTablePanel setupTable() {
		return new PoolTablePanel(game.getPoolTable());
	}

	private JPanel createBallTray() {
		// // TODO Create A panel to display a list of pocketed pool balls
		// return tray;
		return new JPanel();
	}
}