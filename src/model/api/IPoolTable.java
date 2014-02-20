package model.api;

import java.util.List;

import model.RectCushion;

public interface IPoolTable {

	public List<IPoolBall> getBalls();

	public List<RectCushion> getCushions();

	public List<IPocket> getPockets();

}