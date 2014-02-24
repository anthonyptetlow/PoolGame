package controller;

import java.util.TimerTask;

import model.Environment;
import model.api.IPoolTable;

import org.jbox2d.dynamics.Body;

public class PoolTimerTask extends TimerTask {

	private IPoolTable model;

	public PoolTimerTask(IPoolTable model) {
		super();
		this.model = model;
	}

	@Override
	public void run() {
		// isStationary();
		model.passTime();
	}

	private boolean isStationary() {
		Body body = Environment.world.getBodyList();
		System.out.println(body);
		while ((body = body.m_next) != null)
			System.out.println(body);
		return true;
	}
}
