
public class Multiplier extends Thread {
	volatile int initRow;
	volatile int numRows;
	volatile int[][] matrixA;
	volatile int[][] matrixB;
	volatile int[][] matrixC;
	static Object candado = new Object();
	public Multiplier(int initRow, int numRows, int[][] matrixA, int[][] matrixB, int[][] matrixC) {
		this.initRow = initRow;
		this.numRows = numRows;
		this.matrixA = matrixA;
		this.matrixB = matrixB;
		this.matrixC = matrixC;
	}
	@Override
	public void run() {
		int result = 0;
		for(int i = initRow; i < (initRow + numRows); i++){
			for(int j = 0; j < matrixB[0].length; j++) {
				for(int k = 0; k < matrixB.length; k++) {
					result += matrixA[i][k]*matrixB[k][j];
				}
				synchronized(candado) {
					matrixC[i][j] = result;
				}
				result = 0;
			}
		}
	}

}
