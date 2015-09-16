import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Cuenta {
	AtomicInteger saldo = new AtomicInteger(0);
	Semaphore sem = new Semaphore(1);
	public void meterDinero(int cantidad) {
		try {
			sem.acquire();
			saldo.addAndGet(cantidad);
			sem.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void sacarDinero(int cantidad) {
		try {
			sem.acquire();
			saldo.addAndGet(-cantidad);
			sem.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getSaldo() {
		return saldo.get();
	}
}
