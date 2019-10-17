package lyl.test.demo20190911;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest1 implements Runnable{
	private volatile static int state = 0;
	final Lock l = new ReentrantLock();
	private String name;

	public ThreadTest1(String name){
		super();
		this.name = name;		
	}
	
	@Override
	public void run() {
		while (state <= 40) {
			l.lock();
			if (state % 4 != 3 || name.equals("A")) {
				System.out.print(name);
				state++;
			}
			if (state % 4 == 3 || name.equals("B")) {
				System.out.print(name);
				state++;
			}
			l.unlock();
			
		}
	}
	
	public static void main(String[] args) {
		Runnable a = new ThreadTest1("A");
		Runnable b =  new ThreadTest1("B");
		new Thread(a).start();
		new Thread(b).start();
		
	
	}
	

//
//	public static void main(String[] args) {
//		final Lock l = new ReentrantLock();
//
//		Thread A = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (state <= 40) {
//					l.lock();
//					if (state % 4 != 3) {
//						System.out.print("A");
//						state++;
//					}
//					l.unlock();
//				}
//			}
//		});
//		Thread B = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (state <= 30) {
//					l.lock();
//					if (state % 4 == 3) {
//						System.out.print("B");
//						state++;
//					}
//					l.unlock();
//				}
//			}
//		});
//		Thread C = new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				while (state <= 30) {
//					l.lock();
//					if (state % 3 == 2) {
//						System.out.print("C");
//						state++;
//					}
//					l.unlock();
//				}
//			}
//		});
//		A.start();
//		B.start();
//		C.start();
//	}

}
