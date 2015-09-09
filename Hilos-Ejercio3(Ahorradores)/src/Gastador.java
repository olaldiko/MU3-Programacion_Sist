
public class Gastador extends Thread {
	int cantidad = 0;
	boolean acabar = false;
	Cuenta cuenta;
	public Gastador(int cantidad, Cuenta cuenta) {
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
