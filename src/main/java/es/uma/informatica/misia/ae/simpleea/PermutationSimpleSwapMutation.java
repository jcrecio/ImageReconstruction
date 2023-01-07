package es.uma.informatica.misia.ae.simpleea;

import java.util.Random;

public class PermutationSimpleSwapMutation implements Mutation {

	private double prob;
	private Random rnd;
	public static final String PERMUTATION_PROBABILITY_PARAM = "permutationProbability";
	
	public PermutationSimpleSwapMutation (Random rnd, double prob) {
		this.rnd = rnd;
		this.prob = prob;
	}

	@Override
	public Individual apply(Individual individual) {
		Permutation original = (Permutation) individual;
		Permutation mutated = new Permutation(original);
		int length = mutated.getChromosome().length;
		if (rnd.nextDouble() < prob) {
			int random1 = new Random().nextInt(length);
			int random2 = new Random().nextInt(length);
			
			int valueA = mutated.getChromosome()[random1];
			int valueB = mutated.getChromosome()[random2];
			mutated.getChromosome()[random1] = valueB;
			mutated.getChromosome()[random2] = valueA;			
		}
		return mutated;
	}

	public double getpProb() {
		return prob;
	}

	public void setBitFlipProb(double prob) {
		this.prob = prob;
	}

}
