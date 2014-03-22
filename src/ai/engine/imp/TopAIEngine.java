package ai.engine.imp;

import java.util.Map;
import java.util.Set;

import model.api.IPoolGame;

import org.jbox2d.common.Vec2;

import ai.engine.IShotEvaluator;
import ai.engine.IShotGenerator;
import ai.engine.IShotSelector;

public class TopAIEngine {

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

	public Vec2 getNextShot(IPoolGame game) {
		Set<Vec2> possibleShots = shotGen.getShots(game);
		Map<Vec2, Integer> shotsAndScores = shotEval
				.evaluateShots(possibleShots);
		return shotSel.selectShot(shotsAndScores);
	}

}
