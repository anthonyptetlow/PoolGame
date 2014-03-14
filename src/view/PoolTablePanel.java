package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Environment;
import model.RectCushion;
import model.api.IPocket;
import model.api.IPoolBall;
import model.api.IPoolTable;

public class PoolTablePanel extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -949315898738707714L;
	private IPoolTable model;
	private double scale = 500f;
	private double offset = 0.10f;
	private Point mousePosition;
	private double dirScale = 2.0f;

	public PoolTablePanel(IPoolTable model) {
		this.model = model;
		this.model.addObserver(this);
	}

	@Override
	public int getWidth() {
		return Math.max(super.getWidth(), 1450);
	}

	@Override
	public int getHeight() {
		return Math.max(super.getHeight(), 900);
	}

	public void setMousePosition(Point mousePosition) {
		this.mousePosition = mousePosition;
	}

	@Override
	public void paint(Graphics g) {
		Image bufferImage = createImage(getWidth(), getHeight());
		// double scale = Math.max((model.getWidth() / getWidth()),
		// (model.getHeight() / getHeight()));
		Graphics buffer = bufferImage.getGraphics();
		Graphics2D g2d = (Graphics2D) buffer;
		// Draw Pool Table
		// TODO Clean up the drawing of objects
		// Draw Balls

		for (IPoolBall ball : model.getBalls()) {
			g2d.setColor(ball.getTeamColour());
			drawCircle(g2d, ball);
		}

		IPoolBall ball = model.getWhiteBall();
		g2d.setColor(Color.WHITE);
		if (ball != null) {
			drawCircle(g2d, ball);

			if (mousePosition != null && Environment.isStationary()) {

				g2d.setColor(Color.BLACK);

				double x1 = (ball.getPosX() + offset) * scale;
				double y1 = (ball.getPosY() + offset) * scale;

				dirScale = Math.abs(1000.0f / (x1 - mousePosition.x));
				double x2 = x1 + ((x1 - mousePosition.x) * dirScale);
				double y2 = y1 + ((y1 - mousePosition.y) * dirScale);

				g2d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
			}
		}
		g2d.setColor(Color.BLACK);
		for (IPocket pocket : model.getPockets()) {
			g2d.fillOval(
					(int) ((pocket.getPosX() - pocket.getRadius() + offset) * scale),
					(int) ((pocket.getPosY() - pocket.getRadius() + offset) * scale),
					(int) ((pocket.getRadius() * 2.0f) * scale),
					(int) ((pocket.getRadius() * 2.0f) * scale));
			g2d.drawOval(
					(int) ((pocket.getPosX() - pocket.getRadius() + offset) * scale),
					(int) ((pocket.getPosY() - pocket.getRadius() + offset) * scale),
					(int) ((pocket.getRadius() * 2.0f) * scale),
					(int) ((pocket.getRadius() * 2.0f) * scale));
		}
		g2d.setColor(Color.GREEN);

		for (RectCushion cushion : model.getCushions()) {
			float width = cushion.getWidth();
			float height = cushion.getHeight();
			g2d.drawRect(
					(int) ((cushion.getPosX() - (0.5 * width) + offset) * scale),
					(int) ((cushion.getPosY() - (0.5 * height) + offset) * scale),
					(int) (width * scale), (int) (height * scale));
			g2d.fillRect(
					(int) ((cushion.getPosX() - (0.5 * width) + offset) * scale),
					(int) ((cushion.getPosY() - (0.5 * height) + offset) * scale),
					(int) (width * scale), (int) (height * scale));

		}

		g.drawImage(bufferImage, 0, 0, null);
	}

	private void drawCircle(Graphics2D g2d, IPoolBall ball) {
		g2d.fillOval(
				(int) ((ball.getPosX() - ball.getRadius() + offset) * scale),
				(int) ((ball.getPosY() - ball.getRadius() + offset) * scale),
				(int) ((ball.getRadius() * 2.0f) * scale),
				(int) ((ball.getRadius() * 2.0f) * scale));
		g2d.drawOval(
				(int) ((ball.getPosX() - ball.getRadius() + offset) * scale),
				(int) ((ball.getPosY() - ball.getRadius() + offset) * scale),
				(int) ((ball.getRadius() * 2.0f) * scale),
				(int) ((ball.getRadius() * 2.0f) * scale));
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}