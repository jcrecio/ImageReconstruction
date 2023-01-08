package es.uma.informatica.misia.ae.simpleea;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {

	public static boolean ENABLE_LOGS = true;
	
	public static void main(String args[]) {
		if (args.length < 2) {
			help();
			return;
		}
		if (args[0].equals("display")) {
			try {
				int[][] matrix = FileUtils.readMatrixFromFile(args[1]);
				
				int[] order = new int[512];
				for (int i = 0; i<512; i++)order[i]=i;
				FileUtils.ConvertMatrixIntoImage(matrix, order, "output1");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		if (args[0].equals("evaluate")) {
			Problem problem = new ImageReconstruction(new EuclideanDistanceEvaluateFunction(), args[1]);
			int[] order = new int[512];
			for (int i = 0; i<512; i++)order[i]=i;
			double fitness = problem.evaluate(new Permutation(order));
			System.out.println(fitness);
			return;
		}

		if (args.length < 6) {
			help();
			return;
		}
		
		
		long seedParam = System.currentTimeMillis();
		boolean includeInitialChromosome = false;
		if (args.length == 8 && !args[7].equals("-m")) {
			seedParam = Long.parseLong(args[7]);
		}
		
		EvaluationsVariableMutation variableMutation = null;
		if (args.length == 8 && args[7].equals("-m")) {
			variableMutation = new EvaluationsVariableMutation(
					new int[] {500, 1000, 1500},
					new double[] {0.35, 0.15, 0.012});
		}
		if (args.length == 9) {
			includeInitialChromosome = args[7].equals("true");
			seedParam = Long.parseLong(args[8]);
		}
		
		Random seed = new Random(seedParam);

		String file = args[1];
		int populationSize = Integer.parseInt(args[2]);
		int numberOfEvaluations = Integer.parseInt(args[3]);
		double mutationProbability = Double.parseDouble(args[4]);
		Crossover recombinationOperator = null;
		Mutation mutationOperator = null;
		try {
			mutationOperator = getMutationOperator(args[5], mutationProbability, seed, variableMutation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			recombinationOperator = getRecombinationOperator(args[6], seed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ProblemParameters problemParameters = new ProblemParameters();
		problemParameters.setNumberOfEvaluations(numberOfEvaluations);
		problemParameters.setMutationProbability(mutationProbability);
		problemParameters.setPopulationSize(populationSize);
		
		Map<String, Double> params = readEAParameters(problemParameters);

		Problem problem = new ImageReconstruction(new EuclideanDistanceEvaluateFunction(), file);
		
		runAlgorithm(params, 
				problem, 
				mutationOperator,
				recombinationOperator,
				includeInitialChromosome,
				getNameExecution(args[5], mutationProbability, args[6], populationSize,
						numberOfEvaluations));
	}

	private static void runAlgorithm(Map<String, Double> inputParams, 
			Problem problem, 
			Mutation mutation,
			Crossover recombination,
			boolean includeInitialPopulation,
			String name) {
		EvolutionaryAlgorithm difference = 
				new EvolutionaryAlgorithm(inputParams, problem, mutation, recombination, 
						includeInitialPopulation);

		Individual solution = difference.run();
		try {
			FileUtils.ConvertMatrixIntoImage(((ImageReconstruction) problem).getMatrix(),
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
		parameters.put(EvolutionaryAlgorithm.RANDOM_SEED_PARAM, (double) System.currentTimeMillis());
		return parameters;
	}
	
	private static Mutation getMutationOperator(String mutation, double mutationProbability, 
			Random seed, EvaluationsVariableMutation variableMutation) throws Exception {
		switch(mutation) {
			case "inversion": return new PermutationInversionMutation(seed, mutationProbability, variableMutation);
			case "simpleSwap": return new PermutationSimpleSwapMutation(seed, mutationProbability, variableMutation);
			case "swap": return new PermutationSwapMutation(seed, mutationProbability, variableMutation);
			case "scramble": return new PermutationScrambleMutation(seed, mutationProbability, variableMutation);
			case "scramble2": return new PermutationScrambleMutation2(seed, mutationProbability, variableMutation);

			default: throw new Exception("Specify a valid mutation operator");
		}
	}
	
	private static Crossover getRecombinationOperator(String recombination, Random seed) 
			throws Exception {
		switch(recombination) {
			case "edge": return new PermutationEdgeCrossover(seed);
			case "ox1": return new PermutationOx1Crossover(seed);
			default: throw new Exception("Specify a valid recombination operator");
		}
	}
	
	private static String getNameExecution(String mutationName, double mutationProbability,
			String recombinationMutation, int populationSize, int evaluationFunctions) {
		return mutationName + "_" + mutationProbability + "___" + recombinationMutation +
				"___population_" + populationSize + "___evaluations_" + evaluationFunctions;
	}
	
	private static void help() {
		System.out.println("Image Reconstruction.");
		System.out.println("-------------------------------------------------");
		System.out.println();
		System.out.println();
		System.out.println("Modes available: display, run");
		System.out.println("-------------------------------------------------");
		System.out.println("Mode display:");
		System.out.println("$ display <file with int matrix>");
		System.out.println();
		System.out.println();
		System.out.println("Mode run:");
		System.out.println("$ run <file> <population size> <number of evaluations> <mutation probability> <mutation operator> <recombination operator> [includeInitialChromosome] [seed]");
	}
}
