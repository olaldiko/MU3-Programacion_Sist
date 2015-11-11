import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CalculatorPool {

	final int SIMULTANEOUS_THREADS = 8;
	
	Vector<Calculation> calcs = new Vector<>();
	Vector<Future<Calculation>> results = new Vector<>();
	
	MathFileReader reader = new MathFileReader();
	
	ExecutorService pool = Executors.newFixedThreadPool(SIMULTANEOUS_THREADS);
	
	public void loadCalculations() {
		reader.readFile();
		calcs = reader.parseFile();
	}
	
	public void calculate() {
		for(Calculation c : calcs) {
			results.addElement(pool.submit(new CalculatorThread(c)));
			System.out.println(c+" added to thread pool");
		}
		pool.shutdown();
	}
	public void printResults() {
		for(Future<Calculation> f : results) {
			if(f.isDone()) {
				try {
					System.out.println(f.get()+" result--> "+f.get().getResult());
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
