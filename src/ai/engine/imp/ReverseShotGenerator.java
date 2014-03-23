package ai.engine.imp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.api.IPocket;
import model.api.IPoolBall;
import model.api.IPoolGame;

import org.jbox2d.common.Vec2;

import ai.engine.IShotGenerator;

public class ReverseShotGenerator implements IShotGenerator {

	@Override
	// Note: Need to work pockets backfor the contact point
	// TODO if no shots available select one that hits right ball
	public Set<Vec2> getShots(IPoolGame game) {
		// TODO getPotentialShots
		List<IPoolBall> balls = game.getPoolTable().getBalls();
		List<IPocket> pockets = game.getPoolTable().getPockets();
		IPoolBall white = game.getPoolTable().getWhiteBall();

		Set<Vec2> shots = new HashSet<Vec2>();

		// For Each Pocket
		for (IPocket pocket : pockets) {
			// For Each Ball
			for (IPoolBall ball : balls) {
				// that is current players
				if (ball.getTeamColour().equals(
						game.getCurrentPlayer().getColor())) {
					// check Path between is clear
					if (pathBetweenBallsClear(
							coordToVec2(pocket.getPosX(), pocket.getPosY()),
							ball.getNode().getPosition())) {
						// TODO Check Angle of attack -90 < x < 90
						// check path between this ball and white is clear
						// TODO change this as the collision point isnt the
						// centre of the ball
						if (pathBetweenBallsClear(
								white.getNode().getPosition(), ball.getNode()
										.getPosition())) {
							// TODO change to calculated Shot
							// If clear add to set of clear paths maybe new
							// object {Pocket,
							// PoolBall, WhiteBall, ShotVector(only ind
							// direction), Option[power]
							shots.add(null);
						}
					}
				}
			}
		}

		return new HashSet<Vec2>();
	}

	private Vec2 coordToVec2(float x, float y) {
		return new Vec2(x, y);
	}

	private boolean pathBetweenBallsClear(Vec2 ob1, Vec2 ob2) {
		// TODO check Path is clear
		// for each ball
		// caculate math on sheet A
		// if (o- r traveling ball) < ball.r then return false

		return false;
	}

}
