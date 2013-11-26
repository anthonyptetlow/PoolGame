package pool.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;

public class PoolTable extends Observable {

	private Environment environment;
	private List<Pocket> pockets;
	private List<Cushion> cushions;
	private Cue cue;
	private List<Ball> balls;

	public PoolTable() {
		balls = new ArrayList<Ball>();
		cushions = new ArrayList<Cushion>();
		pockets = new ArrayList<Pocket>();
		cue = null;
		environment = new Environment();
	}

	public void createBall() {
		balls.add(new Ball(20.0f, 60.0f, 20, Color.RED, environment));
	}

	// TODO Remove this method and replace with a cushion creation method
	public void createGroundBox() {
		PolygonShape groundBox;
		groundBox = new PolygonShape();
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(0.0f, -10.0f);

		Body groundBody = environment.world.createBody(groundBodyDef);

		groundBox.setAsBox(50.0f, 10.0f);

		groundBody.createFixture(groundBox, 0.0f);
	}

	public void passTime() {
		environment.step();
		setChanged();
		notifyObservers();
	}

	public List<Ball> getBalls() {
		return balls;
	}

}
