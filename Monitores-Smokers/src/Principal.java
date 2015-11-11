import java.util.Scanner;
import java.util.stream.Stream;

public class Principal {

	final int SMOKER_QTY = 3;
	final int AGENT_QTY = 1;
	
	Table table = new Table();
	
	Agent[] agents = new Agent[AGENT_QTY];
	Smoker[] smokers = new Smoker[SMOKER_QTY];
	
	Scanner kb = new Scanner(System.in);
	
	public void createThreads() {
		for(int i = 0; i < AGENT_QTY; i++) {
			agents[i] = new Agent(table);
		}
		for(int i = 0; i < SMOKER_QTY; i++) {
			smokers[i] = new Smoker(i, table);
		}
	}
	
	public void startThreads() {
		Stream.of(agents)
			.forEach(Agent::start);
		Stream.of(smokers)
			.forEach(Smoker::start);
	}
	
	public void stopThreads() {
		Stream.of(agents)
			.forEach(Agent::endTask);
		Stream.of(smokers)
			.forEach(Smoker::endTask);
	}
	
	public void exec() {
		createThreads();
		startThreads();
		kb.nextLine();
		stopThreads();
	}
	
	public static void main(String[] args) {
		Principal p = new Principal();
		p.exec();
	}

}
