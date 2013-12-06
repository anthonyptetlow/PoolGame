package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.PoolTable;

public class BallCreationListener implements MouseListener {

	private PoolTable model;

	public BallCreationListener(PoolTable model) {
		this.model = model;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		model.createBall(30.0f, 30.0f);
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
