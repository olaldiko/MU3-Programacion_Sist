package robots;
import buffers.CylinderBuffer;
import parts.Cylinder;

public class CylinderBuilderRobot extends Thread{

	volatile boolean finalize = false;
	
	CylinderBuffer buffer;

	final int BUILD_TIME = 200;
	
	public CylinderBuilderRobot(CylinderBuffer buffer) {
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
			buffer.putCylinder(new Cylinder());
			System.out.println("The robot puts a cylinder into the buffer");
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
