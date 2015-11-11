
public class Dwarf extends Thread {

	int id;
	int seat;
	volatile boolean finalize = false;
	
	DinningRoom dr;
	
	public Dwarf(DinningRoom dr, int id) {
		this.dr = dr;
		this.id = id;
	}
	public int getSeat() {
		return seat;
	}
	
	public void setSeat(int seat) {
		this.seat = seat;
	}
	
	public int getDwarfId() {
		return this.id;
	}
	
	@Override
	public void run() {
		while (!finalize) {
			dr.takeSeat(this);
			dr.eat(this);
			dr.leave(this);
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
