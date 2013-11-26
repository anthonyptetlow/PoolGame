package pool.model;

import java.awt.Color;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class Ball {
	// JavaFX UI for ball
	public Body node;

	// Ball radius in pixels
	private int radius;

	private Color color;

	public Ball(float posX, float posY, int radius, Color color, Environment e) {
		this.radius = radius;
		this.color = color;
		node = create(posX, posY, e);
	}

	public float getPosX() {
		return node.getPosition().x;
	}

	public float getPosY() {
		return node.getPosition().y;
	}

	public int getRadius() {
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
		cs.m_radius = radius * 0.1f; // We need to convert radius to JBox2D
										// equivalent
		// Create a fixture for ball
		FixtureDef fd = new FixtureDef();
		fd.shape = cs;
		fd.density = 0.6f;
		fd.friction = 0.0f;
		// fd.restitution = 0.8f;

		/**
		 * position. Forces, torques, and impulses can be applied to these
		 * Virtual invisible JBox2D body of ball. Bodies have velocity and
		 * bodies.
		 */
		Body body = e.world.createBody(bd);
		body.createFixture(fd);
		// TODO Remove this velocity
		body.setLinearVelocity(new Vec2(0.0f, -4.0f));
		return body;
	}

}
