package es.uma.informatica.misia.ae.simpleea;

import java.util.Random;

public class PermutationMutation implements Mutation {

	private double prob;
	private Random rnd;
	public static final String PERMUTATION_PROBABILITY_PARAM = "permutationProbability";
	
	public PermutationMutation (Random rnd, double prob) {
		this.rnd = rnd;
		this.prob = prob;
	}

	@Override
	public Individual apply(Individual individual) {
		Permutation original = (Permutation) individual;
		Permutation mutated = new Permutation(original);
		int length = mutated.getChromosome().length;
		for (int i = 0; i < length; i++) {
			if (rnd.nextDouble() < prob) {
				int random = new Random().nextInt(length);
				int valueA = mutated.getChromosome()[i];
				int valueB = mutated.getChromosome()[random];
				mutated.getChromosome()[i] = valueB;
				mutated.getChromosome()[random] = valueA;
			}
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
