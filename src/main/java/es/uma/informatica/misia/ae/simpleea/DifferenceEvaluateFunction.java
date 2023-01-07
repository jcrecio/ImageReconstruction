package es.uma.informatica.misia.ae.simpleea;

public class DifferenceEvaluateFunction implements EvaluateFunction {
	public double execute(int[] A, int[] B) {
		double sum = 0;
		for (int i = 0; i < A.length; i++) {
			sum += Math.abs(A[i] - B[i]);
		}
		
		return sum;
	}
}
