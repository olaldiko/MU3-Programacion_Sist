
public class Principal {
	public void ejecutar() {
		Thread hilo = new Thread( new HiloContador(10));
		hilo.start();
		System.out.println("Agur!!!");
	}
	public static void main(String[] args) {
		Principal p = new Principal();
		p.ejecutar();

	}

}
