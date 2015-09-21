package Buffers;
import java.util.Vector;
import java.util.concurrent.Semaphore;

import Pieces.SweaterBody;

public class SweaterBodyBuffer {
	final int MAX_PIECES = 10;
	
	Vector<SweaterBody> buffer = new Vector<>();
		
	Semaphore full = new Semaphore(MAX_PIECES);
	Semaphore empty = new Semaphore(0);
	
	Object lock = new Object();
	
	public void putSweaterBody(SweaterBody b) {
		try {
			full.acquire();
			synchronized(lock) {
				buffer.addElement(b);
			}
			empty.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SweaterBody getSweaterBody() {
		SweaterBody s = null;
		try {
			empty.acquire();
			synchronized(lock) {
				s = buffer.remove(0);
			}
			full.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return s;
	}

}
