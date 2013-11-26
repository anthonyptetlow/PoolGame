package mock.view;

import javax.swing.JFrame;

import mock.model.MockTable;

public class AppWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9222311713904614969L;
	private TableView table;

	public AppWindow(MockTable model) {

		super();
		table = new TableView(model);
		add(table);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 600);
		setVisible(true);

	}
}
