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
				
				
				int[] order2 = ImageReconstruction.getRandomOrder(512, 512);
				FileUtils.ConvertMatrixIntoImage(matrix, order2, "output2");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}

		if (args.length < 6) {
			// help();
			return;
		}

		Problem problem = new ImageReconstruction(args[1]);
//		Individual bestSolution = new Individual();
//		bestSolution.setFitness(Double.MAX_VALUE);
//		ProblemParameters pp = new ProblemParameters();
//		pp.setNumberOfEvaluations(1000);
//		for (double i = 0.8; i > 0.09; i -= 0.1) {
//			pp.setMutationProbability(i);
//			for (int j = 10; j < 100; j += 25) {
//				pp.setPopulationSize(j);
//				for (int k = 20; k < 250; k += 20) {
//					pp.setCut1(k);
//					pp.setCut2(500 - k);
//					Map<String, Double> params = readEAParameters(pp);
//					EvolutionaryAlgorithm evolutionaryAlgorithm = new EvolutionaryAlgorithm(params, problem);
//
//					Individual partialBestSolution = evolutionaryAlgorithm.run();
//					if (partialBestSolution.getFitness()<bestSolution.getFitness()) {
//						bestSolution = partialBestSolution;
//						try {
//							FileUtils.ConvertMatrixIntoImage(((ImageReconstruction) problem).getMatrix(),
//									((Permutation) bestSolution).getChromosome(), "output__FITNESS_IS_" + bestSolution.getFitness());
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
//
//				}
//
//			}
//		}
//		System.out.println(bestSolution);

		
		Individual bestSolution = new Individual();
		bestSolution.setFitness(Double.MAX_VALUE);
		ProblemParameters pp = new ProblemParameters();
		pp.setNumberOfEvaluations(1000);
		pp.setMutationProbability(0.5);
		pp.setCut1(150);
		pp.setCut2(350);
		pp.setPopulationSize(100);
		Map<String, Double> params = readEAParameters(pp);
		EvolutionaryAlgorithm evolutionaryAlgorithm = new EvolutionaryAlgorithm(params, problem);

		Individual partialBestSolution = evolutionaryAlgorithm.run();
		if (partialBestSolution.getFitness()<bestSolution.getFitness()) {
			bestSolution = partialBestSolution;
			try {
				FileUtils.ConvertMatrixIntoImage(((ImageReconstruction) problem).getMatrix(),
						((Permutation) bestSolution).getChromosome(), "output__FITNESS_IS_" + bestSolution.getFitness());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

//		Map<String, Double> parameters = readEAParameters(args);
//		EvolutionaryAlgorithm evolutionaryAlgorithm = new EvolutionaryAlgorithm(parameters, problem);
//		
//		Individual bestSolution = evolutionaryAlgorithm.run();
//		System.out.println(bestSolution);
//		try {
//			FileUtils.ConvertMatrixIntoImage(((ImageReconstruction)problem).getMatrix(), 
//					null, "input_");
//			FileUtils.ConvertMatrixIntoImage(((ImageReconstruction)problem).getMatrix(), 
//					((Permutation)bestSolution).getChromosome(), "output/output_");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	private static Map<String, Double> readEAParameters(ProblemParameters pp) {
		Map<String, Double> parameters = new HashMap<>();
		parameters.put(EvolutionaryAlgorithm.POPULATION_SIZE_PARAM, pp.getPopulationSize());
		parameters.put(EvolutionaryAlgorithm.MAX_FUNCTION_EVALUATIONS_PARAM, pp.getNumberOfEvaluations());
		parameters.put(PermutationMutation.PERMUTATION_PROBABILITY_PARAM, pp.getMutationProbability());
		parameters.put(EvolutionaryAlgorithm.CUT1, (double)pp.getCut1());
		parameters.put(EvolutionaryAlgorithm.CUT2, (double)pp.getCut2());

		long randomSeed = System.currentTimeMillis();
//		if (args.length > 4) {
//			randomSeed = Long.parseLong(args[6]);
//		}
		parameters.put(EvolutionaryAlgorithm.RANDOM_SEED_PARAM, (double) randomSeed);
		return parameters;
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
		parameters.put(EvolutionaryAlgorithm.RANDOM_SEED_PARAM, (double) randomSeed);
		return parameters;
	}
}
