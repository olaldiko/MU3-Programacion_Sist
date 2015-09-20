import java.util.concurrent.atomic.AtomicInteger;

public class Player {
	AtomicInteger lives = new AtomicInteger(5);
	
	public synchronized int addLife() {
		if((lives.get() > 0)&&(lives.get() < 10)) {
			return lives.incrementAndGet();
		} else {
			return -1;
		}
	}
	public synchronized int removeLife() {
		if((lives.get() > 0)&&(lives.get() < 10)) {
			return lives.decrementAndGet();
		} else {
			return -1;
		}
	}
	public int getLives() {
		return lives.get();
	}
}
