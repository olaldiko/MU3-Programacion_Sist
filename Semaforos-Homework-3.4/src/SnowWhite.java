
public class SnowWhite extends Thread{

	final int SLEEP_TIME = 1000;
	
	volatile boolean finalize = false;
	DinningRoom dr;
	
	
	public SnowWhite(DinningRoom dr) {
		this.dr = dr;
	}
	
	@Override
	public void run() {
		while(!finalize) {
			if(!dr.serveDinner()) {
				try {
					System.out.println("SnowWhite sleeps");
					Thread.sleep(SLEEP_TIME);
				} catch (InterruptedException e) {}
			}
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
}
