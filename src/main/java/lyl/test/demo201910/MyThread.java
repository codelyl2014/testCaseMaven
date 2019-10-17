package lyl.test.demo201910;

public class MyThread extends Thread{

	public void run() {
		doSomething();
	}
	
	public synchronized static void doSomething() {
		while (true) {
			if(Thread.currentThread().isInterrupted()) {
				System.out.println("exit mythread");
				break;
			}			
		}
	}
}
