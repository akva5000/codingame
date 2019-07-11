package org.vadim;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class MainLoopBottom {
	private Predicate<Integer> loopCondition;
	private UnaryOperator<Integer> loopDelta;
	private VariablesSetter mainLoopBodyBefore;
	private InnerLoop innerLoop;

	public MainLoopBottom(Predicate<Integer> loopCondition, UnaryOperator<Integer> loopDelta,
			VariablesSetter mainLoopBodyBefore, InnerLoop innerLoop) {
		this.loopCondition = loopCondition;
		this.loopDelta = loopDelta;
		this.mainLoopBodyBefore = mainLoopBodyBefore;
		this.innerLoop = innerLoop;
	}

	public void run(int indexId, int initialValue) {
		int xOut = 1;
		final int[] indexes = new int[4];

		indexes[indexId] = initialValue;
		while (loopCondition.test(indexes[indexId])) {
			if (xOut > 0) outNSpaces(xOut);
			mainLoopBodyBefore.apply(indexes, indexId);

			// apply inner loop
			innerLoop.apply(indexes);

			if (xOut > 0) outNSpaces(xOut);
			System.out.println();
			++xOut;
			indexes[indexId] = loopDelta.apply(indexes[indexId]);
		}
	}

	private void outNSpaces(int xOut) {
		for (int x = 0; x < xOut; x++) System.out.print(' ');
	}
}
