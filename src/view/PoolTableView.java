package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.RectCushion;
import model.api.IPocket;
import model.api.IPoolBall;
import model.api.IPoolTable;

public class PoolTableView extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -949315898738707714L;
	private IPoolTable model;
	private double scale = 500f;
	private double offset = 0.10f;

	public PoolTableView(IPoolTable model) {
		this.model = model;
		this.model.addObserver(this);
	}

	@Override
	public void paint(Graphics g) {
		Image bufferImage = createImage(getWidth(), getHeight());
		// double scale = Math.max((model.getWidth() / getWidth()),
		// (model.getHeight() / getHeight()));
		Graphics buffer = bufferImage.getGraphics();
		Graphics2D g2d = (Graphics2D) buffer;
		// Draw Pool Table

		// Draw Balls

		for (IPoolBall ball : model.getBalls()) {
			g2d.setColor(ball.getTeamColour());
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

		IPoolBall ball = model.getWhiteBall();
		if (ball != null) {
			g2d.setColor(ball.getTeamColour());
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

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}