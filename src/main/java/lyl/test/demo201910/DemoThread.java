package lyl.test.demo201910;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DemoThread implements Runnable {

	final Lock locks = new ReentrantLock();

	private String name;

	public DemoThread(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		try {
			locks.lock();
			for (int i = 0; i < 5; i++) {
				System.out.println("线程" + name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			locks.unlock();
		}
	}

	public static void main(String[] args) {
		try {
			DemoThread A = new DemoThread("A");
			DemoThread B = new DemoThread("B");
			DemoThread C = new DemoThread("C");
			Thread threadA = new Thread(A);
			Thread threadB = new Thread(B);
			Thread threadC = new Thread(C);
			threadA.start();
			threadA.sleep(3);
			threadB.start();
			threadB.sleep(3);
			threadC.start();
			threadC.sleep(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
