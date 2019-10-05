package org.vadim.genetics;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Chromosome {
	private final Random rnd = new Random();

	private final Gene[] genes;
	private int lastFitnessValue = -1;

	public Chromosome(int genesNumber, boolean initialize) {
		genes = new Gene[genesNumber];
		if (initialize) generateGenes();
	}

	public int size() {
		return genes.length;
	}

	public Gene getGene(int idx) {
		return genes[idx];
	}

	public void setGene(int idx, final Gene gene) {
		genes[idx] = gene;
	}

	public int getFitness() {
		return lastFitnessValue;
	}

	public void evaluateFitness(final Function<Chromosome, Integer> ff) {
		lastFitnessValue = ff.apply(this);
	}

	public Chromosome copy() {
		Chromosome c2 = new Chromosome(genes.length, false);
		IntStream.range(0, genes.length)
			.parallel()
			.forEach(idx -> c2.genes[idx] = genes[idx].copy());
		c2.lastFitnessValue = lastFitnessValue;
		return c2;
	}

	private void generateGenes() {
		for (int i = 0; i < genes.length; i++) {
			genes[i] = new Gene(rnd.nextBoolean());
		}
	}
}
