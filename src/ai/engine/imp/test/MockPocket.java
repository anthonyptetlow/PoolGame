package ai.engine.imp.test;

import model.api.IPocket;

public class MockPocket implements IPocket {

	private final float radius = 0.075f / 2.0f;
	private final float x, y;

	public MockPocket(float posX, float posY) {
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

}
