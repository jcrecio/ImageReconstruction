package es.uma.informatica.misia.ae.simpleea;

import java.util.Random;

public class PermutationInversionMutation implements Mutation {

	private double prob;
	private Random rnd;
	public static final String PERMUTATION_PROBABILITY_PARAM = "permutationProbability";
	
	public PermutationInversionMutation (Random rnd, double prob) {
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
			int random2_ = new Random().nextInt(length);
			int random2;
			if (random1 < random2_) random2 = random2_;
			else {
				random2 = random1;
				random1 = random2_;
			}
			
			for (int i = 0; i < (random2_ - random1) / 2; i++) {
				int valueA = mutated.getChromosome()[random1 + i];
				int valueB = mutated.getChromosome()[random2 - i];
				mutated.getChromosome()[random1 + i] = valueB;
				try {
					mutated.getChromosome()[random1 - i] = valueA;

				}
				catch(Exception ex) {
					int a = 1;
				}
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
