import java.util.Scanner;

public class Principal {
	final int NUM_PROD = 200;
	final int NUM_CONS = 60;
	Buffer buff = new Buffer(NUM_CONS);
	Productor[] productores = new Productor[NUM_PROD];
	Consumidor[] consumidores = new Consumidor[NUM_CONS];
	Scanner teclado = new Scanner(System.in);
	
	
	public void crearHilos() {
		crearProductores();
		crearConsumidores();
	}
	public void crearProductores() {
		for(int i = 0; i < NUM_PROD; i++) {
			productores[i] = new Productor(i, buff);
		}
	}
	public void crearConsumidores() {
		for(int i = 0; i < NUM_CONS; i++) {
			consumidores[i] = new Consumidor(i, buff);
		}
	}
	public void ejecutarHilos() {
		ejecutarProductores();
		ejecutarConsumidores();
	}
	public void ejecutarProductores() {
		for(int i = 0 ;i < NUM_PROD; i++) {
			productores[i].start();
		}
	}
	public void ejecutarConsumidores() {
		for(int i = 0; i < NUM_CONS; i++) {
			consumidores[i].start();
		}
	}
	public void terminarHilos() {
		terminarProductores();
		terminarConsumidores();
	}
	public void terminarProductores() {
		for(int i = 0; i < NUM_PROD; i++) {
			productores[i].finalizar();
		}
	}
	public void terminarConsumidores() {
		for(int i = 0; i < NUM_CONS; i++) {
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
