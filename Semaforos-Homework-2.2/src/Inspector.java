
public class Inspector extends Thread {

	int id = 0;
	InspectionBank bank;
	volatile boolean finalize = false;
	
	final int INSPECTION_TIME = 2000;
	
	public Inspector(int id, InspectionBank bank) {
		this.id = id;
		this.bank = bank;
	}
			
	public void CheckCar() {
		Car c;
		c = bank.getCar();
		try {
			Thread.sleep(INSPECTION_TIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.setChecked();
		System.out.println("The inspector "+id+" checks "+c.toString());
	}
	
	@Override	
	public void run() {
		System.out.println("Inspector "+id+" goes to work");
		while(!finalize) {
			CheckCar();
		}
		System.out.println("Inspector "+id+" goes home");
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
