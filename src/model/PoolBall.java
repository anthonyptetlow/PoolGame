package model;

import model.api.IPoolBall;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class PoolBall implements IPoolBall {
	// JavaFX UI for ball
	public Body node;

	// Ball radius in pixels
	private float radius;

	public PoolBall(float posX, float posY, Environment e) {
		// TODO Fix Dimentions
		this.radius = 5.0f;
		node = create(posX, posY, e);
	}

	/* (non-Javadoc)
	 * @see model.IPoolBall#getPosX()
	 */
	@Override
	public float getPosX() {
		return node.getPosition().x;
	}

	/* (non-Javadoc)
	 * @see model.IPoolBall#getPosY()
	 */
	@Override
	public float getPosY() {
		return node.getPosition().y;
	}

	/* (non-Javadoc)
	 * @see model.IPoolBall#getRadius()
	 */
	@Override
	public float getRadius() {
		return radius;
	}

	/**
	 * This method creates a ball by using Circle object from JavaFX and
	 * CircleShape from JBox2D
	 * 
	 * @param e
	 */
	// TODO Rebuild this to be tidy
	private Body create(float posX, float posY, Environment e) {
		// Create an JBox2D body defination for ball.
		BodyDef bd = new BodyDef();
		bd.type = BodyType.DYNAMIC;
		bd.position.set(posX, posY);

		CircleShape cs = new CircleShape();
		cs.m_radius = radius * 1.0f; // We need to convert radius to JBox2D
										// equivalent
		// Create a fixture for ball
		FixtureDef fd = new FixtureDef();
		fd.shape = cs;
		fd.density = 1.0f;
		fd.friction = 0.1f;
		fd.restitution = 1.0f;

		/**
		 * position. Forces, torques, and impulses can be applied to these
		 * Virtual invisible JBox2D body of ball. Bodies have velocity and
		 * bodies.
		 */
		Body body = e.world.createBody(bd);
		body.createFixture(fd);
		// TODO Remove this velocity
		body.setLinearVelocity(new Vec2(50.0f, 50.0f));
		body.setLinearDamping(0.05f);
		return body;
	}

}
