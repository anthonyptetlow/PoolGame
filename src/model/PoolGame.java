package model;

import model.api.IPoolTable;

public class PoolGame {

	private PoolTable poolTable;

	public PoolGame() {
		rackTable();
	}

	public void rackTable() {
		poolTable = new PoolTable();

	}

	public IPoolTable getPoolTable() {
		return poolTable;
	}
}
