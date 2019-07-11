package org.vadim;

@FunctionalInterface
public interface VariablesSetter {
	void apply(int[] indexes, int loopIndexId);
}
