package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.PoolModel;

public class PoolTableView extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -949315898738707714L;
	private PoolModel model;

	public PoolTableView(PoolModel model) {
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

		g2d.drawRect((int) (model.dbBody.getPosition().x * 100),
				(int) (model.dbBody.getPosition().y * 100), 50, 50);

		g.drawImage(bufferImage, 0, 0, null);
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}
}