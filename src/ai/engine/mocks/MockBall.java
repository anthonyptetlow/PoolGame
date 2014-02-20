package ai.engine.mocks;

import java.awt.Color;

import model.api.IPoolBall;

public class MockBall implements IPoolBall {

	private float x;
	private float y;
	private float r;
	private Color teamColour;

	public MockBall(float x, float y, float r) {
		this.x = x;
		this.y = y;
		this.r = r;
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
		return r;

	}

	@Override
	public Color getTeamColour() {
		return teamColour;
	}

}
