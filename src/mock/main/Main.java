package mock.main;

import mock.model.MockTable;
import mock.view.AppWindow;

import org.jbox2d.common.Vec2;


public class Main {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		MockTable model = new MockTable();

		Vec2 position = model.dbBody.getPosition();

		float angle = model.dbBody.getAngle();

		new AppWindow(model);

		for (int i = 0; i < 60; i++) {
			model.step();
			System.out.println((int) position.x + " " + (int) position.y + " "
					+ angle);
			Thread.sleep(100);
		}
	}

}
