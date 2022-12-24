package es.uma.informatica.misia.ae.simpleea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
					int a = 1;
				}
			}
		}

		return matrix;
	}
}