package ai.engine.imp.test;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.Set;

import model.PoolGame;
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

}