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
	private final float radius;

	/**
	 * 
	 * @param posX
	 *            , posY The center of the pocket
	 * @param width
	 * @param height
	 * @param e
	 */
	public Pocket(float posX, float posY, Environment e) {
		this(posX, posY, 0.075f, e);
	}

	public Pocket(float posX, float posY, float diameter, Environment e) {
		node = create(posX, posY, e);
		radius = diameter / 2.0f;
	}

	private Body create(float posX, float posY, Environment e) {

		BodyDef bd = new BodyDef();
		bd.type = BodyType.STATIC;
		bd.position.set(posX, posY);

		CircleShape cs = new CircleShape();
		cs.m_radius = radius;

		FixtureDef fd = new FixtureDef();
		fd.shape = cs;
		fd.density = 1.7f;
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
