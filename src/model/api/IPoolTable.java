package model.api;

import java.awt.Color;
import java.util.List;
import java.util.Observer;

import model.PoolBall;
import model.RectCushion;

public interface IPoolTable {

	List<IPoolBall> getBalls();

	List<RectCushion> getCushions();

	List<IPocket> getPockets();

	void addObserver(Observer o);

	PoolBall createBall(float f, float g, Color c);

	boolean addWhiteBall(float x, float y);

	void passTime();

	IPoolBall getWhiteBall();

	void removeWhite();

	void pocket(IPoolBall ball);

}