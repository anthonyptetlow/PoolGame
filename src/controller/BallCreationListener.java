package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Environment;
import model.api.IPoolTable;

import org.jbox2d.common.Vec2;

public class BallCreationListener implements MouseListener {

	private IPoolTable model;
	private Point pressedPoint = null;
	private float scale = 0.005f;

	public BallCreationListener(IPoolTable model) {
		this.model = model;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!model.addWhiteBall(0.675f, 0.675f)) {
			pressedPoint = e.getPoint();
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (Environment.isStationary())
			if (pressedPoint != null) {
				float x = (pressedPoint.x - e.getPoint().x) * scale;
				float y = (pressedPoint.y - e.getPoint().y) * scale;

				model.getWhiteBall().getNode()
						.setLinearVelocity(new Vec2(x, y));
			}
		pressedPoint = null;

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
