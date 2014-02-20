package model.api;

import java.util.List;
import java.util.Observer;

import model.RectCushion;

public interface IPoolTable {

	public List<IPoolBall> getBalls();

	public List<RectCushion> getCushions();

	public List<IPocket> getPockets();

	void addObserver(Observer o);

}