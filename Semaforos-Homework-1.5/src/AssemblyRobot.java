import java.util.Random;

public class AssemblyRobot extends Thread {

	Random random = new Random();
	PartBuffer[] buffers;
	
	volatile boolean finalize = false;
	
	final int ASSEMBLY_TIME = 100;
	
	public AssemblyRobot(PartBuffer[] buffers) {
		this.buffers = buffers;
	}
	
	@Override
	public void run() {
		Part p;
		while(!finalize) {
			try {
				Thread.sleep(ASSEMBLY_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p = buffers[random.nextInt(buffers.length)].getPart();
			System.out.println("Assembly robot gets part of type "+p.type);
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
