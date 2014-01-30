package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.PoolTable;
import model.RectCushion;
import model.api.IPoolBall;

public class PoolTableView extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -949315898738707714L;
	private PoolTable model;

	public PoolTableView(PoolTable model) {
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
		g2d.setColor(Color.BLACK);
		// Draw Pool Table

		// Draw Balls

		for (IPoolBall ball : model.getBalls()) {

			g2d.fillOval((int) (ball.getPosX() - ball.getRadius()),
					(int) (ball.getPosY() + ball.getRadius()),
					(int) (ball.getRadius() * 2.0f),
					(int) (ball.getRadius() * 2.0f));
		}

		for (RectCushion cushion : model.getCushions()) {
			float width = cushion.getWidth();
			float height = cushion.getHeight();
			g2d.fillRect((int) (cushion.getPosX() - (0.5 * width)),
					10 + (int) (cushion.getPosY() - (0.5 * height)),
					(int) (width), (int) (height));
		}

		g.drawImage(bufferImage, 0, 0, null);
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}