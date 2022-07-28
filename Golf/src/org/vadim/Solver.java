package org.vadim;

import static org.vadim.SolutionPrinter.fillPath;
import static org.vadim.SolutionPrinter.printSolution;
import static org.vadim.data.SolutionState.ballPoints;
import static org.vadim.data.SolutionState.height;
import static org.vadim.data.SolutionState.holePoints;
import static org.vadim.data.SolutionState.width;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.vadim.data.Ball;
import org.vadim.turn.BallPathCalculator;
import org.vadim.turn.BallTurn;

public class Solver {
	private final char[][] field = new char[height][width];
	private List<List<List<BallTurn>>> allPaths;
	private int[] indexes;
	private boolean isOver = false;

	public void singleBallSolve() {
		List<BallTurn> path = BallPathCalculator.getSolutions(ballPoints.get(0), holePoints.get(0))
				.get(0);

		clearField();
		fillPath(field, path);
		printSolution(field);
	}

	public void multyBallsSolve() {
		allPaths = new ArrayList<>(ballPoints.size());
		for (int i = 0; i < ballPoints.size(); i++) {
			allPaths.add(null);
		}
		Ball[] ballsCombination = new Ball[ballPoints.size()];
		ballsCombinations(ballPoints, 0, ballPoints.size(), ballsCombination, this::handleCombination);
	}

	private void ballsCombinations(List<Ball> balls, int startPosition, int len, Ball[] result, Consumer<Ball[]> ballsCombinationConsumer) {
		if (isOver) {
			return;
		}

		if (len == 0) {
			ballsCombinationConsumer.accept(result);
			return;
		}

		for (int i = startPosition; i <= balls.size() - len; i++) {
			result[result.length - len] = balls.get(i);
			ballsCombinations(balls, i + 1, len - 1, result, ballsCombinationConsumer);
		}
	}

	private void handleCombination(Ball[] ballsCombination) {
		Stream.iterate(0, idx -> idx < ballsCombination.length, idx -> idx += 1)
				.forEach(idx -> allPaths.set(idx, BallPathCalculator.getSolutions(ballsCombination[idx], holePoints.get(idx))
						.stream()
						.filter(list -> !list.isEmpty())
						.collect(Collectors.toList())));

		// combine solutions
		indexes = new int[allPaths.size()];
		Arrays.fill(indexes, 0);

		int currIndex = indexes.length - 1;
		LABEL1:
		while (indexes[0] < allPaths.get(0).size()) {
			if (indexes[currIndex] >= allPaths.get(currIndex).size()) {
				do {
					--currIndex;
					if (currIndex < 0) {
						break LABEL1;
					}
					++indexes[currIndex];
				} while (indexes[currIndex] >= allPaths.get(currIndex).size());

				for (int j = currIndex + 1; j < indexes.length; j++) {
					indexes[j] = 0;
				}

				currIndex = indexes.length - 1;
			}

			if (checkSolution(ballsCombination)) {
				printSolution(field);
				isOver = true;
				return;
			}

			++indexes[currIndex];
		} // while
	}

	private boolean checkSolution(Ball[] balls) {
		clearField();
		try {
			for (int i = 0; i < balls.length; i++) {
				List<BallTurn> path = allPaths.get(i).get(indexes[i]);
				fillPath(field, path);
			}
		} catch (Exception ex) {
			return false;
		}

		return true;
	}
	
	private void clearField() {
		for (int y = 0; y < height; y++) {
			Arrays.fill(field[y], '.');
		}
	}

}
