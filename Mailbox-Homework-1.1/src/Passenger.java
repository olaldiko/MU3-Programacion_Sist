
public class Passenger extends Thread {

	volatile boolean finalize = false;
	int id;
	int boardZone;
	BoardingZone zone;
	
	public Passenger(BoardingZone zone, int id) {
		this.zone = zone;
		this.id = id;
		this.setName("Passenger "+id);
	}
	
	@Override
	public void run() {
		while (!finalize) {
			zone.enterZone(this);
			System.out.println(this.getName()+" enters the boarding zone");
			boardZone = zone.enterBoardingZone(this);
			zone.leaveZone(this);
			System.out.println(this.getName()+" boards a taxi and leaves");
		}
		
		
	}
	public int getBoardZone() {
		return this.boardZone;
	}
	public void endTask() {
		this.finalize = true;
	}
	
}
