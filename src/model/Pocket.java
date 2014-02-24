package model;

import model.api.IPocket;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

public class Pocket implements IPocket {
	// JavaFX UI for ball
	private Body node;

	// Pocket radius in pixels
	// TODO Check the size of a pocket
	private float radius = 75.0f / 2.0f;

	/**
	 * 
	 * @param posX
	 *            , posY The center of the pocket
	 * @param width
	 * @param height
	 * @param e
	 */
	public Pocket(float posX, float posY, Environment e) {
		node = create(posX, posY, e);
	}

	private Body create(float posX, float posY, Environment e) {

		BodyDef bd = new BodyDef();
		bd.type = BodyType.STATIC;
		bd.position.set(posX, posY);

		// FIXME Rework the shape so colision appears whent he users ball is
		// over half way into the pocket
		CircleShape cs = new CircleShape();
		cs.m_radius = radius * 1.0f;

		FixtureDef fd = new FixtureDef();
		fd.shape = cs;
		fd.density = 1.0f;
		fd.friction = 0.1f;
		fd.restitution = 1.0f;
		Body body = Environment.world.createBody(bd);
		body.createFixture(fd);
		body.setUserData(this);
		return body;
	}

	@Override
	public float getPosX() {
		return node.getPosition().x;

	}

	@Override
	public float getPosY() {
		return node.getPosition().y;
	}

	@Override
	public float getRadius() {
		return radius;
	}

}
