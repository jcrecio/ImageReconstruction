package es.uma.informatica.misia.ae.simpleea;

import java.security.SecureRandom;
import java.util.Random;

public class ImageReconstruction implements Problem {
	private int[][] matrix;
	private int n;
	private int m;
	
	public ImageReconstruction(String file) {
		this.matrix = FileUtils.readMatrixFromFile(file);
		this.n = this.matrix.length;
		this.m = this.matrix[0].length;
	}
	
	public double evaluate(Individual individual) {
		Permutation permutation = (Permutation)individual;
		int[] chromosome = permutation.getChromosome();
		double result = 0.0;
		
		int length = chromosome.length;
		for (int i=0 ; i < length-1; i++) {
			int[] row1 = matrix[chromosome[i]];
			int[] row2 = matrix[chromosome[i + 1]];
			result += getDifference(row1, row2);
		}
		
		return result;
	}
	
	public double getDifference(int[] A, int[] B) {
		double[] partialDifferences = new double[A.length];
		double numberOfDifferentPixels = 0;
		for (int i = 0; i < A.length; i++) {
			partialDifferences[i] = Math.abs(A[i] - B[i]);
			if (partialDifferences[i] > 0) numberOfDifferentPixels++;
		}
		
		double difference = 0;
		for (int i = 0; i < A.length; i++) {
			difference += partialDifferences[i] * numberOfDifferentPixels;
		}
		
		return difference;
	}
	
	
	public Permutation generateRandomIndividual(Random rnd) {
	    int a[] = new int[n];

	    for (int i = 0; i < n; i++) {
	        a[i] = i;
	    }

	    int [] result = new int[n];

	    int x = n;
	    SecureRandom rd = new SecureRandom();

	    for (int i = 0; i < n; i++) {
	        int k = rd.nextInt(x);

	        result[i] = a[k];

	        a[k] = a[x-1];

	        x--;
	    }

	    return new Permutation(result);
	}
	
	public int[][] getMatrix(){
		return matrix;
	}	
}
