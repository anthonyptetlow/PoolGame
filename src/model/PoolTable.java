package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import model.api.IPocket;
import model.api.IPoolBall;
import model.api.IPoolTable;

public class PoolTable extends Observable implements IPoolTable {

	private Environment environment;
	private List<IPocket> pockets;
	private List<RectCushion> cushions;
	private Cue cue;
	private List<IPoolBall> balls;

	public PoolTable() {
		balls = new ArrayList<IPoolBall>();
		cushions = new ArrayList<RectCushion>();
		pockets = new ArrayList<IPocket>();
		cue = null;
		environment = new Environment(this);
	}

	public void createBall(float x, float y) {
		balls.add(new PoolBall(x, y, environment));
	}

	public void createPocket(float x, float y) {
		pockets.add(new Pocket(x, y, environment));
	}

	public void createBoarder() {
		float width = 100.0f;
		float height = 20.0f;
		cushions.add(new RectCushion(50.0f, 10.0f, width, height, environment));
		cushions.add(new RectCushion(60.0f, 100.0f, width, height, environment));
		cushions.add(new RectCushion(110.0f, 50.0f, height, width, environment));
		cushions.add(new RectCushion(10.0f, 50.0f, height, width, environment));

	}

	/*
	 * // TODO Remove this method and replace with a cushion creation method
	 * public void createGroundBox() { PolygonShape groundBox = new
	 * PolygonShape(); groundBox.setAsBox(50.0f, 10.0f);
	 * 
	 * BodyDef groundBodyDef = new BodyDef(); groundBodyDef.position.set(0.0f,
	 * -10.0f);
	 * 
	 * Body groundBody = environment.world.createBody(groundBodyDef);
	 * 
	 * groundBody.createFixture(groundBox, 0.0f); }
	 */
	public void passTime() {
		environment.step();
		setChanged();
		notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IPoolTable#getBalls()
	 */
	@Override
	public List<IPoolBall> getBalls() {
		return balls;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IPoolTable#getCushions()
	 */
	@Override
	public List<RectCushion> getCushions() {
		return cushions;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.IPoolTable#getPockets()
	 */
	@Override
	public List<IPocket> getPockets() {
		return pockets;
	}

}
