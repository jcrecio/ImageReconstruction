package es.uma.informatica.misia.ae.simpleea;

import java.util.Random;

public class PermutationSwapMutation implements Mutation {

	private double prob;
	private Random rnd;
	public static final String PERMUTATION_PROBABILITY_PARAM = "permutationProbability";
	
	public PermutationSwapMutation (Random rnd, double prob, EvaluationsVariableMutation m) {
		this.rnd = rnd;
		this.prob = prob;
	}

	@Override
	public Individual apply(Individual individual, int numberOfEvaluations) {
		Permutation original = (Permutation) individual;
		Permutation mutated = new Permutation(original);
		int length = mutated.getChromosome().length;
		if (rnd.nextDouble() < prob) {
			int random1 = rnd.nextInt(length);
			int random2_ = rnd.nextInt(length);
			int random2;
			if (random1 < random2_) random2 = random2_;
			else {
				random2 = random1;
				random1 = random2_;
			}
			
			int[] randomIndexes = ImageReconstruction.getRandomOrder(random2 - random1, 1);
			for (int i = 0; i < randomIndexes.length; i++) {
				int valueA = mutated.getChromosome()[random1 + i];
				int valueB = mutated.getChromosome()[random1 + randomIndexes[i]];
				mutated.getChromosome()[random1 + i] = valueB;
				mutated.getChromosome()[random1 + randomIndexes[i]] = valueA;
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
