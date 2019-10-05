package org.vadim.genetics;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Algorithm {
	private static final double uniformRate = 0.5;
	private static final double mutationRate = 0.3d;
	private static final int tournamentSize = 3;
	
	private static final boolean elitism = true;
	private static final int offspringSize = 10;
	private static final int elitismNumber = 2;

	private static final ThreadLocal<Random> rnd = ThreadLocal.withInitial(() -> new Random());

	public static Chromosome evaluate(final Population population, final Function<Chromosome, Integer> ff, int roundNumber) {
		Population pop = population;
		Population newPopulation = new Population(pop.size(), pop.getGenSize(), false);
		Chromosome bestChromo = population.get(0);
		
		int round = 0;
		while (round < roundNumber) {
			evolvePopulation(pop, newPopulation);
			newPopulation.evaluateFitness(ff);
			
			Chromosome bchromo = newPopulation.getFittest();
			if(bchromo.getFitness() > bestChromo.getFitness()) {
				bestChromo = bchromo.copy();
			}
		
			Population p = pop;
			pop = newPopulation;
			newPopulation = p;
			if(bestChromo.getFitness() > 0) ++round;
		}

		return bestChromo;
	}

	private static void evolvePopulation(final Population pop, final Population newPopulation) {
		int elitismOffset = elitism ? elitismNumber : 0;
		
		// Keep our best individual
		if(elitism) {
			for (int i = 0; i < elitismOffset; i++) {
				newPopulation.saveChromosome(i, pop.get(i));
			}
		}
		

		tournamentSelection2(pop, newPopulation, elitismOffset, offspringSize, tournamentSize);
		IntStream.range(elitismOffset + offspringSize, pop.size())
			.parallel()
			.forEach(idx -> newPopulation.saveChromosome(idx,
					crossover2(
							pop.get(rnd.get().nextInt(pop.size())),
							pop.get(rnd.get().nextInt(pop.size())))));
		
		IntStream.range(elitismOffset, elitismOffset + offspringSize)
			.parallel()
			.mapToObj(idx -> newPopulation.get(idx))
			.forEach(chromo -> mutate2(chromo));
	}

	// Crossover individuals	
	private static Chromosome crossover2(Chromosome chromo1, Chromosome chromo2) {
		Chromosome newSol = new Chromosome(chromo1.size(), false);
		// Loop through genes
		IntStream.range(0, chromo1.size())
			.parallel()
			.forEach(idx -> {
				newSol.setGene(idx,
						rnd.get().nextDouble() <= uniformRate ? chromo1.getGene(idx) : chromo2.getGene(idx));
			});

		return newSol;
	}
	
	// Mutate an individual
	private static void mutate2(final Chromosome chromo) {
		// Loop through genes
		IntStream.range(0, chromo.size())
			.parallel()
			.forEach(idx -> {
				if (rnd.get().nextDouble() <= mutationRate) {
					chromo.getGene(idx).setValue(!chromo.getGene(idx).getValue());
				}
			});
	}


	// Select individuals for crossover
	private static void tournamentSelection2(final Population pop, final Population newPop, int startPos, int number, int simple) {
		IntStream.range(startPos, startPos + number)
			.parallel()
			.forEach(idx -> {
				final Chromosome chromo = Stream.generate( () -> pop.get(rnd.get().nextInt(pop.size())))
					.limit(simple)
					.max( (Chromosome o1, Chromosome o2) -> Integer.compare(o2.getFitness(), o1.getFitness()))
					.get();
				newPop.saveChromosome(idx, chromo);
			});
	}
}
