package model;

import java.awt.Color;
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
	private List<IPoolBall> balls;

	public PoolTable() {
		balls = new ArrayList<IPoolBall>();
		cushions = new ArrayList<RectCushion>();
		pockets = new ArrayList<IPocket>();
		environment = new Environment(this);
		setupTable();
	}

	public PoolBall createBall(float x, float y, Color color) {

		PoolBall ball = new PoolBall(x, y, color, environment);
		balls.add(ball);
		return ball;

	}

	public void setupTable() {
		// TODO Clean the mathematics up here
		// Build Cussions
		// Top
		cushions.add(new RectCushion((1275 / 2) + 37.5f, -10.0f, 1275, 20,
				environment));
		// Left1
		cushions.add(new RectCushion(-10.0f, (1275 / 2.0f) + 37.5f, 20.0f,
				1275, environment));
		// Left2
		cushions.add(new RectCushion(-10.0f,
				1275 + 75.0f + 37.5f + (1275.0f / 2.0f), 20.0f, 1275.0f,
				environment));
		// Bottom
		cushions.add(new RectCushion((1275 / 2) + 37.5f, 2700.0f + 10.0f, 1275,
				20, environment));

		// Right1
		cushions.add(new RectCushion(1275 + 10.0f + 75.0f,
				(1275 / 2.0f) + 37.5f, 20.0f, 1275, environment));
		// Right2
		cushions.add(new RectCushion(1275 + 10.0f + 75.0f,
				1275 + 75.0f + 37.5f + (1275.0f / 2.0f), 20.0f, 1275.0f,
				environment));
		pockets.add(new Pocket(0, 0, environment));
		pockets.add(new Pocket(1275.0f + 75.0f, 0, environment));
		pockets.add(new Pocket(-10.0f, 1275.0f + 75.0f, environment));
		pockets.add(new Pocket(0.0f, (1275.0f + 75.0f) * 2.0f, environment));
		pockets.add(new Pocket(1275.0f + 75.0f + 10.0f, 1275.0f + 75.0f,
				environment));
		pockets.add(new Pocket(1275.0f + 75.0f, (1275.0f + 75.0f) * 2.0f,
				environment));

		rackBalls();

	}

	private void rackBalls() {
		float startX = 675.0f;
		float startY = 2025.0f;
		balls.add(new PoolBall(startX, startY, Color.RED, environment));

		balls.add(new PoolBall(startX - 27.0f, startY + 54.0f, Color.YELLOW,
				environment));
		balls.add(new PoolBall(startX + 27.0f, startY + 54.0f, Color.YELLOW,
				environment));

		balls.add(new PoolBall(startX - (2 * 27.0f), startY + (2 * 54.0f),
				Color.RED, environment));
		balls.add(new PoolBall(startX, startY + (2 * 54.0f), Color.BLACK,
				environment));
		balls.add(new PoolBall(startX + (2 * 27.0f), startY + (2 * 54.0f),
				Color.RED, environment));

		balls.add(new PoolBall(startX - (3 * 27.0f), startY + (3 * 54.0f),
				Color.YELLOW, environment));
		balls.add(new PoolBall(startX - (1 * 27.0f), startY + (3 * 54.0f),
				Color.YELLOW, environment));
		balls.add(new PoolBall(startX + (1 * 27.0f), startY + (3 * 54.0f),
				Color.RED, environment));
		balls.add(new PoolBall(startX + (3 * 27.0f), startY + (3 * 54.0f),
				Color.YELLOW, environment));

		balls.add(new PoolBall(startX - (4 * 27.0f), startY + (4 * 54.0f),
				Color.RED, environment));

		balls.add(new PoolBall(startX - (2 * 27.0f), startY + (4 * 54.0f),
				Color.YELLOW, environment));
		balls.add(new PoolBall(startX, startY + (4 * 54.0f), Color.RED,
				environment));
		balls.add(new PoolBall(startX + (2 * 27.0f), startY + (4 * 54.0f),
				Color.YELLOW, environment));

		balls.add(new PoolBall(startX + (4 * 27.0f), startY + (4 * 54.0f),
				Color.RED, environment));

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
