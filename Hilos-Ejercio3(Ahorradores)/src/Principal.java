import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	final int AHORRADORES_NO = 1000;
	final int GASTADORES_NO = 1000;
	final int CANT_AHORRADORES = 1000;
	final int CANT_GASTADORES = 1000;
	Cuenta cuenta = new Cuenta();
	ArrayList<Ahorrador> ahorradores = new ArrayList<>();
	ArrayList<Gastador> gastadores = new ArrayList<>();
	Scanner teclado = new Scanner(System.in);
	public void ejecutar() {
		crearAhorradores();
		crearGastadores();
		for(int i = 0; i < 100; i ++){
			System.out.println("Saldo actual: "+cuenta.getSaldo());
		}
	}
	public void finalizar() {
		for(int i = 0; i < AHORRADORES_NO; i++) {
			ahorradores.get(i).setAcabar(true);
		}
		for(int i = 0; i < GASTADORES_NO; i++) {
			gastadores.get(i).setAcabar(true);
		}
		System.out.println("Saldo Final: "+cuenta.getSaldo());
	}
	public void crearAhorradores() {
		for(int i = 0; i < AHORRADORES_NO; i++) {
			ahorradores.add(new Ahorrador(CANT_AHORRADORES, cuenta));
			ahorradores.get(i).start();
		}
	}
	public void crearGastadores() {
		for(int i = 0; i < GASTADORES_NO; i++) {
			gastadores.add(new Gastador(CANT_GASTADORES, cuenta));
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
