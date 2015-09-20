
public class Counter extends Thread{
	volatile boolean finalize = false;
	volatile int counter = 0;
	@Override
	public void run() {
		while(!finalize) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		counter += 1;
		System.out.println(counter);
		}
	}
	public void endTask() {
		finalize = true;
	}
}
