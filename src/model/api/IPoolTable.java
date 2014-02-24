package model.api;

import java.awt.Color;
import java.util.List;
import java.util.Observer;

import model.RectCushion;

public interface IPoolTable {

	public List<IPoolBall> getBalls();

	public List<RectCushion> getCushions();

	public List<IPocket> getPockets();

	void addObserver(Observer o);

	public void createBall(float f, float g, Color c);

	public void passTime();

}