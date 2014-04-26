package ai.engine;

import model.api.IPlayer;
import model.api.IPoolGame;

import org.jbox2d.common.Vec2;

public interface IAIEngine {
	Vec2 getNextShot(IPoolGame game, IPlayer player);
}
