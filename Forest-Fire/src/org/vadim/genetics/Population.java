package org.vadim.genetics;

import java.util.Arrays;
import java.util.function.Function;

public class Population {
	private final Chromosome[] chromosomes;
	private final int genSize;

	public static Population build(int psize, int gsize, boolean initialize) {
		return new Population(psize, gsize, initialize);
	}

	public Population(int populationSize, int genesNumber, boolean initialize) {
		this.chromosomes = new Chromosome[populationSize];
		genSize = genesNumber;
		if (initialize) {
			for (int i = 0; i < populationSize; i++) {
				chromosomes[i] = new Chromosome(genesNumber, true);
			}
		}
	}

	public int size() {
		return chromosomes.length;
	}

	public int getGenSize() {
		return genSize;
	}

	public Chromosome get(int idx) {
		return chromosomes[idx];
	}

	public Chromosome getFittest() {
		return chromosomes[0];
	}

	public void evaluateFitness(final Function<Chromosome, Integer> ff) {
		Arrays.asList(chromosomes)
			.parallelStream()
			.forEach(chromo -> chromo.evaluateFitness(ff));
		
		Arrays.sort(chromosomes, (o1, o2) ->  Integer.compare(o2.getFitness(), o1.getFitness()));
	}

	public void saveChromosome(int index, Chromosome indiv) {
		chromosomes[index] = indiv;
	}
	
	void fitnessSort() {
		Arrays.sort(chromosomes, (o1, o2) ->  Integer.compare(o2.getFitness(), o1.getFitness()));
	}
}
