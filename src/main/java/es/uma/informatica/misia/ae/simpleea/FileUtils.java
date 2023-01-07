package es.uma.informatica.misia.ae.simpleea;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

public class FileUtils {
	public static int[][] readMatrixFromFile(String file) {
		BufferedReader reader;
		List<String> partialResult = new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				partialResult.add(line.trim());
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

//		for (int i = 0; i < 512; i++) {
//		    for (int j = i + 1; j < 512; j++) {
//		        int temp = matrix[i][j];
//		        matrix[i][j] = matrix[j][i];
//		        matrix[j][i] = temp;
//		    }
//		}
		
		return matrix;
	}
	
	public static void ConvertMatrixIntoImage(int[][] matrix_, int[] order, String outputFile) throws Exception {
		int[][]matrix = new int[512][512];
		for (int i = 0; i < 512; i++) {
			matrix[i]=new int[512];
		    for (int j = 0; j < 512; j++) {
		    	matrix[i][j]=matrix_[i][j];
		    }
		  }
		int yLength = matrix[0].length;
		BufferedImage bufferedImage = new BufferedImage(matrix.length, yLength, BufferedImage.TYPE_BYTE_GRAY);
				
//		for (int i = 0; i < 512; i++) {
//		    for (int j = i + 1; j < 512; j++) {
//		        int temp = matrix[i][j];
//		        matrix[i][j] = matrix[j][i];
//		        matrix[j][i] = temp;
//		    }
//		}
		for (int i = 0; i < matrix.length; i++) {
			int[] row = matrix[order[i]];
			for (int j = 0; j < row.length; j++) {
				int color = row[j] * 0x00010101;
		        bufferedImage.setRGB(i, j, color);        
			}
		}

		
		try {
			boolean result = ImageIO.write(bufferedImage, "bmp", new File(outputFile + System.currentTimeMillis() + ".bmp"));
			System.out.println("Fitness = " + outputFile);
		}
		catch(Exception ex) {
			System.out.print("There's been an error converting the int matrix into an image.");
			throw ex;
		}
	}
}