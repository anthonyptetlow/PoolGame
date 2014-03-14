package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Environment;
import model.api.IPoolTable;

import org.jbox2d.common.Vec2;

public class BallCreationListener implements MouseListener {

	private IPoolTable model;
	private boolean takeShot = true;
	// TODO Move to shared properties class
	private float scaleVis = 500f;
	private float offset = 0.10f;

	public BallCreationListener(IPoolTable model) {
		this.model = model;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (model.getWhiteBall() == null) {
			model.addWhiteBall(0.675f, 0.675f);
			takeShot = false;
		} else {
			takeShot = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (Environment.isStationary())
			if (takeShot) {
				float xCorrected = ((e.getPoint().x / scaleVis) - offset);
				float yCorrected = ((e.getPoint().y / scaleVis) - offset);

				float x = (model.getWhiteBall().getPosX() - xCorrected);
				float y = (model.getWhiteBall().getPosY() - yCorrected);

				model.getWhiteBall().getNode()
						.setLinearVelocity(new Vec2(x, y));
			}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
