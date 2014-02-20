package controller;

import model.Environment;
import model.PoolBall;
import model.api.IPoolTable;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;

public class BallPocketContactListener implements ContactListener {

	private IPoolTable poolTable;

	public BallPocketContactListener(IPoolTable poolTable) {
		this.poolTable = poolTable;
	}

	@Override
	public void beginContact(Contact contact) {
		Body b = contact.getFixtureA().getBody();
		if (b.getUserData() instanceof PoolBall) {
			Environment.bodiesToRemove.add(b);
		}
		// b = contact.getFixtureB().getBody();
		// if (b.getUserData()instanceof PoolBall)
		// Environment.world.destroyBody(null);
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub

	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}

}
