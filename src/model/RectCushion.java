package model;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class RectCushion {

	private Body node;

	private float width, height;

	/**
	 * 
	 * @param posX
	 *            , posY The center of the rectangle
	 * @param width
	 * @param height
	 * @param e
	 */
	public RectCushion(float posX, float posY, float width, float height,
			Environment e) {
		this.width = width;
		this.height = height;
		node = create(posX, posY, e);
	}

	public float getPosX() {
		return node.getPosition().x;
	}

	public float getPosY() {
		return node.getPosition().y;
	}

	public void name() {
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
		bd.type = BodyType.STATIC;
		bd.position.set(posX, posY);

		PolygonShape ps = new PolygonShape();
		// FIXME Work out exact dimensions
		ps.setAsBox(0.5f * width, 0.5f * height);

		// equivalent
		// Create a fixture for ball
		FixtureDef fd = new FixtureDef();
		fd.shape = ps;
		fd.density = 0.5f;
		fd.friction = 0.1f;
		fd.restitution = 1f;

		Body body = e.world.createBody(bd);
		body.createFixture(fd);
		return body;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

}
