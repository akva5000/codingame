package org.vadim;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class MainLoopTop {
	private int size;
	private Predicate<Integer> loopCondition;
	private UnaryOperator<Integer> loopDelta;
	private VariablesSetter mainLoopBodyBefore;
	private InnerLoop innerLoop;

	public MainLoopTop(int size, Predicate<Integer> loopCondition, UnaryOperator<Integer> loopDelta,
			VariablesSetter mainLoopBodyBefore, InnerLoop innerLoop) {
		this.size = size;
		this.loopCondition = loopCondition;
		this.loopDelta = loopDelta;
		this.mainLoopBodyBefore = mainLoopBodyBefore;
		this.innerLoop = innerLoop;
	}

	public void run(int indexId, int initialValue) {
		int sizeY = size + size - 1;
		int xOut = sizeY / 2;
		final int[] indexes = new int[4];

		indexes[indexId] = initialValue;
		while (loopCondition.test(indexes[indexId])) {
			if (xOut > 0) outNSpaces(xOut);
			mainLoopBodyBefore.apply(indexes, indexId);

			// apply inner loop
			innerLoop.apply(indexes);

			if (xOut > 0) outNSpaces(xOut);
			System.out.println();
			indexes[indexId] = loopDelta.apply(indexes[indexId]);
			--xOut;
		}
	}

	private void outNSpaces(int xOut) {
		for (int x = 0; x < xOut; x++) System.out.print(' ');
	}
}
