package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.api.IPoolTable;

import org.jbox2d.common.Vec2;

public class BallCreationListener implements MouseListener {

	private IPoolTable model;
	private Float f;

	public BallCreationListener(IPoolTable model) {
		this.model = model;
		f = 0.0f;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		model.createBall(0.675f, 0.675f, Color.WHITE).node
				.setLinearVelocity(new Vec2(-0.05f, 0.0f));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
