package builders;
import buffers.SleeveBuffer;
import parts.Sleeve;

public class SleeveMaker extends Thread {
	
	final int BUILD_TIME = 100;
	
	volatile boolean finalize = false;
	
	
	SleeveBuffer buffer;
	
	public SleeveMaker(SleeveBuffer buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		while(!finalize) {
			try {
				Thread.sleep(BUILD_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buffer.putSleeve(new Sleeve());
			System.out.println("Sleeve maker puts a new piece into the buffer");

		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
