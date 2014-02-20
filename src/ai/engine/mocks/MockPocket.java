package ai.engine.mocks;

import model.api.IPocket;

public class MockPocket implements IPocket {

	private float x;
	private float y;
	private float r;

	public MockPocket(float x, float y, float r) {
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
}
