
public class Principal {
	final int ROWS = 100;
	final int COLS = 100;
	final int NUM_THREADS = 10;
	final int ROWS_THREAD = 10;
	final int BOUNDS = 10;
	int matrixA[][] = RandomMatrixFactory.generateMatrix(ROWS, COLS, BOUNDS);
	int matrixB[][] = RandomMatrixFactory.generateMatrix(ROWS, COLS, BOUNDS);
	int matrixC[][] = new int[ROWS][COLS];
	Multiplier[] threads = new Multiplier[NUM_THREADS];
	public void run() {
		startThreads();
		waitThreads();
		viewMatrix();
	}
	public void startThreads() {
		for(int i = 0; i < NUM_THREADS; i++) {
			threads[i] = new Multiplier((i * ROWS_THREAD), ROWS_THREAD, matrixA, matrixB, matrixC);
			threads[i].start();
		}
	}
	public void waitThreads() {
		for(int i = 0; i < NUM_THREADS; i++) {
			try{
			threads[i].join();
			}catch(Exception e){};
		}
	}
	public void viewMatrix() {
		for(int i = 0; i < ROWS; i++) {
			for(int j = 0; j < COLS; j++) {
				System.out.print(matrixC[i][j] + " , ");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Principal p = new Principal();
		p.run();

	}
}
