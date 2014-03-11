package controller;

import java.awt.Color;
import java.util.TimerTask;

import model.Environment;
import model.api.IPlayer;
import model.api.IPoolBall;
import model.api.IPoolGame;

public class PoolTimerTask extends TimerTask {

	private IPoolGame model;
	private boolean wasStationary;

	public PoolTimerTask(IPoolGame model) {
		super();
		this.model = model;
		wasStationary = Environment.isStationary();
	}

	@Override
	public void run() {
		boolean isStationary = Environment.isStationary();
		if (isStationary && !wasStationary) {
			// Do rules regulation

			// check first ball hit need to extract isBlackHitFoulShot
			if (Environment.firstCollisionThisTurn == null) {
				System.out.println("Foul: No Ball Hit");
				model.setFoulShot(true);
			} else if (!Environment.firstCollisionThisTurn.getTeamColour()
					.equals(Color.BLACK)) {

				for (IPoolBall ballOnTable : model.getPoolTable().getBalls()) {
					if (ballOnTable.getTeamColour().equals(
							model.getCurrentPlayer().getColor())) {
						System.out
								.println("Foul: Black Hit First When other balls on table");
						model.setFoulShot(true);
						break;
					}
				}

			} else if (!Environment.firstCollisionThisTurn.getTeamColour()
					.equals(model.getCurrentPlayer())) {
				System.out.println("Foul: Opponent Ball Hit First");
				model.setFoulShot(true);
			}

			// check white ball potted
			for (IPoolBall ball : Environment.pottedThisTurn) {
				if (ball.getTeamColour().equals(Color.WHITE))
					System.out.println("Foul: Pocketed White");
				model.setFoulShot(true);
			}

			if (model.getCurrentPlayer().getColor() != null) {

				for (IPoolBall ball : Environment.pottedThisTurn) {
					// If potted opponents ball foul
					if (!ball.getTeamColour().equals(
							model.getCurrentPlayer().getColor())) {
						System.out.println("Foul: Potted Opponents Ball");
						model.setFoulShot(true);
					} else if (ball.getTeamColour().equals(Color.BLACK)) {
						// Check current player and state of table

						for (IPoolBall ballOnTable : model.getPoolTable()
								.getBalls()) {
							if (ballOnTable.getTeamColour().equals(
									model.getCurrentPlayer().getColor())) {
								System.out.println("GameOver: Player "
										+ model.getCurrentPlayer() + " looses");
								System.exit(1);
							}
						}
						System.out.println("GameOver: Player "
								+ model.getCurrentPlayer() + " Wins!");
						// TODO Check that no fouls have been made, a foul on
						// black pot meanse loose not win
						System.exit(1);
					}

				}

			} else { // no team assigned

				if (Environment.pottedThisTurn.size() > 0) {
					Color currentPlayerColor = Environment.pottedThisTurn
							.get(0).getTeamColour();
					// Check for black ball
					if (currentPlayerColor.equals(Color.BLACK)) {
						System.out.println("GameOver: Player "
								+ model.getCurrentPlayer() + " looses");
						System.exit(1);
					} else {

						for (IPlayer player : model.getPlayers()) {
							if (player.equals(model.getCurrentPlayer())) {
								player.setColor(currentPlayerColor);
							} else {
								if (currentPlayerColor.equals(Color.RED))
									player.setColor(Color.YELLOW);
								if (currentPlayerColor.equals(Color.YELLOW))
									player.setColor(Color.RED);

							}
						}
					}
				}// No balls potted

				// Still check for white ball and black ball potted
			}
			// Clear Move State
			Environment.firstCollisionThisTurn = null;
			Environment.pottedThisTurn.clear();
			// Then execute player game mechanics
			// If foul
			// Switch Player and Shot in hand
			if (model.isFoulShot()) {
				model.switchPlayer();
				model.setShotInHand(true);
				model.setFoulShot(false);
			} // else If potted Set shotinhand to true on pot and if shot in
				// hand true then dont switch player but setShotInHand to False
			else if (model.isShotInHand()) {
				model.setShotInHand(false);
			} else { // else switch player normal play
				model.switchPlayer();
				model.setShotInHand(false);
			}
			System.out.println("Player " + model.getCurrentPlayer().getId()
					+ "'s turn");
		}

		wasStationary = isStationary;
		model.getPoolTable().passTime();
	}
}
