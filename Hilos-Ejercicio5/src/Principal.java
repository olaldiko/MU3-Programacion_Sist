import java.util.concurrent.Semaphore;

public class Principal {
	final int NUM_HILOS = 100;
	final int CANTIDAD = 10;
	Semaphore barrera1 = new Semaphore(0);
	Semaphore barrera2 = new Semaphore(0);
	public void ejecutar() {
		for(int i = 0; i < NUM_HILOS; i++){
		Thread hilo = new Thread(new HiloContador(i, CANTIDAD, NUM_HILOS, barrera1, barrera2));
		hilo.start();
		
		}
	}
	public static void main(String[] args) {
		Principal p = new Principal();
		p.ejecutar();

	}

}
