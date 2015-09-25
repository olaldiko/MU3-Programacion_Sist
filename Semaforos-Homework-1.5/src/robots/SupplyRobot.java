package robots;
import buffers.PartBuffer;
import parts.Part;

public class SupplyRobot extends Thread {
	int partType;
	PartBuffer buffer;
	
	volatile boolean finalize = false;
	
	final int SUPPLY_TIME = 100;
	
	public SupplyRobot(PartBuffer buffer, int partType) {
		this.buffer = buffer;
		this.partType = partType;
	}
	
	@Override
	public void run() {
		while(!finalize) {
			try {
				Thread.sleep(SUPPLY_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buffer.putPart(new Part(partType));
			System.out.println("Supply robot puts a "+partType+" type part");
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
