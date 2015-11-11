
public class Dwarf extends Thread{
	volatile int chair = 0;
	volatile boolean finalize = false;
	DinningRoom dr;
	
	public Dwarf(DinningRoom dr) {
		this.dr = dr;
	}
	@Override
	public void run() {
		while (!finalize) {
			dr.seat(this);
			dr.eat(this);
			dr.leave(this);
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
	
	public void setChair(int chair) {
		this.chair = chair;
	}
	
	public int getChair() {
		return this.chair;
	}
	public String toString() {
		return this.getName();
	}
}
