import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	final int AHORRADORES_NO = 10;
	final int GASTADORES_NO = 10;
	final int CANT_AHORRADORES = 1;
	final int CANT_GASTADORES = 1;
	Cuenta cuenta = new Cuenta();
	ArrayList<Ahorrador> ahorradores = new ArrayList<>();
	ArrayList<Gastador> gastadores = new ArrayList<>();
	Scanner teclado = new Scanner(System.in);
	public void ejecutar() {
		crearAhorradores();
		crearGastadores();
		iniciarClientes();
		for(int i = 0; i < 100; i ++){
			System.out.println("Saldo actual: "+cuenta.getSaldo());
		}
	}
	public void finalizar() {
		for(int i = 0; i < AHORRADORES_NO; i++) {
			try {
				ahorradores.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i = 0; i < GASTADORES_NO; i++) {
			try {
				gastadores.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Saldo Final: "+cuenta.getSaldo());
	}
	public void crearAhorradores() {
		for(int i = 0; i < AHORRADORES_NO; i++) {
			ahorradores.add(new Ahorrador(CANT_AHORRADORES, cuenta));
		}
	}
	public void crearGastadores() {
		for(int i = 0; i < GASTADORES_NO; i++) {
			gastadores.add(new Gastador(CANT_GASTADORES, cuenta));
		}
	}
	public void iniciarClientes() {
		for(int i = 0; i < AHORRADORES_NO; i++) {
			ahorradores.get(i).start();
		}
		for(int i = 0; i < GASTADORES_NO; i++) {
			gastadores.get(i).start();
		}
	}
	public static void main(String[] args) {
		Principal p = new Principal();
		p.ejecutar();
		p.finalizar();
		System.exit(0);

	}

}
