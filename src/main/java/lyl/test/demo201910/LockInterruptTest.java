package lyl.test.demo201910;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptTest {

	static class ThreadUtil extends Thread {
		private Thread thread;

		public ThreadUtil(Thread thread) {
			this.thread = thread;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			thread.interrupt();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final Lock lock = new ReentrantLock();
		lock.lock();
		Thread.sleep(1000);
		System.out.println("主线程获取到锁");
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				long start = System.currentTimeMillis();
				try {
					System.out.println(Thread.currentThread().getName() + "启动");
					lock.lockInterruptibly();
				} catch (Exception e) {
					System.out.println(Thread.currentThread().getName() + " interrupted");
				}
				long end = System.currentTimeMillis();
				long time = end - start;
				System.out.println(Thread.currentThread().getName() + "wait " + time);

			}
		});
		thread.start();
		new ThreadUtil(thread).start();
		System.out.println("主线程将进行10秒io操作");
		Thread.sleep(10000);
	}

}
