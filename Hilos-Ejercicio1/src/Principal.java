import java.util.Scanner;


public class Principal {
	public void ejecutar(){
		MiHilo hilo = new MiHilo();
		hilo.start();
		esperaTeclado();
		hilo.finalizar();

	}
	public void esperaTeclado() {
		Scanner teclado = new Scanner(System.in);
		teclado.nextLine();
	}
	public static void main(String args[]) {
		Principal p = new Principal();
		p.ejecutar();
	}
}
