package org.vadim;

import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class InnerLoopImpl implements InnerLoop {
	private int indexId;
	private Predicate<Integer> loopCondition;
	private UnaryOperator<Integer> loopDelta;
	private VariablesSetter innerLoopBodyAfter;
	private PrintChar printChar;

	public InnerLoopImpl(int indexId, Predicate<Integer> loopCondition, UnaryOperator<Integer> loopDelta,
			VariablesSetter innerLoopBodyAfter, PrintChar printChar) {
		this.indexId = indexId;
		this.loopCondition = loopCondition;
		this.loopDelta = loopDelta;
		this.innerLoopBodyAfter = innerLoopBodyAfter;
		this.printChar = printChar;
	}

	public void apply(int[] indexes) {
		boolean flag = false;
		while (loopCondition.test(indexes[indexId])) {
			if (!flag) {
				flag = true;
			} else {
				System.out.print(' ');
			}
			System.out.print(printChar.getChar(indexes));
			innerLoopBodyAfter.apply(indexes, indexId);
			indexes[indexId] = loopDelta.apply(indexes[indexId]);
		}
	}

}
