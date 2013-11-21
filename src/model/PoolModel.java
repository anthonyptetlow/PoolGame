package model;

import java.util.Observable;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class PoolModel extends Observable {

	public final World world = new World(new Vec2(0, 0.0f));

	public Body dbBody;

	private final float timeStep = 1.0f / 60.0f;

	private final int velocityIterations = 6;

	private final int positionIterations = 2;

	public PoolModel() {

		createGroundBox();
		createdynamicBox();
	}

	public void createGroundBox() {
		PolygonShape groundBox;
		groundBox = new PolygonShape();
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(0.0f, -10.0f);

		Body groundBody = world.createBody(groundBodyDef);

		groundBox.setAsBox(50.0f, 10.0f);

		groundBody.createFixture(groundBox, 0.0f);
	}

	public void createdynamicBox() {

		BodyDef bodyDef = new BodyDef();

		bodyDef.type = BodyType.DYNAMIC;

		bodyDef.position.set(0.0f, 4.0f);

		dbBody = world.createBody(bodyDef);

		PolygonShape dynamicBox = new PolygonShape();

		dynamicBox.setAsBox(1.0f, 1.0f);

		FixtureDef fixtureDef = new FixtureDef();

		fixtureDef.shape = dynamicBox;

		fixtureDef.density = 1.0f;

		fixtureDef.friction = 0.3f;

		dbBody.createFixture(fixtureDef);
		dbBody.setLinearVelocity(new Vec2(0.0f, -4.0f));
	}

	public void step() {
		world.step(timeStep, velocityIterations, positionIterations);
		setChanged();
		notifyObservers();
	}

}
