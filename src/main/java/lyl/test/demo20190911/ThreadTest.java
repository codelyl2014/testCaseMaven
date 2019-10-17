package lyl.test.demo20190911;

import java.util.concurrent.Semaphore;

public class ThreadTest {

	private static Semaphore A = new Semaphore(1);
	private static Semaphore B = new Semaphore(1);
	private static Semaphore C = new Semaphore(1);

	static class ThreadA extends Thread {
		@Override
		public void run() {
			try {
				for (int i = 0; i < 10; i++) {
					A.acquire();
					System.out.println("A ");
					B.release();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static class ThreadB extends Thread {
		@Override
		public void run() {
			try {
				for (int i = 0; i < 10; i++) {
					B.acquire();
					System.out.println("B ");
					C.release();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static class ThreadC extends Thread {
		@Override
		public void run() {
			try {
				for (int i = 0; i < 10; i++) {
					C.acquire();
					System.out.println("C");
					A.release();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		B.acquire();
		C.acquire();
		new ThreadA().start();
		new ThreadB().start();
		new ThreadC().start();
	}

}
