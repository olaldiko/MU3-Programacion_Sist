import java.util.ArrayList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Buffer {
	final int MAX_VALORES = 10;
	ArrayList<Integer> valores = new ArrayList<>();
	volatile int indiceEntrada = 0;
	volatile int indiceSalida = 0;
	volatile int valor;
	int numConsumidores;
	Semaphore lleno = new Semaphore(MAX_VALORES);
	Semaphore vacio = new Semaphore(0);
	Object candado = new Object();
	CyclicBarrier barrera;
	public Buffer(int numCons) { 
		this.numConsumidores = numCons; 
		this.barrera = new CyclicBarrier(numConsumidores);
		}
	
	public void put(int value) {
		try {
			lleno.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Buffer recibe valor "+value);
		synchronized(candado) {
			valores.add(value);
		}
		vacio.release();
	}
	public int get() {
		if(barrera.getNumberWaiting() == numConsumidores-1) {
			try {
				vacio.acquire();
				synchronized(candado) {
					valor = valores.remove(0);
					System.out.println("Buffer envia valor "+valor);
					lleno.release();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			barrera.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return valor;
	}
}
