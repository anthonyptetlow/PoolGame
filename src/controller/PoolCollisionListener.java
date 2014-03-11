package controller;

import java.awt.Color;

import model.Environment;
import model.Pocket;
import model.api.IPoolBall;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;

public class PoolCollisionListener implements ContactListener {

	public PoolCollisionListener() {
	}

	@Override
	public void beginContact(Contact contact) {
		Body body1 = contact.getFixtureA().getBody();
		Body body2 = contact.getFixtureB().getBody();

		// Check for any balls and remove them
		checkPocketedAndRemoveBall(body1, body2);
		checkPocketedAndRemoveBall(body2, body1);

		// Check for first collision with another ball
		if (Environment.firstCollisionThisTurn == null)
			Environment.firstCollisionThisTurn = getBallFromWhiteCollision(
					body1, body2);
	}

	/**
	 * 
	 * @return the ball if a white ball to ball collision else nul
	 */
	private IPoolBall getBallFromWhiteCollision(Body body1, Body body2) {
		if (body1.getUserData() instanceof IPoolBall
				&& body2.getUserData() instanceof IPoolBall) {
			IPoolBall ball1 = (IPoolBall) body1.getUserData();
			IPoolBall ball2 = (IPoolBall) body2.getUserData();

			if (ball1.getTeamColour().equals(Color.WHITE))
				return ball2;
			if (ball2.getTeamColour().equals(Color.WHITE))
				return ball1;

		}
		return null;
	}

	private void checkPocketedAndRemoveBall(Body body1, Body body2) {
		if (body1.getUserData() instanceof Pocket) {
			Environment.bodiesToRemove.add(body2);
		} else if (body2.getUserData() instanceof Pocket) {
			Environment.bodiesToRemove.add(body1);
		}
	}

	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

}
