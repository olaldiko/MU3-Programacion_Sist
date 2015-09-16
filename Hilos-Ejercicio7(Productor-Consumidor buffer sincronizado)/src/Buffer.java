import java.util.concurrent.Semaphore;

public class Buffer {
	final int MAX_VALORES = 10;
	volatile int valores[] = new int[MAX_VALORES];
	volatile int indiceEntrada = 0;
	volatile int indiceSalida = 0;
	volatile int valor;
	Semaphore lleno = new Semaphore(MAX_VALORES);
	Semaphore vacio = new Semaphore(0);
	public void put(int value) {
		try {
			lleno.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Buffer recibe valor "+value);
		valores[indiceEntrada++ % MAX_VALORES] = value;
		vacio.release();
	}
	public int get() {
		try {
			vacio.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		valor = valores[indiceSalida++ % MAX_VALORES];
		System.out.println("Buffer envia valor "+valor);
		lleno.release();
		return valor;
	}
}
