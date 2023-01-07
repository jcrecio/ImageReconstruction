package es.uma.informatica.misia.ae.simpleea;

public class RangeEvaluateFunction implements EvaluateFunction {
	public double execute(int[] A, int[] B) {
		double sum = 0;
		for (int i = 0; i < A.length; i++) {
			double abs = Math.abs(A[i] - B[i]);
			if (abs > 100 && abs < 200) {
				sum += 1;
			}
			else if (abs > 200) sum+=2;
		}
		
		return sum;
	}
}
