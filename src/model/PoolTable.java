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
	private List<IPoolBall> pocketedBalls;
	private IPoolBall white;

	public PoolTable(Environment environment) {
		balls = new ArrayList<IPoolBall>();
		cushions = new ArrayList<RectCushion>();
		pockets = new ArrayList<IPocket>();
		pocketedBalls = new ArrayList<IPoolBall>();
		this.environment = environment;
		setupTable();
	}

	public PoolBall createBall(float x, float y, Color color) {

		PoolBall ball = new PoolBall(x, y, color, environment);
		balls.add(ball);
		return ball;

	}

	public void setupTable() {
		// TODO Clean the mathematics up here
		// Top 1
		cushions.add(new RectCushion((1.275f / 2.0f) + 0.0375f, -0.010f,
				1.275f, 0.020f, environment));
		// Top 2
		cushions.add(new RectCushion(
				1.275f + 0.075f + 0.0375f + (1.2750f / 2.0f), -0.010f, 1.275f,
				0.020f, environment));
		// Left
		cushions.add(new RectCushion(-0.01f, (1.275f / 2.0f) + 0.0375f, 0.02f,
				1.275f, environment));
		// right
		cushions.add(new RectCushion(2.700f + 0.01f, (1.275f / 2.0f) + 0.0375f,
				0.02f, 1.275f, environment));
		// Bottom 1
		cushions.add(new RectCushion((1.275f / 2.0f) + 0.0375f,
				1.275f + 0.075f + 0.010f, 1.275f, 0.020f, environment));
		// Bottom 2
		cushions.add(new RectCushion(
				1.275f + 0.075f + 0.0375f + (1.2750f / 2.0f),
				1.275f + 0.075f + 0.01f, 1.275f, 0.020f, environment));

		pockets.add(new Pocket(0, 0, environment));
		pockets.add(new Pocket(1.275f + 0.075f, -0.05f, 0.1f, environment));

		pockets.add(new Pocket(0.0f, 1.275f + 0.075f, environment));
		pockets.add(new Pocket(1.275f + 0.075f, 1.275f + 0.075f + 0.05f, 0.1f,
				environment));

		pockets.add(new Pocket((1.275f + 0.075f) * 2.0f, 0, environment));
		pockets.add(new Pocket((1.275f + 0.075f) * 2.0f, 1.275f + 0.075f,
				environment));

		rackBalls();

	}

	private void rackBalls() {
		float startX = 2.025f;
		float startY = 0.675f;
		balls.add(new PoolBall(startX, startY, Color.RED, environment));

		balls.add(new PoolBall(startX + 0.054f, startY - 0.027f, Color.YELLOW,
				environment));

		balls.add(new PoolBall(startX + 0.054f, startY + 0.027f, Color.YELLOW,
				environment));

		balls.add(new PoolBall(startX + (2 * 0.054f), startY - (2 * 0.027f),
				Color.RED, environment));
		balls.add(new PoolBall(startX + (2 * 0.054f), startY, Color.BLACK,
				environment));
		balls.add(new PoolBall(startX + (2 * 0.054f), startY + (2 * 0.027f),
				Color.RED, environment));

		balls.add(new PoolBall(startX + (3 * 0.054f), startY - (3 * 0.027f),
				Color.YELLOW, environment));
		balls.add(new PoolBall(startX + (3 * 0.054f), startY - (1 * 0.027f),
				Color.YELLOW, environment));
		balls.add(new PoolBall(startX + (3 * 0.054f), startY + (1 * 0.027f),
				Color.RED, environment));
		balls.add(new PoolBall(startX + (3 * 0.054f), startY + (3 * 0.027f),
				Color.YELLOW, environment));

		balls.add(new PoolBall(startX + (4 * 0.054f), startY - (4 * 0.027f),
				Color.RED, environment));

		balls.add(new PoolBall(startX + (4 * 0.054f), startY - (2 * 0.027f),
				Color.YELLOW, environment));
		balls.add(new PoolBall(startX + (4 * 0.054f), startY, Color.RED,
				environment));
		balls.add(new PoolBall(startX + (4 * 0.054f), startY + (2 * 0.027f),
				Color.YELLOW, environment));

		balls.add(new PoolBall(startX + (4 * 0.054f), startY + (4 * 0.027f),
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

	@Override
	public boolean addWhiteBall(float x, float y) {
		if (white == null) {
			white = new PoolBall(x, y, Color.WHITE, environment);
			return true;
		}
		return false;
	}

	@Override
	public IPoolBall getWhiteBall() {
		return white;
	}

	public void removeWhite() {
		white = null;
	}

	@Override
	public void pocket(IPoolBall ball) {
		pocketedBalls.add(ball);
	}
}
