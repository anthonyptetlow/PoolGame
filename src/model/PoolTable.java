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
		float pocketRadius = 0.0375f;
		float pocketD = pocketRadius * 2.0f;
		float cussionLength = 1.275f;
		float offset = 0.01f;
		float doubleOffset = offset * 2.0f;
		// Top 1
		cushions.add(new RectCushion((cussionLength / 2.0f) + pocketRadius,
				-offset, cussionLength, doubleOffset, environment));
		// Top 2
		cushions.add(new RectCushion(cussionLength + pocketD + pocketRadius
				+ (1.2750f / 2.0f), -offset, cussionLength, doubleOffset,
				environment));
		// Left
		cushions.add(new RectCushion(-offset, (cussionLength / 2.0f)
				+ pocketRadius, 0.02f, cussionLength, environment));
		// right
		cushions.add(new RectCushion(2.700f + offset, (cussionLength / 2.0f)
				+ pocketRadius, 0.02f, cussionLength, environment));
		// Bottom 1
		cushions.add(new RectCushion((cussionLength / 2.0f) + pocketRadius,
				cussionLength + pocketD + offset, cussionLength, doubleOffset,
				environment));
		// Bottom 2
		cushions.add(new RectCushion(cussionLength + pocketD + pocketRadius
				+ (1.2750f / 2.0f), cussionLength + pocketD + offset,
				cussionLength, doubleOffset, environment));

		pockets.add(new Pocket(0, 0, environment));
		pockets.add(new Pocket(cussionLength + pocketD, -0.05f, 0.1f,
				environment));

		pockets.add(new Pocket(0.0f, cussionLength + pocketD, environment));
		pockets.add(new Pocket(cussionLength + pocketD, cussionLength + pocketD
				+ 0.05f, 0.1f, environment));

		pockets.add(new Pocket((cussionLength + pocketD) * 2.0f, 0, environment));
		pockets.add(new Pocket((cussionLength + pocketD) * 2.0f, cussionLength
				+ pocketD, environment));

		rackBalls();

	}

	private void rackBalls() {
		float startX = 2.025f;
		float startY = 0.675f;

		float ballDiameter = 0.054f;
		float ballRadius = ballDiameter / 2.0f;

		balls.add(new PoolBall(startX, startY, Color.RED, environment));

		balls.add(new PoolBall(startX + ballDiameter, startY - ballRadius,
				Color.YELLOW, environment));

		balls.add(new PoolBall(startX + ballDiameter, startY + ballRadius,
				Color.YELLOW, environment));

		balls.add(new PoolBall(startX + (2 * ballDiameter), startY
				- (2 * ballRadius), Color.RED, environment));
		balls.add(new PoolBall(startX + (2 * ballDiameter), startY,
				Color.BLACK, environment));
		balls.add(new PoolBall(startX + (2 * ballDiameter), startY
				+ (2 * ballRadius), Color.RED, environment));

		balls.add(new PoolBall(startX + (3 * ballDiameter), startY
				- (3 * ballRadius), Color.YELLOW, environment));
		balls.add(new PoolBall(startX + (3 * ballDiameter), startY
				- (1 * ballRadius), Color.YELLOW, environment));
		balls.add(new PoolBall(startX + (3 * ballDiameter), startY
				+ (1 * ballRadius), Color.RED, environment));
		balls.add(new PoolBall(startX + (3 * ballDiameter), startY
				+ (3 * ballRadius), Color.YELLOW, environment));

		balls.add(new PoolBall(startX + (4 * ballDiameter), startY
				- (4 * ballRadius), Color.RED, environment));

		balls.add(new PoolBall(startX + (4 * ballDiameter), startY
				- (2 * ballRadius), Color.YELLOW, environment));
		balls.add(new PoolBall(startX + (4 * ballDiameter), startY, Color.RED,
				environment));
		balls.add(new PoolBall(startX + (4 * ballDiameter), startY
				+ (2 * ballRadius), Color.YELLOW, environment));

		balls.add(new PoolBall(startX + (4 * ballDiameter), startY
				+ (4 * ballRadius), Color.RED, environment));

	}

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
