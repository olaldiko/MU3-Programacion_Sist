import java.util.concurrent.Semaphore;

public class Consumidor extends Thread{
	int id;
	Buffer buff;
	Semaphore nuevo, leido;
	volatile boolean end = false;
	public Consumidor(int id, Buffer buff, Semaphore nuevo, Semaphore leido) {
		this.id = id;
		this.buff = buff;
		this.nuevo = nuevo;
		this.leido = leido;
	}
	
	@Override
	public void run() {
		while(!end) {
			try {
				nuevo.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				System.out.println("Consumidor "+id+" consume "+buff.get());
			leido.release();
		}
	}
	public void finalizar() {
		this.end = true;
	}
}
