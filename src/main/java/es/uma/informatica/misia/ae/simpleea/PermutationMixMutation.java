package es.uma.informatica.misia.ae.simpleea;

import java.util.Random;

public class PermutationMixMutation implements Mutation {

	private double prob;
	private Random rnd;
	public static final String PERMUTATION_PROBABILITY_PARAM = "permutationProbability";
	
	public PermutationMixMutation (Random rnd, double prob, EvaluationsVariableMutation m) {
		this.rnd = rnd;
		this.prob = prob;
	}

	@Override
	public Individual apply(Individual individual, int numberOfEvaluations) {
		Permutation original = (Permutation) individual;
		Permutation mutated = new Permutation(original);
		int length = mutated.getChromosome().length;
		if (rnd.nextDouble() < prob) {
			int random = rnd.nextInt(length);
			int random2 = rnd.nextInt(length);

			int valueA = mutated.getChromosome()[random];
			int valueB = mutated.getChromosome()[random2];
			mutated.getChromosome()[random] = valueB;
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
