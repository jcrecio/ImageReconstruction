package es.uma.informatica.misia.ae.simpleea;

public class ProblemParameters {
	private double mutationProbability;
	private int populationSize;
	private int numberOfEvaluations;
	
	
	
	public double getMutationProbability() {
		return mutationProbability;
	}
	public void setMutationProbability(double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}
	public int getPopulationSize() {
		return populationSize;
	}
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}
	public int getNumberOfEvaluations() {
		return numberOfEvaluations;
	}
	public void setNumberOfEvaluations(int numberOfEvaluations) {
		this.numberOfEvaluations = numberOfEvaluations;
	}
}
