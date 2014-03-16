package model;

import java.awt.Color;
import java.util.LinkedHashSet;
import java.util.Set;

import model.api.IPoolBall;
import model.api.IPoolGame;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import Util.Properties;
import controller.PoolCollisionListener;

public class Environment {
	// TODO fix this static references
	public static final World world = new World(new Vec2(0, 0.0f));

	public static final float timeStep = 1.0f / 24.0f;

	private final int velocityIterations = 6;

	private final int positionIterations = 2;

	public static Set<Body> bodiesToRemove;

	private static IPoolGame poolGame;

	public static Set<IPoolBall> pottedThisTurn;

	public static IPoolBall firstCollisionThisTurn;

	public Environment(IPoolGame poolGame) {
		bodiesToRemove = new LinkedHashSet<Body>();
		world.setContactListener(new PoolCollisionListener());
		this.poolGame = poolGame;
		pottedThisTurn = new LinkedHashSet<IPoolBall>();
		firstCollisionThisTurn = null;
	}

	public void step() {
		// Step world
		world.step(timeStep, velocityIterations, positionIterations);
		// Remove Pocketed Bodies
		for (Body body : bodiesToRemove) {
			// Remove white ball and sort table
			checkWhiteAndRemove(body);
			poolGame.getPoolTable().getBalls().remove(body.getUserData());
			poolGame.getPoolTable().pocket((PoolBall) body.getUserData());
			pottedThisTurn.add((IPoolBall) body.getUserData());
			world.destroyBody(body);
		}

		bodiesToRemove.clear();

	}

	private void checkWhiteAndRemove(Body body) {
		if (((PoolBall) body.getUserData()).getTeamColour().equals(Color.white)) {
			System.out.println("Remove White");
			poolGame.getPoolTable().removeWhite();
			poolGame.setFoulShot(true);
		}
	}

	public static boolean isStationary() {
		Body body = Environment.world.getBodyList();
		if (poolGame.getPoolTable().getWhiteBall() != null
				&& poolGame.getPoolTable().getWhiteBall().getNode()
						.getLinearVelocity().length() > Properties.STATIONARY_VELOCITY)
			return false;
		while ((body = body.m_next) != null) {
			if (body.getLinearVelocity().length() > Properties.STATIONARY_VELOCITY)
				return false;
		}
		return true;
	}
}
