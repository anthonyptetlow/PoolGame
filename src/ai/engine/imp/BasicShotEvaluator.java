package ai.engine.imp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jbox2d.common.Vec2;

import ai.engine.IShotEvaluator;

public class BasicShotEvaluator implements IShotEvaluator {

	@Override
	public Map<Vec2, Integer> evaluateShots(Set<Vec2> possibleShots) {
		// TODO Evaluate Shots
		Map<Vec2, Integer> evaluations = new HashMap<Vec2, Integer>();
		for (Vec2 vec2 : possibleShots) {
			// Use SHeet C to calculate leniency and use this to produce a
			// value
			evaluations.put(vec2, 1);
		}
		return evaluations;

	}

}
