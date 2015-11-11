
public abstract class Person extends Thread{
	final int timeInToilet = 200;
	int id;
	Toilet t;

	volatile boolean finalize = false;
	public Person(int id, Toilet t) {
		this.t = t;
		this.id = id;
	}
	@Override
	public void run() {
		while(!finalize) {
			t.enterToilet(this);
			System.out.println(this.toString()+" enters the toilet");
			try {
				sleep(timeInToilet);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			t.exitToilet(this);
			System.out.println(this.toString()+" exits the toilet");
		}
	}
	public void endTask() {
		this.finalize = true;
	}
	public String toString() {
		return "Person "+id;
	}
}
