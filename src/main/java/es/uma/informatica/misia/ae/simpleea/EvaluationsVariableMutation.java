package es.uma.informatica.misia.ae.simpleea;

public class EvaluationsVariableMutation {
	private int[] evaluationInterval;
	private double[] mutationInterval;
	
	public EvaluationsVariableMutation(int[] evaluationInterval,
							double[] mutationInterval) {
		this.evaluationInterval = evaluationInterval;
		this.mutationInterval = mutationInterval;
		
	}
	public int[] getEvaluationInterval() {
		return evaluationInterval;
	}
	public void setEvaluationInterval(int[] evaluationInterval) {
		this.evaluationInterval = evaluationInterval;
	}
	public double[] getMutationInterval() {
		return mutationInterval;
	}
	public void setMutationInterval(double[] mutationInterval) {
		this.mutationInterval = mutationInterval;
	}
}
