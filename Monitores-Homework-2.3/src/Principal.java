import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Principal {

	final int SUPPLY_ROBOTS = 3;
	final int BUFFER_SIZE = 2;
	PartBuffer buffer = new PartBuffer(SUPPLY_ROBOTS, BUFFER_SIZE);
	
	AssemblyRobot assemblyRobot = new AssemblyRobot(buffer);
	SupplyRobot[] supplyRobots = new SupplyRobot[SUPPLY_ROBOTS];

	Scanner kb = new Scanner(System.in);
	
	public void createSupplyRobots() {
		IntStream
			.range(0, SUPPLY_ROBOTS)
			.forEach(i -> { 
				supplyRobots[i] = new SupplyRobot(buffer, i);
				supplyRobots[i].setName("SupplyRobot "+i);	
			}
		);
		assemblyRobot.setName("Assembly robot");
	}
	public void startAssemblyChain() {
		Stream.of(supplyRobots)
			.forEach(SupplyRobot::start);
		assemblyRobot.start();
	}
	
	public void stopAssemblyChain() {
		Stream.of(supplyRobots)
			.forEach( r -> {
				r.endTask();
				try {
					r.join();
				} catch (Exception e) {}
			});
		assemblyRobot.endTask();
	}
	public void exec() {
		createSupplyRobots();
		startAssemblyChain();
		kb.nextLine();
		stopAssemblyChain();
	}
	
	public static void main(String[] args) {
		Principal p = new Principal();
		p.exec();
	}
}
