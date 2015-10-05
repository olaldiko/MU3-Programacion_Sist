public class Car extends Thread {

	int id;
	Direction direction;
	Bridge bridge;
	
	
	public Car(int id, Direction direction, Bridge bridge) {
		this.id = id;
		this.direction = direction;
		this.bridge = bridge;
	}
	
	@Override
	public void run() {
		bridge.enterBridge(this);
		System.out.println("Car "+id+" enters bridge in "+direction+" direction");
		try {
			Thread.sleep(1100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bridge.exitBridge(this);
		System.out.println("Car "+id+" exits the bridge in "+direction+" direction");
	}

	public int getIdCar() {
		return id;
	}

	public Direction getDirection() {
		return direction;
	}

}
