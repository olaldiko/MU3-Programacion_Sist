import java.util.Random;
import java.util.concurrent.Semaphore;

public class Productor extends Thread{
	int id;
	volatile boolean end = false;
	volatile int val;
	Random rand = new Random();
	final int RAND_RANGE = 1000;
	Buffer buff;
	
	Semaphore nuevo, leido;
	
	public Productor(int id, Buffer buff, Semaphore nuevo, Semaphore leido) {
		this.id = id;
		this.buff = buff;
		this.nuevo = nuevo;
		this.leido = leido;
	}
	@Override
	public void run() {
		while(!end) {
			try {
				leido.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			val = rand.nextInt(RAND_RANGE);
			System.out.println("Productor "+id+" produce "+val);
			buff.put(val);
			nuevo.release();
		}
	}
	public void finalizar() {
		this.end = true;
	}
}
