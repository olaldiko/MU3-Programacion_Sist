import java.util.Scanner;

import Buffers.SleeveBuffer;
import Buffers.SweaterBodyBuffer;
import Builders.SleeveMaker;
import Builders.SweaterAssembler;
import Builders.SweaterBodyMaker;

public class Principal {
	final int NUM_SLEEVE_MAKERS = 1;
	final int NUM_BODY_MAKERS = 1;
	final int NUM_SWEATER_ASSEMBLERS = 1;
	
	SleeveBuffer sleeveBuffer = new SleeveBuffer();
	SweaterBodyBuffer bodyBuffer = new SweaterBodyBuffer();
	
	SleeveMaker[] sleeveMakers = new SleeveMaker[NUM_SLEEVE_MAKERS];
	SweaterBodyMaker[] bodyMakers = new SweaterBodyMaker[NUM_BODY_MAKERS];
	SweaterAssembler[] sweaterAssemblers = new SweaterAssembler[NUM_SWEATER_ASSEMBLERS];
	
	Scanner kb = new Scanner(System.in);
	public void createThreads() {
		for(int i = 0; i < NUM_SLEEVE_MAKERS; i++) {
			sleeveMakers[i] = new SleeveMaker(sleeveBuffer);
			sleeveMakers[i].setName("Sleeve maker "+i);
		}
		for(int i = 0; i < NUM_BODY_MAKERS; i++) {
			bodyMakers[i] = new SweaterBodyMaker(bodyBuffer);
			bodyMakers[i].setName("Sweater body maker "+i);
		}
		for(int i = 0; i < NUM_SWEATER_ASSEMBLERS; i++) {
			sweaterAssemblers[i] = new SweaterAssembler(sleeveBuffer, bodyBuffer);
			sweaterAssemblers[i].setName("Sweater assembler "+i);
		}
	}
	
	public void startThreads() {
		for(int i = 0; i < NUM_SLEEVE_MAKERS; i++) {
			sleeveMakers[i].start();
		}
		for(int i = 0; i < NUM_BODY_MAKERS; i++) {
			bodyMakers[i].start();
		}
		for(int i = 0; i < NUM_SWEATER_ASSEMBLERS; i++) {
			sweaterAssemblers[i].start();
		}
	}
	
	public void stopThreads() {
		for(int i = 0; i < NUM_SLEEVE_MAKERS; i++) {
			sleeveMakers[i].endTask();
			try {
				sleeveMakers[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i = 0; i < NUM_BODY_MAKERS; i++) {
			bodyMakers[i].endTask();
			try {
				bodyMakers[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i = 0; i < NUM_SWEATER_ASSEMBLERS; i++) {
			sweaterAssemblers[i].endTask();
			try {
				sweaterAssemblers[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
