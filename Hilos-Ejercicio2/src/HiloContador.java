
public class HiloContador implements Runnable {
	int cantidad;
	public HiloContador(int cantidad){
		this.cantidad = cantidad;
	}
	@Override
	public void run() {
		for( int i = 0; i < cantidad; i++){
			System.out.println(i);
		}
	}
}
