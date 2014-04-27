package ai.imp;

import java.util.Map;

import org.jbox2d.common.Vec2;

import ai.IShotSelector;

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
		if (selectedShot == null
				&& shotsAndScores.keySet().iterator().hasNext()) {
			selectedShot = shotsAndScores.keySet().iterator().next();
		} else {
			// TODO Fix random shot if non available
			selectedShot = new Vec2((float) Math.random(),
					(float) Math.random());
		}
		return selectedShot;
	}
}
