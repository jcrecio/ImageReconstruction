package es.uma.informatica.misia.ae.simpleea;

import java.util.Random;

public class PermutationScrumbleMutation implements Mutation {

	private double prob;
	private Random rnd;
	public static final String PERMUTATION_PROBABILITY_PARAM = "permutationProbability";
	
	public PermutationScrumbleMutation (Random rnd, double prob) {
		this.rnd = rnd;
		this.prob = prob;
	}

	@Override
	public Individual apply(Individual individual) {
		Permutation original = (Permutation) individual;
		Permutation mutated = new Permutation(original);
		int length = mutated.getChromosome().length;
		if (rnd.nextDouble() < prob) {
			int random1 = -1, random2 = -1;
			int random1_ = new Random().nextInt(1, length - 1);
			int random2_ = new Random().nextInt(1, length - 1);

			if (random1_ > random2_) {
				int r1_ = random1_;
				random1 = random2_;
				random2 = r1_;
			}
			
			for (int i = random1; i < random2; i++) {
				if (i == random1) {
					mutated.getChromosome()[random1] = mutated.getChromosome()[random2];
					continue;
				}
				mutated.getChromosome()[i] = mutated.getChromosome()[i+1];
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
