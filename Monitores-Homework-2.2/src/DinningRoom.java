import java.util.stream.IntStream;

public class DinningRoom {
	
	int numSeats;
	volatile int avaibleSeats;
	boolean[] dwarfInChair;
	boolean[] dinnerServed;
	Object avaibleSeatsLock = new Object();
	Object dinnerServedLock[];
	Object swCallLock = new Object();
	Object mutex = new Object();
	
	public DinningRoom(int numSeats) {
		this.numSeats = numSeats;
		this.avaibleSeats = numSeats;
		dwarfInChair = new boolean[numSeats];
		dinnerServed = new boolean[numSeats];
		dinnerServedLock = new Object[numSeats];
		IntStream.range(0, numSeats).forEach(i -> {
			dwarfInChair[i] = false;
			dinnerServed[i] = false;
			dinnerServedLock[i] = new Object();
		});
	}
	public void takeSeat(Dwarf d) {
		int seat = -1;
		synchronized (avaibleSeatsLock) {
			while(avaibleSeats == 0) {
				try {
					avaibleSeatsLock.wait();
				} catch (InterruptedException e) {}		
			}
			for(int i = 0; i < numSeats; i++) {
				if(!dwarfInChair[i]) {
					seat = i;
					dwarfInChair[i] = true;
					System.out.println("Dwarf "+d.getDwarfId()+" gets seat in chair no. "+i);
					break;
				}
			}
		avaibleSeats--;
		}
		synchronized (swCallLock) {
			swCallLock.notify();
		}
		d.setSeat(seat);
		
	}
	
	public void eat(Dwarf d) {
		synchronized (dinnerServedLock[d.getSeat()]) {			
			while(!dinnerServed[d.getSeat()]) {
				try {
					dinnerServedLock[d.getSeat()].wait();
				} catch (InterruptedException e) {}
			}
			System.out.println("Dwarf "+d.getDwarfId()+" eats");
		}
	}
	
	public void leave(Dwarf d) {
		synchronized (avaibleSeatsLock) {
			dwarfInChair[d.getSeat()] = false;
			synchronized (dinnerServedLock[d.getSeat()]) {
				dinnerServed[d.getSeat()] = false;
			}
			System.out.println("Dwarf "+d.getDwarfId()+" leaves chair "+d.getSeat());
			avaibleSeats++;
		}
	}
	
	public void serveDinner() {
		synchronized (swCallLock) {
			for(int i = 0; i < numSeats; i++) {
				if(dwarfInChair[i] && !dinnerServed[i]) {
					dinnerServed[i] = true;
					System.out.println("SnowWhite places dinner to dwarf in chair "+i);
					synchronized (dinnerServedLock[i]) {
						dinnerServedLock[i].notify();
					}
				}
			}
			try {
				if(avaibleSeats == numSeats) {
					swCallLock.wait();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
