package view;

import javax.swing.JFrame;

import model.PoolModel;

public class AppWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9222311713904614969L;
	private PoolTableView table;

	public AppWindow(PoolModel model) {

		super();
		table = new PoolTableView(model);
		add(table);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setVisible(true);

	}
}
