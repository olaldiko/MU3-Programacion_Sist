import java.util.Random;

public class NumGeneratorThread extends Thread {
	Buffer b;
	Random rand = new Random();
	int id;
	final int RAND_BOUNDS = 10;
	volatile boolean finalize = false;
	public NumGeneratorThread(Buffer b, int id) {
		this.b = b;
		this.id = id;
	}
	@Override
	public void run() {
		while(!finalize) {
			b.addValue(id);
		}
	}
	public void endTask() {
		this.finalize = false;
	}
	
}
