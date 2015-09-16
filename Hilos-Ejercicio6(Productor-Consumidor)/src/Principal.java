import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Principal {
	final int NUM_HILOS = 1;
	Buffer buff = new Buffer();
	Productor[] productores = new Productor[NUM_HILOS];
	Consumidor[] consumidores = new Consumidor[NUM_HILOS];
	Scanner teclado = new Scanner(System.in);
	
	Semaphore nuevo = new Semaphore(0);
	Semaphore leido = new Semaphore(1);
	
	public void crearHilos() {
		for(int i = 0; i < NUM_HILOS; i++) {
			productores[i] = new Productor(i, buff, nuevo, leido);
			consumidores[i] = new Consumidor((i + NUM_HILOS), buff, nuevo, leido);
		}
	}
	public void ejecutarHilos() {
		for(int i = 0; i < NUM_HILOS; i++) {
			productores[i].start();
			consumidores[i].start();
		}
	}
	public void terminarHilos() {
		for(int i = 0; i < NUM_HILOS; i++) {
			productores[i].finalizar();
			consumidores[i].finalizar();
		}
	}
	public void ejecutar() {
		crearHilos();
		ejecutarHilos();
		teclado.nextLine();
		terminarHilos();
	}
	public static void main(String[] args) {
		Principal p = new Principal();
		p.ejecutar();
	}
}
