package es.uma.informatica.misia.ae.simpleea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EvolutionaryAlgorithm {
	public static final String MAX_FUNCTION_EVALUATIONS_PARAM = "maxFunctionEvaluations";
	public static final String RANDOM_SEED_PARAM = "randomSeed";
	public static final String POPULATION_SIZE_PARAM = "populationSize";
	public static final String CUT1 = "cut1";
	public static final String CUT2 = "cut2";
	
	private Problem problem;
	private int functionEvaluations;
	private int maxFunctionEvaluations;
	private List<Individual> population;
	private int populationSize;
	private Random seed;
	
	private Individual bestSolution;
	
	private Selection selection;
	private Replacement replacement;
	private Mutation mutation;
	private Crossover recombination;
	
	public EvolutionaryAlgorithm(
			Map<String, Double> parameters, 
			Problem problem,
			Mutation mutation,
			Crossover recombination) {
		configureAlgorithm(parameters, problem, mutation, recombination);
	}
	
	private void configureAlgorithm(Map<String, Double> parameters, 
			Problem problem, Mutation mutation, Crossover recombination){
		
		populationSize = parameters.get(POPULATION_SIZE_PARAM).intValue();
		maxFunctionEvaluations = parameters.get(MAX_FUNCTION_EVALUATIONS_PARAM).intValue();
		
		long randomSeed = parameters.get(RANDOM_SEED_PARAM).longValue();
		this.problem = problem; 
		this.seed = new Random(randomSeed);
		this.mutation = mutation;
		this.selection = new BinaryTournament(seed);
		this.replacement = new ElitistReplacement();
		this.recombination = recombination;
	}
	
	public Individual run() {
		population = generateInitialPopulation();
		functionEvaluations = 0;
		
		evaluatePopulation(population);
		while (functionEvaluations < maxFunctionEvaluations) {
			Individual parent1 = selection.selectParent(population);
			Individual parent2 = selection.selectParent(population);
			Individual child = recombination.apply(parent1, parent2);
			child = mutation.apply(child);
			evaluateIndividual(child);
			population = replacement.replacement(population, Arrays.asList(child));
			if (Main.ENABLE_LOGS) {
				System.out.println("Evaluation number: " + functionEvaluations + " fitness: " + + bestSolution.getFitness());
			}
		}
		
		return bestSolution;
	}
	
	private void evaluateIndividual(Individual individual) {
		double fitness = problem.evaluate(individual);
		individual.setFitness(fitness);
		functionEvaluations++;
		checkIfBest(individual);
	}

	private void checkIfBest(Individual individual) {
		if (bestSolution == null || individual.getFitness() < bestSolution.getFitness()) {
			bestSolution = individual;
		}
	}

	private void evaluatePopulation(List<Individual> population) {
		for (Individual individual: population) {
			evaluateIndividual(individual);
		}
	}

	private List<Individual> generateInitialPopulation() {
		List<Individual> population = new ArrayList<>();
		for (int i=0; i < populationSize; i++) {
			population.add(problem.generateRandomIndividual(seed));
		}
		return population;
	}
	
}
