package model.api;

import java.awt.Color;

import org.jbox2d.dynamics.Body;

public interface IPoolBall {

	float getPosX();

	float getPosY();

	float getRadius();

	Color getTeamColour();

	Body getNode();
}