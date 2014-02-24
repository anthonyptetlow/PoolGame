package model.api;

import java.awt.Color;
import java.util.List;
import java.util.Observer;

import model.PoolBall;
import model.RectCushion;

public interface IPoolTable {

	public List<IPoolBall> getBalls();

	public List<RectCushion> getCushions();

	public List<IPocket> getPockets();

	void addObserver(Observer o);

	public PoolBall createBall(float f, float g, Color c);

	public void passTime();

}