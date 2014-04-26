package ai.engine;

import java.util.Map;
import java.util.Set;

import org.jbox2d.common.Vec2;

public interface IShotEvaluator {

	public Map<Vec2, Integer> evaluateShots(Set<Vec2> possibleShots);

}