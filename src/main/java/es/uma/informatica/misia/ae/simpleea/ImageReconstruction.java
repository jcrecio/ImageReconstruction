package es.uma.informatica.misia.ae.simpleea;

import java.util.Random;

public class ImageReconstruction {
	private int[][] matrix;
	
	public ImageReconstruction(String file) {
		this.matrix = FileUtils.readMatrixFromFile(file);
	}
	
	public double evaluate(Individual individual) {
		Permutation permutation = (Permutation)individual;
		int[] chromosome = permutation.getChromosome();
		double result = 0.0;
		
		int length = chromosome.length;
		for (int i=0 ; i < length-1; i++) {
			int[] row1 = matrix[i];
			int[] row2 = matrix[i+1];
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
		return null;
		//return new BinaryString(n,rnd);
	}
	
}
