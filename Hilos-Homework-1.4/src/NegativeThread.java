
public class NegativeThread extends Thread {

	volatile boolean finalize = false;
	volatile Player player;
	public NegativeThread(Player player) {
		this.player = player;
	}
	@Override
	public void run() {
		int lives;
		while(!finalize) {
			lives = player.removeLife();
			if (lives == -1) {
				finalize = true;
			} else {
				System.out.println("Negative Thread removes 1 life, new lives: "+lives);
			}
		}
	}
}
