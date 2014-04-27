package ai.imp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.api.IPocket;
import model.api.IPoolBall;
import model.api.IPoolGame;

import org.jbox2d.common.Vec2;

import ai.IShotGenerator;

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

		if (pathBetweenPointsClear(coordToVec2(p.getPosX(), p.getPosY()),
				coordToVec2(b.getPosX(), b.getPosY()), allBalls)) {
			// TODO Calculate White-Ball Contact Point
			Vec2 contactPoint = calculateWhiteBallCentreForContactPoint(p, b);
			// check path between this ball and white is clear
			allBalls.remove(w);
			allBalls.add(b);
			if (pathBetweenPointsClear(contactPoint,
					coordToVec2(w.getPosX(), w.getPosY()), allBalls)) {

				// float perpGrad = perpGradient(
				// coordToVec2(b.getPosX(), b.getPosY()), contactPoint);

				// If clear add to set of clear paths maybe new
				// object {Pocket,
				// PoolBall, WhiteBall, ShotVector(only ind
				// direction), Option[power]
				float xVector = contactPoint.x - w.getPosX();
				float yVector = contactPoint.y - w.getPosY();

				// TODO Check Angle of attack -90 < x < 90
				// If angle between perpGrad and shot 0 < x < 180
				// System.out.println(perpGrad);
				// double angle1 = Math.atan(perpGrad);
				// double angle2 = Math.atan(yVector / xVector);
				// System.out.println("Angle1:" + angle1);
				// System.out.println("Angle2:" + angle2);
				// if (0 < (angle2 - angle1) && (angle2 - angle1) < 3.14) {
				return new Vec2(xVector, yVector);
				// }
			}
		}
		return null;
	}

	// private float perpGradient(Vec2 ball, Vec2 contactPoint) {
	// System.out.println("Ball: " + ball);
	// System.out.println("ContPoint: " + contactPoint);
	// return -1.0f / ((ball.y - contactPoint.y) / (ball.x - contactPoint.x));
	// }

	private Vec2 calculateWhiteBallCentreForContactPoint(IPocket p, IPoolBall b) {
		double theta = Math.atan((p.getPosY() - b.getPosY())
				/ (p.getPosX() - b.getPosX()));
		double dx = b.getRadius() * Math.cos(theta), dy = b.getRadius()
				* Math.cos(theta);

		double xContact = b.getPosX() + 2 * dx;
		double yContact = b.getPosY() + 2 * dy;
		return new Vec2((float) xContact, (float) yContact);
	}

	private Vec2 coordToVec2(float x, float y) {
		return new Vec2(x, y);
	}

	public boolean pathBetweenPointsClear(Vec2 ob1, Vec2 ob2,
			List<IPoolBall> balls) {
		for (IPoolBall ball : balls) {
			if (ballOnPath(ob1, ob2, ball)) {
				return false;
			}
		}
		return true;
	}

	public boolean ballOnPath(Vec2 ob1, Vec2 ob2, IPoolBall ball) {
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

		if ((o - ball.getRadius()) < ball.getRadius()) {
			// Calculate n = (a,b) -> (x,y)
			double n = Math.sqrt(sq(a - x) + sq(b - y));
			// Calculate m = (c,d) -> (x,y)
			double m = Math.sqrt(sq(c - x) + sq(d - y));
			// Calculate p = sqrt(n^2 - o ^2)
			double p = Math.sqrt(sq(n) - sq(o));
			// Calculate q = sqrt(m^2 - o ^2)
			double q = Math.sqrt(sq(m) - sq(o));
			// if(p + q <= (dist (a,b) -> (c,d) return false
			if (p + q <= (Math.sqrt(sq(a - c) + sq(b - d)) + (2 * ball
					.getRadius())))
				return true;
		}
		return false;
	}

	private double sq(double v) {
		return v * v;
	}

}
