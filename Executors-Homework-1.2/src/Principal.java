import java.util.Scanner;

public class Principal {
	
	String source;
	String dest;
	
	FileCopyPool pool = new FileCopyPool();
	
	Scanner kb = new Scanner(System.in);
	
	public void exec() {
		do {
			System.out.println("Please, enter the source path:");
			source = kb.nextLine();
		} while (!pool.setSourcePath(source));
		do {
			System.out.println("Please, enter the destination path:");
			dest = kb.nextLine();
		} while (!pool.setDestPath(dest));
		pool.copyFiles();
	}
	
	public static void main(String[] args) {
		Principal p = new Principal();
		p.exec();
	}
	
}
