
public class Principal {
	Player player = new Player();
	NegativeThread neg = new NegativeThread(player);
	PossitiveThread pos = new PossitiveThread(player);
	public void runThreads() {
		pos.start();
		neg.start();
	}
	public void waitThreads() {
		try {
			pos.join();
			neg.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void showResult() {
		if(player.getLives() == 10) {
			System.out.println("The player has won the game!");
		}else{
			System.out.println("The player has lost the game!");
		}
	}
	
	public static void main(String[] args) {
		Principal p = new Principal();
		p.runThreads();
		p.waitThreads();
		p.showResult();
	}
}
