
public class Smoker extends Thread {

	int id;
	int type;
	volatile boolean finalize = false;
	
	Table table;
	
	public Smoker(int id, Table table) {
		this.id = id;
		this.setName("Smoker "+id);
		this.type = id % 3;
		this.table = table;
	}
	
	@Override
	public void run() {
		while(!finalize) {
			try {
				table.smoke(type);
				System.out.println(this.getName()+" smokes");
				table.endSmoking();
				System.out.println(this.getName()+" stops smoking");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void endTask() {
		this.finalize = true;
	}
	
}
