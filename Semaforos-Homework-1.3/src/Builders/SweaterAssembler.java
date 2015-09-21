package Builders;

import Buffers.SleeveBuffer;
import Buffers.SweaterBodyBuffer;

public class SweaterAssembler extends Thread {
	
	final int BUILD_TIME = 300;
	
	volatile boolean finalize = false;
	
	SleeveBuffer sleeveBuffer;
	SweaterBodyBuffer bodyBuffer;	
	
	public SweaterAssembler(SleeveBuffer sleeveBuffer, SweaterBodyBuffer bodyBuffer) {
		this.sleeveBuffer = sleeveBuffer;
		this.bodyBuffer = bodyBuffer;
	}
	
	@Override
	public void run() {
		while(!finalize) {
			sleeveBuffer.getSleeve();
			sleeveBuffer.getSleeve();
			bodyBuffer.getSweaterBody();
			try {
				Thread.sleep(BUILD_TIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("New sweater assembled!");

		}
	}
	
	public void endTask() {
		finalize = true;
	}
}
