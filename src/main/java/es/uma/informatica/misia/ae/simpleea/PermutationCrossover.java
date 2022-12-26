package es.uma.informatica.misia.ae.simpleea;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PermutationCrossover implements Crossover {
	
	private Random rnd;
	private int cut1;
	private int cut2;
	
	public PermutationCrossover(Random rnd, int cut1, int cut2) {
		this.rnd=rnd;
		this.cut1 = cut1;
		this.cut2 = cut2;
	}

	@Override
	public Permutation apply(Individual individual1, Individual individual2) {
		
		Permutation permutation1 = (Permutation) individual1;
		Permutation permutation2 = (Permutation) individual2;
		
//		// 2 points of cut
//		int cut1 = rnd.nextInt(permutation1.getChromosome().length / 2);
//		int cut2 = rnd.nextInt(cut1+1, permutation2.getChromosome().length);

		Permutation child = new Permutation(permutation1);
		
		Map<Integer, Boolean> inclusionInChild = new HashMap<>();
		int z = 0;
		for (int i = cut1; i < cut2; i++) {
			int value = permutation1.getChromosome()[i];
			child.getChromosome()[z] = value;
			inclusionInChild.put(value, true);
			z++;
		}
		
		for (int i = 0; i < permutation2.getChromosome().length; i++) {
			int value = permutation2.getChromosome()[i];
			if (!inclusionInChild.containsKey(value)) {
				child.getChromosome()[z] = value;
				z++;
			}
		}
		
		return child;
	}
}