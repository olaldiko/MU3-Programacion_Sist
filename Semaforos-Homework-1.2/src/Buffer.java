import java.util.concurrent.Semaphore;

public class Buffer {
	volatile int index = 0;
	final int BUFFER_SIZE = 50;
	int[] buffer = new int[BUFFER_SIZE];
	Semaphore inUse = new Semaphore(1);
	public Buffer() {
		for(int i = 0; i < BUFFER_SIZE; i++) {
			this.buffer[i] = 0;
		}
	}
	public void addValue(int val) {
		try {
			inUse.acquire();
			buffer[index++ % BUFFER_SIZE] = val;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inUse.release();
	}
	public int[] getArray() {
		int[] bufferCopy = new int[4];
		try {
			inUse.acquire();
			bufferCopy = buffer.clone();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inUse.release();
		return bufferCopy;
	}
}
