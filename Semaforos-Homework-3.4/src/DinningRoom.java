import java.util.concurrent.Semaphore;

public class DinningRoom {

	final int NUM_CHAIRS = 4;
	Semaphore[] canEat = new Semaphore[NUM_CHAIRS];
	Semaphore[] canSeat = new Semaphore[NUM_CHAIRS];
	boolean[] dwarfInChair = new boolean[NUM_CHAIRS];
	Semaphore serveDinner = new Semaphore(0);
	Semaphore mutex = new Semaphore(1);
	Semaphore freeSeats = new Semaphore(NUM_CHAIRS);
	
	public DinningRoom() {
		for(int i = 0 ; i < NUM_CHAIRS; i++) {
			canEat[i] = new Semaphore(0);
			canSeat[i] = new Semaphore(1);
			dwarfInChair[i] = false;
		}
	}
	
	public void seat(Dwarf d) {
		int seat = -1;
		try {
			freeSeats.acquire();
			mutex.acquire();
			for(int i = 0;i < NUM_CHAIRS && seat == -1; i++) {
				 if(canSeat[i].tryAcquire()) {
					 seat = i;
				 }
			}
			d.setChair(seat);
			dwarfInChair[seat] = true;
			serveDinner.release();
			System.out.println(d.toString()+" takes a seat and calls SnowWhite");
		} catch (InterruptedException e) {}
		mutex.release();
	}
	
	public void eat(Dwarf d) {
		try {
			canEat[d.getChair()].acquire();
			System.out.println(d.toString()+"Starts to eat");
		} catch (InterruptedException e) {}
	}
	public void leave(Dwarf d) {
		try {
		mutex.acquire();
		dwarfInChair[d.getChair()] = false;
		canSeat[d.getChair()].release();
		mutex.release();
		freeSeats.release();
		System.out.println(d.toString()+" leaves");
		} catch (InterruptedException e){};
	}
	public boolean serveDinner() {
		if(serveDinner.tryAcquire()) {
			try{
				mutex.acquire();
				for(int i = 0; i < NUM_CHAIRS; i++) {
					if(dwarfInChair[i] == true) {
						canEat[i].release();
					}
				}	
			} catch (InterruptedException e) {}
			mutex.release();
			return true;
		} else {
			return false;
		}
	}
}