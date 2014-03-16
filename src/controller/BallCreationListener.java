package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Environment;
import model.api.IPoolTable;

import org.jbox2d.common.Vec2;

import Util.Properties;

public class BallCreationListener implements MouseListener {

	private IPoolTable model;
	private boolean takeShot = true;

	// TODO Move to shared properties class

	public BallCreationListener(IPoolTable model) {
		this.model = model;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (model.getWhiteBall() == null) {

			float xCorrected = screenPressToModelpoint(e.getPoint().x);
			float yCorrected = screenPressToModelpoint(e.getPoint().y);
			if (xCorrected < 0.675f) {
				model.addWhiteBall(xCorrected, yCorrected);
			}

			takeShot = false;
		} else {
			takeShot = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (Environment.isStationary())
			if (takeShot) {
				float xCorrected = screenPressToModelpoint(e.getPoint().x);
				float yCorrected = screenPressToModelpoint(e.getPoint().y);

				float x = (model.getWhiteBall().getPosX() - xCorrected);
				float y = (model.getWhiteBall().getPosY() - yCorrected);

				model.getWhiteBall().getNode()
						.setLinearVelocity(new Vec2(x, y));
			}

	}

	private float screenPressToModelpoint(int point) {
		return (point / Properties.MODEL_TO_GRAPHIC) - Properties.VISUAL_OFFSET;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
