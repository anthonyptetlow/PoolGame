package run;

import model.PoolModel;

import org.jbox2d.common.Vec2;

public class Simulation {

	private static final float timeStep = 1.0f / 60.0f;

	private static final int velocityIterations = 6;

	private static final int positionIterations = 2;

	private static PoolModel model;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		model = new PoolModel();

		for (int i = 0; i < 60; ++i)

		{

			model.world.step(timeStep, velocityIterations, positionIterations);

			Vec2 position = model.dbBody.getPosition();

			float angle = model.dbBody.getAngle();

			System.out.println(position.x + " " + position.y + " " + angle);

		}

	}
}
