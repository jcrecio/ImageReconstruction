package es.uma.informatica.misia.ae.simpleea;

import java.util.Random;

public class PermutationScrambleMutation2 implements Mutation {

	private double prob;
	private Random rnd;
	private EvaluationsVariableMutation variableMutation;
	public static final String PERMUTATION_PROBABILITY_PARAM = "permutationProbability";
	
	public PermutationScrambleMutation2 (Random rnd, double prob, EvaluationsVariableMutation m) {
		this.rnd = rnd;
		this.prob = prob;
		this.variableMutation = m;
	}

	@Override
	public Individual apply(Individual individual, int numberOfEvaluations) {
		if (variableMutation != null) {
			for(int i = 0; i < variableMutation.getEvaluationInterval().length; i++) {
				if (numberOfEvaluations > variableMutation.getEvaluationInterval()[i]) {
					if (rnd.nextDouble() >= variableMutation.getMutationInterval()[i]) {
						return individual;
					}
				}
			}
		}

		Permutation original = (Permutation) individual;
		Permutation mutated = new Permutation(original);
		int length = mutated.getChromosome().length;
		if (rnd.nextDouble() < prob) {
			int random1 = -1, random2 = -1;
			int random1_ = rnd.nextInt(1, length - 1);
			int random2_ = rnd.nextInt(1, length - 1);

			if (random1_ > random2_) {
				int r1_ = random1_;
				random1 = random2_;
				random2 = r1_;
			}
			else {
				random1 = random1_;
				random2 = random2_;
			}
			int checkDiff = random2-random1;
			if (checkDiff == 0) return new Permutation(original);
			int slidingWindow = rnd.nextInt(checkDiff);
			
			for (int i = random1; i < random2; i++) {
				if (i + slidingWindow < random2) {
					mutated.getChromosome()[i + slidingWindow] = original.getChromosome()[i];
				}
				else {
					int position = random1 + (i + slidingWindow - random2);
					mutated.getChromosome()[position] = original.getChromosome()[i];
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
