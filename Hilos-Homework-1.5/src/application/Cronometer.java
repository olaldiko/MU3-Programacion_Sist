package application;

import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Cronometer extends Thread{
	IntegerProperty msec = new SimpleIntegerProperty(0);
	IntegerProperty sec  = new SimpleIntegerProperty(0);
	IntegerProperty min = new SimpleIntegerProperty(0);
	IntegerProperty hour = new SimpleIntegerProperty(0);
	volatile boolean finalize = false;
	
	public Cronometer(InvalidationListener controller) {
		this.msec.addListener((InvalidationListener)controller);
	}
	@Override
	public void run() {
		while(!finalize) {
			msec.set(msec.get() + 10);
			if(msec.get() == 1000) {
				msec.set(0);
				sec.set(sec.get() + 1);
				if(sec.get() == 60) {
					sec.set(0);
					min.set(min.get() + 1);
					if(min.get() == 60) {
						min.set(0);
						hour.set(hour.get() + 1);
					}
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void stopCount() {
		this.finalize = true;
	}
}
