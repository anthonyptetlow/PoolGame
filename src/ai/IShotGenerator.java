package ai;

import java.util.Set;

import model.api.IPoolGame;

import org.jbox2d.common.Vec2;

public interface IShotGenerator {

	public Set<Vec2> getShots(IPoolGame game);

}