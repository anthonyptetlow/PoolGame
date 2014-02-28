package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.api.IPoolTable;

import org.jbox2d.common.Vec2;

public class BallCreationListener implements MouseListener {

	private IPoolTable model;
	private Point pressedPoint = null;
	private float scale = 0.01f;

	public BallCreationListener(IPoolTable model) {
		this.model = model;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getPoint());
		if (!model.addWhiteBall(0.675f, 0.675f)) {
			pressedPoint = e.getPoint();
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (pressedPoint != null) {
			System.out.println("Pressed:\t" + pressedPoint);
			System.out.println("Released:\t" + e.getPoint());
			float x = (pressedPoint.x - e.getPoint().x) * scale;
			float y = (pressedPoint.y - e.getPoint().y) * scale;

			System.out.println("X:\t" + x);
			System.out.println("Y:\t" + y);
			model.getWhiteBall().getNode().setLinearVelocity(new Vec2(x, y));

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
