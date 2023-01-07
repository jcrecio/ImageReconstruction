package es.uma.informatica.misia.ae.simpleea;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String args[]) {
		if (args.length < 2) {
			// help();
			return;
		}
		if (args[0].equals("convert")) {
			try {
				int[][] matrix = FileUtils.readMatrixFromFile(args[1]);
				
				int[] order = new int[512];
				for (int i = 0; i<512; i++)order[i]=i;
				FileUtils.ConvertMatrixIntoImage(matrix, order, "output1");
//				
//				
//				int[] order2 = ImageReconstruction.getRandomOrder(512, 512);
//				FileUtils.ConvertMatrixIntoImage(matrix, order2, "output2");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}

		if (args.length < 6) {
			// help();
			return;
		}
	
		int[][] matrix = FileUtils.readMatrixFromFile(args[1]);
		
//		int[] order = new int[512];
//		for (int i = 0; i<512; i++)order[i]=i;
//		try {
//			FileUtils.ConvertMatrixIntoImage(matrix, order, "input");
//			System.out.println();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		ProblemParameters pp = new ProblemParameters();
		pp.setNumberOfEvaluations(1000_000);
		pp.setMutationProbability(0.35);
		pp.setPopulationSize(50);
		Map<String, Double> params = readEAParameters(pp);
		
		Problem problemQuadratic = new ImageReconstruction(new QuadraticEvaluateFunction(), args[1]);
		
//		double originalValue = problemQuadratic.evaluate(new Permutation(order));
//		System.out.println(originalValue);
		
//		Problem problemDifference = new ImageReconstruction(new DifferenceEvaluateFunction(), args[1]);
//		Problem problemRange = new ImageReconstruction(new RangeEvaluateFunction(), args[1]);
		
		runAlgorithm(params, problemQuadratic, "Quadratic");
//		runAlgorithm(params, problemDifference, "Difference");
//		runAlgorithm(params, problemRange, "Range");
	}

	private static void runAlgorithm(Map<String, Double> inputParams, Problem p, String name) {
		EvolutionaryAlgorithm difference = new EvolutionaryAlgorithm(inputParams, p);

		Individual solution = difference.run();
		try {
			FileUtils.ConvertMatrixIntoImage(((ImageReconstruction) p).getMatrix(),
					((Permutation) solution).getChromosome(), name + "_" + solution.getFitness());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Map<String, Double> readEAParameters(ProblemParameters pp) {
		Map<String, Double> parameters = new HashMap<>();
		parameters.put(EvolutionaryAlgorithm.POPULATION_SIZE_PARAM, pp.getPopulationSize());
		parameters.put(EvolutionaryAlgorithm.MAX_FUNCTION_EVALUATIONS_PARAM, pp.getNumberOfEvaluations());
		parameters.put(PermutationSwapMutation.PERMUTATION_PROBABILITY_PARAM, pp.getMutationProbability());
		parameters.put(EvolutionaryAlgorithm.CUT1, (double)pp.getCut1());
		parameters.put(EvolutionaryAlgorithm.CUT2, (double)pp.getCut2());
		parameters.put(EvolutionaryAlgorithm.RANDOM_SEED_PARAM, (double) System.currentTimeMillis());
		return parameters;
	}
}
