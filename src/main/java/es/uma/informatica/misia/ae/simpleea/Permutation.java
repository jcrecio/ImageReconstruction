package es.uma.informatica.misia.ae.simpleea;

public class Permutation extends Individual {
	private int[] chromosome;
	
	public Permutation() {
		this.setChromosome(new int[0]);
	}
	
	public Permutation(int[] values) {
		this.setChromosome(values);
	}
	
	public Permutation(Permutation individual) {
		this.setChromosome(individual.chromosome.clone());
		this.setFitness(individual.getFitness());
	}

	public int[] getChromosome() {
		return chromosome;
	}

	public void setChromosome(int[] chromosome) {
		this.chromosome = chromosome;
	}
}
