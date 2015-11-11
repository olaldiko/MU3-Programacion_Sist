
public class Consumer extends Thread {

	volatile boolean finalize = false;
	
	Buffer buffer;
	
	public Consumer(Buffer buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		while (!finalize) {
			System.out.println(this.getName()+" gets "+buffer.get(this));
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
