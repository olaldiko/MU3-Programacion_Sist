import java.util.ArrayList;

public class Principal {
	final int NUM_HILOS = 10;
	final int NUM_A_GENERAR = 50;
	ArrayList<GeneradorNumeros> generadores = new ArrayList<>();
	public void ejecutar() {
		crearHilos();
		finalizarHilos();
		mostrarResultado();
	}
	public static void main(String[] args) {
		Principal p = new Principal();
		p.ejecutar();
	}
	public void crearHilos() {
		for(int i = 0 ; i < NUM_HILOS; i++) {
			generadores.add(new GeneradorNumeros(i, NUM_A_GENERAR));
			generadores.get(i).start();
			
		}
	}
	public void finalizarHilos(){
		for(int i = 0; i < NUM_HILOS; i++){
			try {
				generadores.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void mostrarResultado() {
		int num = GeneradorNumeros.getNumGenerados().get();
		ArrayList<Integer> valores = GeneradorNumeros.getValores();
		for(int i = 0; i < num; i++){
				System.out.println("Numeros: "+valores.get(i));
			}
		System.out.println("Cantidad de numeros generados: "+num);
	}
}
