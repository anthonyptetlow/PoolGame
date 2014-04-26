package ai.engine.imp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import model.PoolGame;
import model.api.IPoolBall;
import model.api.IPoolGame;

import org.jbox2d.common.Vec2;
import org.junit.Test;

import ai.engine.imp.ReverseShotGenerator;

public class ReverseShotGeneratorTest {

	@Test
	public void getShotsNonAvailable() {
		// No Shots to Pocket
		IPoolGame game = new PoolGame();
		game.setPlayerColors(Color.RED);
		ReverseShotGenerator shotGen = new ReverseShotGenerator();
		Set<Vec2> shots = shotGen.getShots(game);
		assertEquals(shots.size(), 0);
	}

	@Test
	public void testGetShotIfPossible() {
		ReverseShotGenerator shotGen = new ReverseShotGenerator();
		// Straight Shot
		Vec2 shot = shotGen.getShotIfPossible(new MockPocket(1.0f, 2.0f),
				new MockPoolBall(2.0f, 1.0f), new MockPoolBall(3.0f, 0.0f),
				new ArrayList<IPoolBall>());
		System.out.println(shot);
		assertTrue(shot != null);
		//
		// shot = shotGen.getShotIfPossible(new MockPocket(1.0f, 2.0f),
		// new MockPoolBall(2.0f, 1.0f), new MockPoolBall(2.0f, 0.0f),
		// new ArrayList<IPoolBall>());
		// System.out.println(shot);
		// assertTrue(shot != null);

		// Vec2 shot = shotGen.getShotIfPossible(new MockPocket(1.0f, 2.0f),
		// new MockPoolBall(2.0f, 1.0f), new MockPoolBall(1.0f, 1.0f),
		// new ArrayList<IPoolBall>());
		// System.out.println(shot);
		// assertTrue(shot == null);

		//
		// ArrayList<IPoolBall> balls = new ArrayList<IPoolBall>();
		// balls.add(new MockPoolBall(1.5f, 1.5f));
		// shot = shotGen.getShotIfPossible(new MockPocket(1.0f, 2.0f),
		// new MockPoolBall(2.0f, 1.0f), new MockPoolBall(3.0f, 2.0f),
		// balls);
		// assertTrue(shot == null);
		// balls.clear();
		//
		// balls.add(new MockPoolBall(2.51207475f, 1.51267475f));
		// shot = shotGen.getShotIfPossible(new MockPocket(1.0f, 2.0f),
		// new MockPoolBall(2.0f, 1.0f), new MockPoolBall(3.0f, 2.0f),
		// balls);
		// assertTrue(shot == null);
		// balls.clear();
		// TODO check shots on extreme angles
	}

	@Test
	public void testBallNotOnPath() {
		ReverseShotGenerator shotGen = new ReverseShotGenerator();
		assertTrue("The path is clear", shotGen.ballOnPath(
				new Vec2(1.0f, 3.0f), new Vec2(3.0f, 4.0f), new MockPoolBall(
						3.0f, 3.0f)));

		assertTrue("The path is clear", shotGen.ballOnPath(
				new Vec2(3.0f, 4.0f), new Vec2(1.0f, 3.0f), new MockPoolBall(
						3.0f, 3.0f)));

		// Clear Path
		assertTrue("The path is clear", shotGen.ballOnPath(
				new Vec2(3.0f, 1.0f), new Vec2(1.0f, 3.0f), new MockPoolBall(
						3.0f, 3.0f)));
		// not clear Path
		assertTrue("The path is clear", shotGen.ballOnPath(
				new Vec2(1.0f, 3.0f), new Vec2(3.0f, 4.0f), new MockPoolBall(
						3.0f, 3.0f)));

		// Just inside
		assertFalse("The path isn't clear(Just inside Ball)",
				shotGen.ballOnPath(new Vec2(4.0f, 2.950f), new Vec2(1.0f,
						2.950f), new MockPoolBall(3.0f, 3.0f)));
		// On Line
		assertFalse("The path isn't clear(Just touching Ball)",
				shotGen.ballOnPath(new Vec2(4.0f, 2.946f), new Vec2(1.0f,
						2.946f), new MockPoolBall(3.0f, 3.0f)));
		// just outside
		assertTrue("The path isn't clear(Just outside Ball)",
				shotGen.ballOnPath(new Vec2(4.0f, 2.940f), new Vec2(1.0f,
						2.940f), new MockPoolBall(3.0f, 3.0f)));

		// On an angle
		// Just inside
		assertFalse("The path isn't clear(Just inside Ball angled)",
				shotGen.ballOnPath(new Vec2(2.985f, 2.985f), new Vec2(3.0f,
						2.0f), new MockPoolBall(3.0f, 3.0f)));
		// On Line
		assertFalse("The path isn't clear(Just inside Ball angled)",
				shotGen.ballOnPath(new Vec2(2.980f, 2.980f), new Vec2(3.0f,
						2.0f), new MockPoolBall(3.0f, 3.0f)));
		// just outside
		assertFalse("The path isn't clear(Just inside Ball angled)",
				shotGen.ballOnPath(new Vec2(2.975f, 2.975f), new Vec2(3.0f,
						2.0f), new MockPoolBall(3.0f, 3.0f)));
		// just outside

	}

	@Test
	public void testPathBetweenPointsClear() {
		ReverseShotGenerator shotGen = new ReverseShotGenerator();

		List<IPoolBall> balls = new ArrayList<IPoolBall>();
		balls.add(new MockPoolBall(2.0f, 2.0f));
		balls.add(new MockPoolBall(3.0f, 1.0f));
		balls.add(new MockPoolBall(4.0f, 0.946f));
		assertFalse("Balls On Path", shotGen.pathBetweenPointsClear(new Vec2(
				1.0f, 1.0f), new Vec2(5.0f, 1.0f), balls));
		assertTrue("No Balls On Path", shotGen.pathBetweenPointsClear(new Vec2(
				0.0f, 0.0f), new Vec2(5.0f, 0.0f), balls));

		assertFalse("Balls On Path", shotGen.pathBetweenPointsClear(new Vec2(
				5.0f, 1.0f), new Vec2(1.0f, 1.0f), balls));

		assertTrue("No Balls On Path", shotGen.pathBetweenPointsClear(new Vec2(
				5.0f, 0.0f), new Vec2(0.0f, 0.0f), balls));
	}

}