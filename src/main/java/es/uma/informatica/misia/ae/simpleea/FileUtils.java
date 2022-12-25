package es.uma.informatica.misia.ae.simpleea;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class FileUtils {
	public static int[][] readMatrixFromFile(String file) {
		BufferedReader reader;
		List<String> partialResult = new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();

			while (line != null) {
				partialResult.add(line.trim());
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int matrix[][] = new int[partialResult.size()][];
		for (int i = 0; i < partialResult.size(); i++) {
			String[] split = partialResult.get(i).split("\\s+");
			matrix[i] = new int[split.length];
			for (int j = 0; j < split.length; j++) {
				try {
					matrix[i][j] = Integer.parseInt(split[j]);
				}
				catch(Exception ex) {
					System.err.println("Error reading matrix[" + i + "][" + j + "]");
				}
			}
		}

		return matrix;
	}
	
	public static void ConvertMatrixIntoImage(int[][] matrix, int[] order, String outputFile) throws Exception {
		int yLength = matrix[0].length;
		BufferedImage bufferedImage = new BufferedImage(matrix.length, yLength, BufferedImage.TYPE_BYTE_GRAY);
		
		for (int i = 0; i < matrix.length; i++) {
			int[] row = order == null ? matrix[i] : matrix[order[i]];
			for (int j = 0; j < row.length; j++) {
				int rgb = (int)row[j]<<16 | (int)row[j] << 8 | (int)row[j];
		        bufferedImage.setRGB(i, j, rgb);
			}
		}
		
		try {
			boolean result = ImageIO.write(bufferedImage, "png", new File(outputFile + System.currentTimeMillis() + ".png"));
			System.out.println(result);
		}
		catch(Exception ex) {
			System.out.print("There's been an error converting the int matrix into an image.");
			throw ex;
		}
	}
}