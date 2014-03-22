package ai.engine.imp;

import java.util.List;
import java.util.Set;

import model.api.IPocket;
import model.api.IPoolBall;
import model.api.IPoolGame;

import org.jbox2d.common.Vec2;

import ai.engine.IShotGenerator;

public class ReverseShotGenerator implements IShotGenerator {

	@Override
	public Set<Vec2> getShots(IPoolGame game) {
		// TODO getPotentialShots
		List<IPoolBall> balls = game.getPoolTable().getBalls();
		List<IPocket> pockets = game.getPoolTable().getPockets();
		IPoolBall white = game.getPoolTable().getWhiteBall();

		//For Each Pocket
			//For Each Ball that is current players
				// check Path between is clear
				//If path clear
					//check path between this ball and white is clear
					//If clear add to set of clear paths maybe new object {Pocket, PoolBall, WhiteBall, ShotVector(only ind direction), Option[power]
		return null;
	}

	private boolean pathBetweenBallsClear(Vec2 ob1, Vec2 ob2) {
		// TODO check Path is clear
		// for each ball
			// caculate math on sheet A
			// if (o- r traveling ball) < ball.r then return false
		
		
		
		return true;
	}

}
