package es.uma.informatica.misia.ae.simpleea;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PermutationOx1Crossover implements Crossover {
	
	private Random random;
	
	public PermutationOx1Crossover(Random rnd) {
		this.random = rnd;
	}

	@Override
	public Permutation apply(Individual individual1, Individual individual2) {
		
		Permutation permutation1 = (Permutation) individual1;
		Permutation permutation2 = (Permutation) individual2;
		
		int lowerCut = random.nextInt(511);
		int upperCut = random.nextInt(511);
		if (lowerCut >= upperCut) {
			int lowerCut_ = lowerCut;
			lowerCut = upperCut;
			upperCut = lowerCut_;
		}
		
		Permutation child = new Permutation(permutation1);
		
		Map<Integer, Boolean> inclusionInChild = new HashMap<>();
		int z = 0;
		for (int i = lowerCut; i < upperCut; i++) {
			int value = permutation1.getChromosome()[i];
			child.getChromosome()[z] = value;
			inclusionInChild.put(value, true);
			z++;
		}
		
		for (int i = 0; i < permutation2.getChromosome().length && z < 512; i++) {
			int value = permutation2.getChromosome()[i];
			if (!inclusionInChild.containsKey(value)) {
				child.getChromosome()[z] = value;
				z++;
			}
		}
		
		return child;
	}
}