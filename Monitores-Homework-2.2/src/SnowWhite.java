
public class SnowWhite extends Thread{

	DinningRoom dr;
	volatile boolean finalize = false;
	
	public SnowWhite(DinningRoom dr) {
		this.dr = dr;
	}
	
	@Override
	public void run() {
		while(!finalize) {
			dr.serveDinner();
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
	
}
