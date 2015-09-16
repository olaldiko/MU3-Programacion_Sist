import java.util.concurrent.CyclicBarrier;

public class Principal {
	final int NUM_HILOS = 1000;
	final int CANTIDAD = 1000;
	CyclicBarrier barrera = new CyclicBarrier(NUM_HILOS);
	public void ejecutar() {
		for(int i = 0; i < NUM_HILOS; i++){
		Thread hilo = new Thread(new HiloContador(i, CANTIDAD, barrera));
		hilo.start();
		
		}
	}
	public static void main(String[] args) {
		Principal p = new Principal();
		p.ejecutar();

	}

}
