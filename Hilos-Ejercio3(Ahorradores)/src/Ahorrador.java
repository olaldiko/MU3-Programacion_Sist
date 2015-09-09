
public class Ahorrador extends Thread {
	int cantidad = 0;
	Cuenta cuenta;
	boolean acabar = false;
	public Ahorrador(int cantidad, Cuenta cuenta) {
		this.cantidad = cantidad;
		this.cuenta = cuenta;
	}
	
	@Override
	public void run() {
		while(!acabar) {
			cuenta.meterDinero(cantidad);
		}
	}
	public void setAcabar(boolean acabar) {
		this.acabar = acabar;
	}
}
