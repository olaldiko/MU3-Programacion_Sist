package builders;
import buffers.SweaterBodyBuffer;
import parts.SweaterBody;

public class SweaterBodyMaker extends Thread {
	
	final int BUILD_TIME = 250;
	
	volatile boolean finalize = false;
	
	SweaterBodyBuffer buffer;
	
	public SweaterBodyMaker(SweaterBodyBuffer buffer) {
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
			buffer.putSweaterBody(new SweaterBody());
			System.out.println("Sweater Body maker puts a new piece into the buffer");
		}
	}
	public void endTask() {
		this.finalize = true;
	}
}
