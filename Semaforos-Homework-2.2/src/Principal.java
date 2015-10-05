import java.util.Scanner;

public class Principal {

	Parking parking = new Parking();
	CarMaker carMaker = new CarMaker(parking);
	
	Scanner kb = new Scanner(System.in);
	public void initializeCarInspectionSys() {
		parking.startInspections();
		carMaker.start();
	}
	
	public void endInspectionSys() {
		carMaker.endTask();
		parking.stopInspections();
	}
	public void exec() {
		initializeCarInspectionSys();
		kb.nextLine();
		endInspectionSys();
	}
	
	public static void main(String[] args) {
		Principal p = new Principal();
		p.exec();
	}
}
