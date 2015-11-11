
import java.util.Random;

public class AssemblyRobot extends Thread {

	Random random = new Random();
	PartBuffer buffer;
	
	volatile boolean finalize = false;
	
	final int ASSEMBLY_TIME = 100;
	
	public AssemblyRobot(PartBuffer buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		int rand;
		while(!finalize) {
			try {
				rand = random.nextInt(buffer.getSize());
				buffer.getPart(rand);
				Thread.sleep(ASSEMBLY_TIME);
				System.out.println("Assembly robot gets a part from "+rand+" position");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
