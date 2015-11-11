import java.util.Random;

public class Producer extends Thread {

	final int RAND_BOUNDS = 100;
	volatile boolean finalize = false;
	
	Random r = new Random();
	Buffer buffer;
	
	public Producer(Buffer buffer) {
		this.buffer = buffer;
	}
	@Override
	public void run() {
		int value;
		while (!finalize) {
			value = r.nextInt(RAND_BOUNDS);
			buffer.put(value);
			System.out.println(this.getName() + " puts " + value);
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
	
}
