package ai.imp;

import java.util.Map;
import java.util.Set;

import model.api.IPlayer;
import model.api.IPoolGame;

import org.jbox2d.common.Vec2;

import ai.IAIEngine;
import ai.IShotEvaluator;
import ai.IShotGenerator;
import ai.IShotSelector;

public class TopAIEngine implements IAIEngine {

	private IShotGenerator shotGen;
	private IShotEvaluator shotEval;
	private IShotSelector shotSel;

	public TopAIEngine() {
		shotGen = new ReverseShotGenerator();
		shotEval = new BasicShotEvaluator();
		shotSel = new MaxShotSelector();
	}

	public Vec2 getNextShot(IPoolGame game, IPlayer player) {
		Set<Vec2> possibleShots = shotGen.getShots(game);
		Map<Vec2, Integer> shotsAndScores = shotEval
				.evaluateShots(possibleShots);
		return shotSel.selectShot(shotsAndScores);
	}

}
