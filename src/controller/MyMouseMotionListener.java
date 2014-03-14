package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import view.PoolTablePanel;

public class MyMouseMotionListener implements MouseMotionListener {

	private PoolTablePanel panel;

	public MyMouseMotionListener(PoolTablePanel panel) {
		this.panel = panel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		panel.setMousePosition(e.getPoint());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		panel.setMousePosition(e.getPoint());
	}
}
