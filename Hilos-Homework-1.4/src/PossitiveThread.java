
public class PossitiveThread extends Thread{
	volatile boolean finalize = false;
	volatile Player player;
	public PossitiveThread(Player player) {
		this.player = player;
	}
	@Override
	public void run() {
		int lives;
		while(!finalize) {
			lives = player.addLife();
			if (lives == -1) {
				finalize = true;
			} else {
				System.out.println("Possitive Thread adds 1 life, new lives: "+lives);
			}
		}
	}
}
