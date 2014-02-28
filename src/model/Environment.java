package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import model.api.IPoolTable;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import controller.BallPocketContactListener;

public class Environment {

	public static final World world = new World(new Vec2(0, 0.0f));

	public static final float timeStep = 1.0f / 24.0f;

	private final int velocityIterations = 6;

	private final int positionIterations = 2;

	public static List<Body> bodiesToRemove;

	private IPoolTable poolTable;

	public Environment(IPoolTable poolTable) {
		bodiesToRemove = new ArrayList<Body>();
		world.setContactListener(new BallPocketContactListener(poolTable));
		this.poolTable = poolTable;
	}

	public void step() {
		world.step(timeStep, velocityIterations, positionIterations);
		for (Body body : bodiesToRemove) {
			if (((PoolBall) body.getUserData()).getTeamColour().equals(
					Color.white)) {
				System.out.println("Remove White");
				poolTable.removeWhite();
			} else
				poolTable.getBalls().remove(body.getUserData());
			world.destroyBody(body);
		}
		bodiesToRemove.clear();
	}
}
