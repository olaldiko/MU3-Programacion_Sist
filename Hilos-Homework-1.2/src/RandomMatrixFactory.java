import java.util.Random;

public class RandomMatrixFactory {
	public static int[][] generateMatrix(int rows, int cols, int bounds) {
		int matrix[][] = new int[rows][cols];
		Random rand = new Random();
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				matrix[i][j] = rand.nextInt(bounds);
			}
		}
		return matrix;
	}
}
