package es.uma.informatica.misia.ae.simpleea;

import java.util.Random;

public class PermutationInversionMutation implements Mutation {

	private double prob;
	private Random rnd;
	private EvaluationsVariableMutation variableMutation;
	public static final String PERMUTATION_PROBABILITY_PARAM = "permutationProbability";
	
	public PermutationInversionMutation (Random rnd, double prob,
			EvaluationsVariableMutation variableMutation) {
		this.rnd = rnd;
		this.prob = prob;
		this.variableMutation = variableMutation;
	}

	@Override
	public Individual apply(Individual individual, int numberOfEvaluations) {
		if (rnd.nextDouble() >= prob) return individual;
		
		Permutation original = (Permutation) individual;
		Permutation mutated = new Permutation(original);
		
		int length = mutated.getChromosome().length;
		int random1 = rnd.nextInt(length);
		int random2_ = rnd.nextInt(length);
		int random2;
		
		if (random1 < random2_) random2 = random2_;
		else {
			random2 = random1;
			random1 = random2_;
		}
		
		int x = random2 - ((random2-random1)/2);
		for (int i = random1; i < x; i++) {
			int valueA = mutated.getChromosome()[i];
			int valueB = mutated.getChromosome()[random2 - (i - random1)];
			mutated.getChromosome()[i] = valueB;
			mutated.getChromosome()[random2 - (i - random1)] = valueA;
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
