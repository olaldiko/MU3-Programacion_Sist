public class Consumidor extends Thread{
	int id;
	Buffer buff;
	volatile boolean end = false;
	public Consumidor(int id, Buffer buff) {
		this.id = id;
		this.buff = buff;
	}
	
	@Override
	public void run() {
		while(!end) {
				System.out.println("Consumidor "+id+" consume "+buff.get());
		}
	}
	public void finalizar() {
		this.end = true;
	}
}
