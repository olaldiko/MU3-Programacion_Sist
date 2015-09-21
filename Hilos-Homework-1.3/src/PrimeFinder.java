import java.util.Vector;

public class PrimeFinder extends Thread {
	volatile int start = 0;
	volatile int count = 0;
	volatile Vector<Integer> list;
	public PrimeFinder(int start, int count, Vector<Integer> list) {
		this.start = start;
		this.count = count;
		this.list = list;
	}
	@Override
	public void run() {
		for(int i = start; i < (start+count); i++) {
			if(isPrime(i)) {
				list.addElement(i);
				System.out.println(this.getName()+" Prime found: "+i);
			}
		}
		
	}
	boolean isPrime(int n) {
	    for(int i = 2; (2*i) < n;i++) {
	        if(n%i == 0)
	            return false;
	    }
	    return true;
	}
}
