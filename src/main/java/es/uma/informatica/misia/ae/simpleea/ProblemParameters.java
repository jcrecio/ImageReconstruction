package es.uma.informatica.misia.ae.simpleea;

public class ProblemParameters {
	private double mutationProbability;
	private double populationSize;
	private double numberOfEvaluations;
	private double penalizer;
	private int cut1;
	private int cut2;
	
	public double getMutationProbability() {
		return mutationProbability;
	}
	public void setMutationProbability(double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}
	public double getPopulationSize() {
		return populationSize;
	}
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}
	public double getNumberOfEvaluations() {
		return numberOfEvaluations;
	}
	public void setNumberOfEvaluations(int numberOfEvaluations) {
		this.numberOfEvaluations = numberOfEvaluations;
	}
	public double getPenalizer() {
		return penalizer;
	}
	public void setPenalizer(int penalizer) {
		this.penalizer = penalizer;
	}
	public int getCut1() {
		return cut1;
	}
	public void setCut1(int cut1) {
		this.cut1 = cut1;
	}
	public int getCut2() {
		return cut2;
	}
	public void setCut2(int cut2) {
		this.cut2 = cut2;
	}
}
