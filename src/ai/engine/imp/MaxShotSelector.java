package ai.engine.imp;

import java.util.Map;

import org.jbox2d.common.Vec2;

import ai.engine.IShotSelector;

public class MaxShotSelector implements IShotSelector {

	@Override
	public Vec2 selectShot(Map<Vec2, Integer> shotsAndScores) {
		// TODO Maybe add a simulation to check?? then reset the table
		Vec2 selectedShot = null;
		Integer maxScore = 0;
		for (Vec2 shot : shotsAndScores.keySet()) {
			if (shotsAndScores.get(shot) > maxScore) {
				selectedShot = shot;
				maxScore = shotsAndScores.get(shot);
			}
		}
		if (selectedShot == null) {
			selectedShot = shotsAndScores.keySet().iterator().next();
		}
		return selectedShot;
	}
}
