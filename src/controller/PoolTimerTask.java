package controller;

import java.awt.Color;
import java.util.TimerTask;

import model.Environment;
import model.api.IPoolBall;
import model.api.IPoolGame;

public class PoolTimerTask extends TimerTask {

	private IPoolGame model;
	private boolean wasStationary;
	private boolean gameOver;

	public PoolTimerTask(IPoolGame model) {
		super();
		this.model = model;
		wasStationary = Environment.isStationary();
		gameOver = false;
	}

	@Override
	public void run() {
		// TODO Tidy this up
		boolean isStationary = Environment.isStationary();
		if (isStationary && !wasStationary) {
			// Do rules regulation

			// check first ball hit need to extract isBlackHitFoulShot
			if (Environment.firstCollisionThisTurn == null) {
				System.out.println("Foul: No Ball Hit");
				model.setFoulShot(true);
			} else if (Environment.firstCollisionThisTurn.getTeamColour()
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
					.equals(model.getCurrentPlayer().getColor())) {
				System.out.println("Foul: Opponent Ball Hit First");
				model.setFoulShot(true);
			}

			// check white ball potted
			for (IPoolBall ball : Environment.pottedThisTurn) {
				if (ball.getTeamColour().equals(Color.WHITE)) {
					System.out.println("Foul: Pocketed White");
					model.setFoulShot(true);
				}
			}

			if (model.getCurrentPlayer().getColor() != null) {
				for (IPoolBall ball : Environment.pottedThisTurn) {

					if (ball.getTeamColour().equals(Color.BLACK)) {
						// Check current player and state of table

						for (IPoolBall ballOnTable : model.getPoolTable()
								.getBalls()) {
							if (ballOnTable.getTeamColour().equals(
									model.getCurrentPlayer().getColor())) {
								gameOver = true;
								model.setFoulShot(true);
							}
						}
						gameOver = true;
					} else if (ball.getTeamColour().equals(
							model.getCurrentPlayer().getColor())) {
						model.setShotInHand(true);
					} else // If potted opponents ball foul
					if (!ball.getTeamColour().equals(
							model.getCurrentPlayer().getColor())) {
						System.out.println("Foul: Potted Opponents Ball");
						model.setFoulShot(true);
					}
				}
			} else { // no team assigned

				if (Environment.pottedThisTurn.size() > 0) {
					Color currentPlayerColor = Environment.pottedThisTurn
							.iterator().next().getTeamColour();
					// Check for black ball
					if (currentPlayerColor.equals(Color.BLACK)) {
						System.out.println("GameOver: Player "
								+ model.getCurrentPlayer() + " looses");
						System.exit(1);
					} else if (currentPlayerColor != Color.WHITE) {

						model.setPlayerColors(currentPlayerColor);
					}
				}// No balls potted

				// Still check for white ball and black ball potted
			}
			if (gameOver) {
				System.out
						.print("GameOver: Player " + model.getCurrentPlayer());
				if (model.isFoulShot())
					System.out.println(" Looses");
				else {
					System.out.println(" Wins");

				}
				System.exit(1);
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
