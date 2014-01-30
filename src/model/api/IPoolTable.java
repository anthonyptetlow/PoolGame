package model.api;

import java.util.List;

import model.Pocket;
import model.PoolBall;
import model.RectCushion;

public interface IPoolTable {

	public List<PoolBall> getBalls();

	public List<RectCushion> getCushions();

	public List<Pocket> getPockets();

}