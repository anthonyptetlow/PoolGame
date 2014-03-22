package ai.engine.imp;

import java.util.Map;
import java.util.Set;

import org.jbox2d.common.Vec2;

import ai.engine.IShotEvaluator;

public class BasicShotEvaluator implements IShotEvaluator {

	@Override
	public Map<Vec2, Integer> evaluateShots(Set<Vec2> possibleShots) {
		// TODO Evaluate Shots
		// Use SHeet C to calculate leaniency and use this to produce a value
		return null;
	}

}
