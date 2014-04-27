package ai;

import java.util.Map;

import org.jbox2d.common.Vec2;

public interface IShotSelector {

	Vec2 selectShot(Map<Vec2, Integer> shotsAndScores);

}