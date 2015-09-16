
public class Gastador extends Thread {
	int cantidad = 0;
	boolean acabar = false;
	final int NUM_VECES = 100;
	Cuenta cuenta;
	public Gastador(int cantidad, Cuenta cuenta) {
		this.cantidad = cantidad;
		this.cuenta = cuenta;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < NUM_VECES; i++) {
			cuenta.sacarDinero(cantidad);
		}
	}
	public void setAcabar(boolean acabar) {
		this.acabar = acabar;
	}
}
