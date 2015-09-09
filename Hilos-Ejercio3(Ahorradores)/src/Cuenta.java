import java.util.concurrent.Semaphore;

public class Cuenta {
	int saldo = 0;
	Semaphore sem = new Semaphore(1);
	public synchronized void meterDinero(int cantidad) {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saldo += cantidad;
		sem.release();
	}
	public synchronized void sacarDinero(int cantidad) {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		saldo -= cantidad;
		sem.release();
	}
	public int getSaldo() {
		return saldo;
	}
}
