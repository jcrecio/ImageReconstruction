package es.uma.informatica.misia.ae.simpleea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class PermutationEdgeCrossover implements Crossover {
	
	private Random rnd;
	
	public PermutationEdgeCrossover(Random rnd) {
		this.rnd=rnd;
	}

	@Override
	public Permutation apply(Individual individual1, Individual individual2) {
		
		Permutation permutation1 = (Permutation) individual1;
		Permutation permutation2 = (Permutation) individual2;
		
		HashMap<Integer, List<Integer>> adjacencyMatrixResult = new HashMap<Integer, List<Integer>>();

		int[] chromosome1 = permutation1.getChromosome();
		int[] chromosome2 = permutation2.getChromosome();

		for(int i = 0; i < chromosome1.length; i++) {
			List<Integer> neighbours = new ArrayList<Integer>();

			if (i == 0) {
				neighbours.add(chromosome1[511]);
				neighbours.add(chromosome1[1]);
				
				if (!adjacencyMatrixResult.containsKey(chromosome2[511])) neighbours.add(chromosome2[511]);
				if (!adjacencyMatrixResult.containsKey(chromosome2[1])) neighbours.add(chromosome2[1]);
				
				adjacencyMatrixResult.put(i, neighbours);
				continue;
			}
			else if (i == 511) {
				neighbours.add(chromosome1[510]);
				neighbours.add(chromosome1[0]);
				
				if (!adjacencyMatrixResult.containsKey(chromosome2[510])) neighbours.add(chromosome2[510]);
				if (!adjacencyMatrixResult.containsKey(chromosome2[0])) neighbours.add(chromosome2[0]);
				
				adjacencyMatrixResult.put(i, neighbours);
				continue;
			}
			
			neighbours.add(chromosome1[i-1]);
			neighbours.add(chromosome1[1+1]);
			
			if (!adjacencyMatrixResult.containsKey(chromosome2[i-1])) neighbours.add(chromosome2[i-1]);
			if (!adjacencyMatrixResult.containsKey(chromosome2[i+1])) neighbours.add(chromosome2[i+1]);
			
			adjacencyMatrixResult.put(i, neighbours);			
		}
		
		Permutation child = new Permutation(permutation1);

		
		int indexChild = 0;
		List<Integer> genesToAdd =new ArrayList<Integer>();
		genesToAdd.add(permutation1.getChromosome()[0]);

		while (indexChild < 511) {
			for (int j = 0; j < genesToAdd.size(); j++) {
				child.getChromosome()[indexChild] = genesToAdd.get(j);
				indexChild++;
				if (indexChild > 511) break;
			}
			
			int minimum = Integer.MAX_VALUE;
			List<Integer> minimums = new ArrayList<Integer>();
			for (int i = 0; i < 512; i++) {
				List<Integer> set = adjacencyMatrixResult.get(i);
				for (int j = 0; j < set.size(); j++) {
					set.removeAll(genesToAdd);
				}
				if (set.size() == minimum && set.size() > 0) {
					minimums.add(i);
					minimum = set.size();
				}
				else if (set.size() < minimum && set.size() > 0) {
					minimums = new ArrayList<Integer>();
					minimums.add(i);
					minimum = set.size();
				}
			}
			if (minimums.size()>0) {
				int randomMinimumIndex = rnd.nextInt(0, minimums.size());
				genesToAdd = adjacencyMatrixResult.get(minimums.get(randomMinimumIndex));	
			}
			else {
				genesToAdd = new ArrayList<Integer>();		
				while(genesToAdd.size()==0) {
					int randomMinimumIndex = rnd.nextInt(0, 511);
					try {
						genesToAdd = adjacencyMatrixResult.get(minimums.get(randomMinimumIndex));
					}
					catch(Exception ex) {
						System.out.println("Error in edge" + ex);
					}
				}
			}
		}

		return child;
	}
}