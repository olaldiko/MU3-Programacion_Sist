import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class GeneradorNumeros extends Thread{
	int idHilo = 0;
	Random gen;
	static ArrayList<Integer> valores = new ArrayList<>();
	int numAGen = 0;
	static AtomicInteger numGenerados = new AtomicInteger(0);
	
	public int getIdHilo() {
		return idHilo;
	}

	public void setIdHilo(int idHilo) {
		this.idHilo = idHilo;
	}

	public Random getGen() {
		return gen;
	}

	public void setGen(Random gen) {
		this.gen = gen;
	}

	public static ArrayList<Integer> getValores() {
		return valores;
	}

	public static void setValores(ArrayList<Integer> valores) {
		GeneradorNumeros.valores = valores;
	}

	public int getNumAGen() {
		return numAGen;
	}

	public void setNumAGen(int numAGen) {
		this.numAGen = numAGen;
	}

	public static AtomicInteger getNumGenerados() {
		return numGenerados;
	}

	public static void setNumGenerados(AtomicInteger numGenerados) {
		GeneradorNumeros.numGenerados = numGenerados;
	}

	public GeneradorNumeros(int id, int cantidad) {
		this.idHilo = id;
		this.numAGen = cantidad;
		this.gen = new Random();
	}
	
	@Override
	public void run() {
		int valor = 0;
		while(numGenerados.get() < numAGen) {
			valor = gen.nextInt(100);
			if(!valores.contains(valor)) {
			valores.add(valor);
			numGenerados.incrementAndGet();
			System.out.println("Hilo "+idHilo+" genera numero: "+valor);
			}
		}
	}
}
