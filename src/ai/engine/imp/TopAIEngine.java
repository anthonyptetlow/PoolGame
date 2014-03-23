package ai.engine.imp;

import java.util.Map;
import java.util.Set;

import model.api.IPlayer;
import model.api.IPoolGame;

import org.jbox2d.common.Vec2;

import ai.engine.IAIEngine;
import ai.engine.IShotEvaluator;
import ai.engine.IShotGenerator;
import ai.engine.IShotSelector;

public class TopAIEngine implements IAIEngine {

	private IShotGenerator shotGen;
	private IShotEvaluator shotEval;
	private IShotSelector shotSel;

	// private UserModel userModel;
	// private StateSearcher stateSearch;

	public TopAIEngine() {
		shotEval = new BasicShotEvaluator();
		shotGen = new ReverseShotGenerator();
		shotSel = new MaxShotSelector();
	}

	public Vec2 getNextShot(IPoolGame game, IPlayer player) {
		Set<Vec2> possibleShots = shotGen.getShots(game);
		Map<Vec2, Integer> shotsAndScores = shotEval
				.evaluateShots(possibleShots);
		return shotSel.selectShot(shotsAndScores);
	}

}
