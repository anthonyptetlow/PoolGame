package ai.engine.imp;

import java.util.ArrayList;
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
					// get shot
					Vec2 possibleShot = getShotIfPossible(pocket, ball, white,
							balls);
					if (possibleShot != null)
						shots.add(possibleShot);
				}
			}
		}

		return new HashSet<Vec2>();
	}

	public Vec2 getShotIfPossible(IPocket p, IPoolBall b, IPoolBall w,
			List<IPoolBall> balls) {

		List<IPoolBall> allBalls = new ArrayList<IPoolBall>();
		allBalls.addAll(balls);
		allBalls.add(w);
		allBalls.remove(b);
		if (pathBetweenPointsClear(coordToVec2(p.getPosX(), p.getPosY()), b
				.getNode().getPosition(), allBalls)) {
			// TODO Calculate White-Ball Contact Point
			// double theta = Math.atan((p.getPosY() - b.getPosY())
			// / (p.getPosX() - b.getPosX()));
			// double dx = b.getRadius() * Math.cos(theta), dy = b.getRadius()
			// * Math.cos(theta);
			//
			// double xContact = b.getPosX() + dx;
			// double yContact = b.getPosY() + dy;
			// Vec2 vecContact = new Vec2((float) xContact, (float) yContact);
			// TODO Check Angle of attack -90 < x < 90
			// check path between this ball and white is clear
			// TODO change this as the collision point isnt the
			// centre of the ball
			if (pathBetweenPointsClear(b.getNode().getPosition(), w.getNode()
					.getPosition(), balls)) {
				// TODO change to calculated Shot
				// If clear add to set of clear paths maybe new
				// object {Pocket,
				// PoolBall, WhiteBall, ShotVector(only ind
				// direction), Option[power]
				return new Vec2();
			}
		}
		return null;
	}

	private Vec2 coordToVec2(float x, float y) {
		return new Vec2(x, y);
	}

	public boolean pathBetweenPointsClear(Vec2 ob1, Vec2 ob2,
			List<IPoolBall> balls) {
		for (IPoolBall ball : balls) {
			if (!ballNotOnPath(ob1, ob2, ball)) {
				return false;
			}
		}
		return true;
	}

	public boolean ballNotOnPath(Vec2 ob1, Vec2 ob2, IPoolBall ball) {
		// Math on sheet A for line intersecting a circle
		float a = ob1.x;
		float b = ob1.y;

		float c = ob2.x;
		float d = ob2.y;

		float x = ball.getPosX();
		float y = ball.getPosY();

		double theta = Math.atan(((b - y) / (a - x)))
				- Math.atan(((b - d) / (a - c)));

		double h = Math.sqrt(sq(a - x) + sq(b - y));

		double o = Math.abs(Math.sin(theta) * h);

		if ((o - ball.getRadius()) > ball.getRadius())
			return true;
		return false;
	}

	private double sq(double v) {
		return v * v;
	}

}
