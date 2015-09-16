import java.util.Random;

public class Productor extends Thread{
	int id;
	volatile boolean end = false;
	volatile int val;
	Random rand = new Random();
	final int RAND_RANGE = 1000;
	Buffer buff;
	
	
	public Productor(int id, Buffer buff) {
		this.id = id;
		this.buff = buff;
	}
	@Override
	public void run() {
		while(!end) {
			val = rand.nextInt(RAND_RANGE);
			System.out.println("Productor "+id+" produce "+val);
			buff.put(val);
		}
	}
	public void finalizar() {
		this.end = true;
	}
}
