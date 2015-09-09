
public class MiHilo extends Thread {
	volatile boolean saludar = true;
	@Override
	public void run() {
		while(saludar) {
		System.out.println("Hola, primera prueba de hilos");
		}
	}
	public void finalizar() {
		saludar = false;
		this.interrupt();
		
	}
	
}
