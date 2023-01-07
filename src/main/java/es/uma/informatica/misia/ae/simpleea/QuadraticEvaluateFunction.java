package es.uma.informatica.misia.ae.simpleea;

public class QuadraticEvaluateFunction implements EvaluateFunction {
	public double execute(int[] A, int[] B) {
		double sum = 0;
		for (int i = 0; i < A.length; i++) {
			sum += Math.pow(A[i] - B[i], 2);
		}
		
		return Math.sqrt(sum);
	}
}
