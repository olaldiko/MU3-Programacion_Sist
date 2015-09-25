package robots;
import buffers.BucketBuffer;
import buffers.CylinderBuffer;

public class AssemblerRobot extends Thread{
	
	volatile boolean finalize = false;
	BucketBuffer bucketBuffer;
	CylinderBuffer cylinderBuffer;
	
	final int BUILD_TIME = 300;
	
	public AssemblerRobot(BucketBuffer bucket, CylinderBuffer cylinder) {
		this.bucketBuffer = bucket;
		this.cylinderBuffer = cylinder;	
	}
	
	@Override
	public void run() {
		while(!finalize) {
			for(int i = 0; i < bucketBuffer.getCapacity(); i++) {
				bucketBuffer.getBucket();
				System.out.println("Assembler gets a bucket");
			}
			for(int i = 0; i < cylinderBuffer.getCapacity(); i++) {
				cylinderBuffer.getCylinder();
				System.out.println("Assembler gets a cylinder");
			}
			try {
				Thread.sleep(BUILD_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Assembler puts the cylinders into de buckets");
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
