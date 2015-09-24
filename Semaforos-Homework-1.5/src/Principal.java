import java.util.Scanner;

public class Principal {
	final int PART_TYPES = 3;
	
	PartBuffer[] buffers = new PartBuffer[PART_TYPES];
	SupplyRobot[] robots = new SupplyRobot[PART_TYPES];
	
	AssemblyRobot assemblyRobot;
	
	Scanner kb = new Scanner(System.in);
	
	
	public void initializeBuffers() {
		for(int i = 0; i < PART_TYPES; i++) {
			buffers[i] = new PartBuffer();
		}
	}
	public void initializeRobots() {
		for(int i = 0; i < PART_TYPES; i++) {
			robots[i] = new SupplyRobot(buffers[i], i);
			robots[i].setName("Supply Robot "+i);
		}
		assemblyRobot = new AssemblyRobot(buffers);
		assemblyRobot.setName("Assembly Robot");
	}
	public void startSupplyChain() {
		for(SupplyRobot robot : robots) {
			robot.start();
		}
		assemblyRobot.start();
	}
	public void stopSupplyChain() {
		for(SupplyRobot robot : robots) {
			robot.endTask();
		}
		assemblyRobot.endTask();
	}
	
	public void exec() {
		initializeBuffers();
		initializeRobots();
		startSupplyChain();
		kb.nextLine();
		stopSupplyChain();
	}
	
	public static void main(String[] args) {
		Principal p = new Principal();
		p.exec();
	}
}
