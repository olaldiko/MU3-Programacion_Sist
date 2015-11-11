
public class Client extends Thread {

	final int CUT_WAIT = 10000;
	final int UNCUT_WAIT = 0;
	int id;
	BarberShop bs;
	
	volatile boolean hasHairCut = false;
	volatile boolean finalize = false;	
	
	public Client(int id, BarberShop bs) {
		this.id = id;
		this.bs = bs;
	}
	
	@Override
	public void run() {
		while(!finalize) {
			if(bs.enterBarberShop(this)) {
				try {
					Thread.sleep(CUT_WAIT);
				} catch (InterruptedException e) {}
				setHairCut(false);
			} else {
				try {
					Thread.sleep(UNCUT_WAIT);
				} catch (InterruptedException e) {}
			}
		}
	}
	
	
	public boolean isHairCut() {
		return hasHairCut;
	}
	
	public void setHairCut(boolean cut) {
		this.hasHairCut = cut;
	}
	
	public void endTask() {
		this.finalize = true;
	}
	
	@Override
	public String toString() {
		return "Client "+id;
	}
	
}
