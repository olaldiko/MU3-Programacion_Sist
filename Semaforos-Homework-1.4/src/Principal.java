import java.util.Scanner;

public class Principal {
	
	BucketBuffer bucketBuffer = new BucketBuffer();
	CylinderBuffer cylinderBuffer = new CylinderBuffer();
	
	CylinderBuilderRobot robotA = new CylinderBuilderRobot(cylinderBuffer);
	BucketBuilderRobot robotB = new BucketBuilderRobot(bucketBuffer);
	
	AssemblerRobot robotC = new AssemblerRobot(bucketBuffer, cylinderBuffer);
	
	Scanner kb = new Scanner(System.in);
	
	public void setThreadNames() {
		robotA.setName("Cylinder Builder Robot");
		robotB.setName("Bucket Builder Robot");
		robotC.setName("Assembler Robot");		
	}
	
	public void startRobots() {
		this.robotA.start();
		this.robotB.start();
		this.robotC.start();
		System.out.println("Robots started");
	}
	
	public void stopRobots() {
		this.robotA.endTask();
		this.robotB.endTask();
		this.robotC.endTask();
		System.out.println("Robots stopped");
	}
	
	public void exec() {
		setThreadNames();
		startRobots();
		kb.nextLine();
		stopRobots();
	}
	
	public static void main(String[] args) {
		Principal p = new Principal();
		p.exec();
	}
}
