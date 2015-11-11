import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Table {

	Lock mutex = new ReentrantLock();
	Condition canSmokeCond = mutex.newCondition();
	Condition someoneSmokingCond = mutex.newCondition();
	
	boolean ingredientsInTable = false;
	boolean someoneSmoking = false;
	
	boolean tobaccoInTable = false;
	boolean paperInTable = false;
	boolean matchesInTable = false;
	
	public void putIngredients() throws InterruptedException {
		Random rand = new Random();
		mutex.lock();
		while(someoneSmoking || ingredientsInTable) {
			someoneSmokingCond.await();
		}
		switch(rand.nextInt(3)) {
		case 0:
			tobaccoInTable = true;
			paperInTable = true;
			matchesInTable = false;
			break;
		case 1:
			tobaccoInTable = false;
			paperInTable = true;
			matchesInTable = true;
			break;
		case 2:
			tobaccoInTable = true;
			paperInTable = false;
			matchesInTable = true;
			break;
		}
		ingredientsInTable = true;
		System.out.println("Agents puts ingredients on table, Tobbaco: "+tobaccoInTable+" Paper: "+paperInTable+" Matches: "+matchesInTable);
		canSmokeCond.signalAll();
		mutex.unlock();
	}
	public void smoke(int smokerType) throws InterruptedException {
		mutex.lock();
		while(!ingredientsInTable) {
			canSmokeCond.await();
		}
		switch(smokerType) {
		case 0:
			while(!paperInTable || !matchesInTable) {
				canSmokeCond.await();
			}
			paperInTable = false;
			matchesInTable = false;
			break;
		case 1:
			while(!tobaccoInTable || !matchesInTable) {
				canSmokeCond.await();
			}
			tobaccoInTable = false;
			matchesInTable = false;
			break;
		case 2:
			while(!tobaccoInTable || !paperInTable) {
				canSmokeCond.await();
			}
			tobaccoInTable = false;
			paperInTable = false;
			break;
		}
		ingredientsInTable = false;
		someoneSmoking = true;
		mutex.unlock();
	}
	public void endSmoking() throws InterruptedException {
		mutex.lock();
		someoneSmoking = false;
		someoneSmokingCond.signalAll();
		mutex.unlock();
	}
	
}
