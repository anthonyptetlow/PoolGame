package model;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

public class Environment {

	public final World world = new World(new Vec2(0, 0.0f));

	private final float timeStep = 1.0f / 60.0f;

	private final int velocityIterations = 6;

	private final int positionIterations = 2;

	public void step() {
		world.step(timeStep, velocityIterations, positionIterations);
	}

}
