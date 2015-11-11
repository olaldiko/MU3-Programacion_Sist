
public class Inspector extends Thread{

	int id;
	Parking parking;
	volatile boolean finalize = false;
	
	public Inspector(int id, Parking parking) {
		this.id = id;
		this.setName("Inspector "+id);
		this.parking = parking;
	}
	@Override
	public void run() {
		Car c;
		while(!finalize) {
			c = parking.getCarForInspection(this);
			c.passInspection();
			System.out.println(this.getName()+" checks car "+c.getName());
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
	
	public int getInspectorId() {
		return this.id;
	}
	
}
