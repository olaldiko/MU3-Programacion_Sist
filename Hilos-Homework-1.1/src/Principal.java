import java.util.Scanner;

public class Principal {
	Counter c = new Counter();
	Scanner kb = new Scanner(System.in);
	public void exec() {
		System.out.println("Pulsa enter para contar");
		kb.nextLine();
		c.start();
		kb.nextLine();
		c.endTask();
	}
	public static void main(String[] args) {
		Principal p = new Principal();
		p.exec();
	}
}
