import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PartBuffer {

	Lock[] buffers;
	Condition[] fullCond;
	Condition[] emptyCond;
	
	int[] partsInBuffer;
	int numBuffers;
	int maxParts;
	
	public PartBuffer(int numBuffers, int maxParts) {
		this.numBuffers = numBuffers;
		buffers = new Lock[numBuffers];
		partsInBuffer = new int[numBuffers];
		fullCond = new Condition[numBuffers];
		emptyCond = new Condition[numBuffers];
		this.maxParts = maxParts;
		for(int i = 0; i < numBuffers; i++) {
			buffers[i] = new ReentrantLock();
			fullCond[i] = buffers[i].newCondition();
			emptyCond[i] = buffers[i].newCondition();
			partsInBuffer[i] = 0;
		}
	}
	public int getSize() {
		return this.numBuffers;
	}
	public void putPart(int position) {
		if((position >= 0) && (position <= numBuffers)) {
			buffers[position].lock();
			if(partsInBuffer[position] < maxParts) {
				partsInBuffer[position]++;
				emptyCond[position].signal();
			} else {
				try {
					fullCond[position].await();
				} catch (InterruptedException e) {}
			}
			buffers[position].unlock();
		}
	}
	public void getPart(int position) {
		if((position >= 0) && (position <= numBuffers)) {
			buffers[position].lock();
			if(partsInBuffer[position] > 0) {
				partsInBuffer[position]--;
				fullCond[position].signal();
			} else {
				try {
					emptyCond[position].await();
				} catch (InterruptedException e) {}
			}
		}
		buffers[position].unlock();
	}
	
}
