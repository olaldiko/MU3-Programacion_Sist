
public class Barber extends Thread {
	
	int id;
	BarberShop bs;
	
	volatile boolean finalize = false;
	
	public Barber(int id, BarberShop bs) {
		this.id = id;
		this.bs = bs;
	}
	@Override
	public void run() {
		while(!finalize) {
			bs.cutHair();
		}
	}
	public void endTask() {
		this.finalize = true;
	}
}
