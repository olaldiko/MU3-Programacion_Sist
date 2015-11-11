
public class SupplyRobot extends Thread {
	int position;
	PartBuffer buffer;
	
	volatile boolean finalize = false;
	
	final int SUPPLY_TIME = 100;
	
	public SupplyRobot(PartBuffer buffer, int position) {
		this.buffer = buffer;
		this.position = position;
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
			buffer.putPart(position);
			System.out.println("Supply robot puts a part in "+position);
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
