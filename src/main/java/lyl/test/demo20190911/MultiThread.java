package lyl.test.demo20190911;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @author loong
 *
 */
public class MultiThread {
	
	private static Lock lock = new ReentrantLock();
	
	private static Condition one = lock.newCondition();
	
	private static Condition two = lock.newCondition();
	
	public static ExecutorService executorService = Executors.newCachedThreadPool();
	
	public static void main(String[] args) throws InterruptedException {
//		Executors.newSingleThreadExecutor().execute(()->{
//			for(int i = 1; i < 1000 ; i+=2) {
//				lock.lock();
//				try {
//					two.signal();
//					System.out.println("当前：" + i);
//					one.await();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				lock.unlock();
//			}
//		});
//		
//		Executors.newSingleThreadExecutor().execute(()->{
//			for(int i = 1; i <= 1000 ; i+=2) {
//				lock.lock();
//				try {
//					one.signal();
//					System.out.println("当前：" + i);
//					two.await();
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				lock.unlock();
//			}
//		});
		
		Thread thread1 = new Thread(()->{
			for(int i = 1; i < 1000 ; i+=2) {
				lock.lock();
				try {
					two.signal();
					System.out.println("当前：" + i);
					one.await();
				} catch (InterruptedException e) {
					
				}
				lock.unlock();
			}
		}) ;
		
		Thread thread2 = new Thread(()->{
			for(int i = 2; i <= 1000 ; i+=2) {
				lock.lock();
				try {
					one.signal();
					System.out.println("当前：" + i);
					two.await();
				} catch (InterruptedException e) {
					
				}
				lock.unlock();
			}
		}) ;
		
		thread1.setPriority(Thread.MAX_PRIORITY);
		thread1.start();
		thread2.start();
	}

}
