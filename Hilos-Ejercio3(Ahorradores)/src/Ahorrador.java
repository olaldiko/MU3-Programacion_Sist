
public class Ahorrador extends Thread {
	int cantidad = 0;
	final int NUM_VECES = 100;
	Cuenta cuenta;
	boolean acabar = false;
	public Ahorrador(int cantidad, Cuenta cuenta) {
		this.cantidad = cantidad;
		this.cuenta = cuenta;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < NUM_VECES; i++){
			cuenta.meterDinero(cantidad);
		}
	}
}
