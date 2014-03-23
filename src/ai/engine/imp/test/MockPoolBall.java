package ai.engine.imp.test;

import java.awt.Color;

import model.api.IPoolBall;

import org.jbox2d.dynamics.Body;

public class MockPoolBall implements IPoolBall {

	private final float radius = 0.027f;
	private final float x, y;

	public MockPoolBall(float posX, float posY) {
		x = posX;
		y = posY;
	}

	@Override
	public float getPosX() {
		return x;
	}

	@Override
	public float getPosY() {
		return y;
	}

	@Override
	public float getRadius() {
		return radius;
	}

	@Override
	public Color getTeamColour() {
		return null;
	}

	@Override
	public Body getNode() {
		return null;
	}

}
