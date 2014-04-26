package ai.engine.imp;

import java.util.ArrayList;
import java.util.List;

public class UserModel {

	private List<Integer> pastMoveScores;

	public UserModel() {
		pastMoveScores = new ArrayList<Integer>();
	}

	public void addScore(Integer score) {
		pastMoveScores.add(score);
	}

	public Integer getScoreAverage() {
		Integer totalSocre = 0;
		for (Integer i : pastMoveScores) {
			totalSocre += i;
		}
		if (pastMoveScores.size() == 0)
			return 0;
		else
			return totalSocre / pastMoveScores.size();
	}
}
