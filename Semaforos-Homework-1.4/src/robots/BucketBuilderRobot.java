package robots;
import buffers.BucketBuffer;
import parts.Bucket;

public class BucketBuilderRobot extends Thread {
	
	volatile boolean finalize = false;
	
	BucketBuffer buffer;
	
	final int BUILD_TIME = 100;
	
	public BucketBuilderRobot(BucketBuffer buffer) {
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
			buffer.putBucket(new Bucket());
			System.out.println("Thre robot puts a bukcet into the buffer");
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
	
	
}
