package ai.engine.mocks;

import java.util.ArrayList;
import java.util.List;

import model.RectCushion;
import model.api.IPocket;
import model.api.IPoolBall;
import model.api.IPoolTable;

public class MockTable implements IPoolTable {

	private List<IPoolBall> balls;
	public List<IPocket> pockets;

	public MockTable() {
		balls = new ArrayList<IPoolBall>();
		// TODO add some balls
		pockets = new ArrayList<IPocket>();
		// TODO add s6 pockets at dimentions
	}

	@Override
	public List<IPoolBall> getBalls() {

		return balls;
	}

	@Override
	public List<RectCushion> getCushions() {
		return null;
	}

	@Override
	public List<IPocket> getPockets() {
		return pockets;
	}

}
