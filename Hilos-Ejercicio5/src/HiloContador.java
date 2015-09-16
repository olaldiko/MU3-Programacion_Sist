import java.util.concurrent.Semaphore;

public class HiloContador implements Runnable {
	int id;
	int cantidad;
	int numHilos;
	static int contador = 0;
	Semaphore barrera1;
	Semaphore barrera2;
	public HiloContador(int id, int cantidad, int numHilos, Semaphore barrera1, Semaphore barrera2){
		this.cantidad = cantidad;
		this.id = id;
		this.numHilos = numHilos;
		this.barrera1 = barrera1;
		this.barrera2 = barrera2;
	}
	@Override
	public void run() {
		for( int i = 0; i < cantidad; i++) {
				System.out.println("Hilo : "+id+" Numero: "+i);
				if(barrera1.getQueueLength() == numHilos-1) {
					System.out.println("----------------------------------------");
					barrera1.release(numHilos);
				}
				try {
					barrera1.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(barrera2.getQueueLength() == numHilos-1) {
					barrera2.release(numHilos);
				}
				try {
					barrera2.acquire();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		}
		System.out.println("Agur!");
	}
}