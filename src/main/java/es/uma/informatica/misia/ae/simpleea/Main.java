package es.uma.informatica.misia.ae.simpleea;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {

	public static void main(String args []) {	
		if (args.length < 2) {
			// help();
			return;
		}
		if (args[0].equals("convert")) {
			try {
				int[][] matrix= FileUtils.readMatrixFromFile(args[1]);
				FileUtils.ConvertMatrixIntoImage(matrix, null, "output");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (args.length < 6) {
			// help();
			return;
		}
		
		Problem problem = new ImageReconstruction(args[1]);
		
		Map<String, Double> parameters = readEAParameters(args);
		EvolutionaryAlgorithm evolutionaryAlgorithm = new EvolutionaryAlgorithm(parameters, problem);
		
		Individual bestSolution = evolutionaryAlgorithm.run();
		System.out.println(bestSolution);
		try {
			FileUtils.ConvertMatrixIntoImage(((ImageReconstruction)problem).getMatrix(), 
					null, "input_");
			FileUtils.ConvertMatrixIntoImage(((ImageReconstruction)problem).getMatrix(), 
					((Permutation)bestSolution).getChromosome(), "output/output_");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Map<String, Double> readEAParameters(String[] args) {
		Map<String, Double> parameters = new HashMap<>();
		parameters.put(EvolutionaryAlgorithm.POPULATION_SIZE_PARAM, Double.parseDouble(args[2]));
		parameters.put(EvolutionaryAlgorithm.MAX_FUNCTION_EVALUATIONS_PARAM, Double.parseDouble(args[3]));
		parameters.put(PermutationMutation.PERMUTATION_PROBABILITY_PARAM, Double.parseDouble(args[4]));
		
		long randomSeed = System.currentTimeMillis();
		if (args.length > 4) {
			randomSeed = Long.parseLong(args[6]);
		}
		parameters.put(EvolutionaryAlgorithm.RANDOM_SEED_PARAM, (double)randomSeed);
		return parameters;
	}
}
