import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class HiloContador implements Runnable {
	int id;
	int cantidad;
	CyclicBarrier barrera;
	public HiloContador(int id, int cantidad, CyclicBarrier barrera){
		this.cantidad = cantidad;
		this.id = id;
		this.barrera = barrera;
	}
	@Override
	public void run() {
		for( int i = 0; i < cantidad; i++){
			System.out.println("Hilo : "+id+" Numero: "+i);
		}
		try {
			barrera.await();
			System.out.println("Hilo: "+id+" Adios");
		} catch (BrokenBarrierException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}