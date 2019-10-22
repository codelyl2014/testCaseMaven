package lyl.test.demo201910;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountWithLock {
	
	private volatile static int count = 0;
	
	public static void main(String[] args) throws InterruptedException {
		
		CountDownLatch countDownLatch = new CountDownLatch(2);
		
		Lock lock = new ReentrantLock();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i =0;i <100000;i++) {
					lock.lock();
					count ++;
					lock.unlock();
				}
				countDownLatch.countDown();
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0;i<100000;i++) {
					lock.lock();
					count ++;
					lock.unlock();
				}
				countDownLatch.countDown();
			}
		}).start();
		
		countDownLatch.await();
		
		System.out.print(count);
		
	}

}
