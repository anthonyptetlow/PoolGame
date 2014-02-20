package controller;

import java.util.TimerTask;

import model.PoolTable;

public class PoolTimerTask extends TimerTask {

	private PoolTable model;

	public PoolTimerTask(PoolTable model) {
		super();
		this.model = model;
	}

	@Override
	public void run() {
		model.passTime();

	}

}
