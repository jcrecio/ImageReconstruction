package es.uma.informatica.misia.ae.simpleea;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class Main {

	public static void main (String args []) {
		if (args.length < 2) {
			// help();
			return;
		}
		
		if (args[0].equals("convert")) {
			try {
				int[][] matrix = FileUtils.readMatrixFromFile(args[1]);
				ConvertMatrixIntoImage(matrix, "output");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (args.length < 4) {
			// help();
			return;
		}
		
		int n = Integer.parseInt(args[3]);
		Problem problem = new Onemax(n);
		
		Map<String, Double> parameters = readEAParameters(args);
		EvolutionaryAlgorithm evolutionaryAlgorithm = new EvolutionaryAlgorithm(parameters, problem);
		
		Individual bestSolution = evolutionaryAlgorithm.run();
		System.out.println(bestSolution);
	}

	private static Map<String, Double> readEAParameters(String[] args) {
		Map<String, Double> parameters = new HashMap<>();
		parameters.put(EvolutionaryAlgorithm.POPULATION_SIZE_PARAM, Double.parseDouble(args[0]));
		parameters.put(EvolutionaryAlgorithm.MAX_FUNCTION_EVALUATIONS_PARAM, Double.parseDouble(args[1]));
		parameters.put(BitFlipMutation.BIT_FLIP_PROBABILITY_PARAM, Double.parseDouble(args[2]));
		
		long randomSeed = System.currentTimeMillis();
		if (args.length > 4) {
			randomSeed = Long.parseLong(args[4]);
		}
		parameters.put(EvolutionaryAlgorithm.RANDOM_SEED_PARAM, (double)randomSeed);
		return parameters;
	}
	
	private static void ConvertMatrixIntoImage(int[][] matrix, String outputFile) throws Exception {
		int xLenght = matrix.length;
		int yLength = matrix[0].length;
		BufferedImage bufferedImage = new BufferedImage(xLenght, yLength, BufferedImage.TYPE_BYTE_GRAY);

		for(int x = 0; x < xLenght; x++) {
		    for(int y = 0; y < yLength; y++) {
		        int rgb = (int)matrix[x][y]<<16 | (int)matrix[x][y] << 8 | (int)matrix[x][y];
		        bufferedImage.setRGB(x, y, rgb);
		    }
		}
		try {
			boolean result = ImageIO.write(bufferedImage, "png", new File("output _" + System.currentTimeMillis() + ".png"));
			System.out.println(result);
		}
		catch(Exception ex) {
			System.out.print("There's been an error converting the int matrix into an image.");
			throw ex;
		}
	}

}
