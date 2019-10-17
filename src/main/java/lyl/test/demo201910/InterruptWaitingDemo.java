package lyl.test.demo201910;

public class InterruptWaitingDemo extends Thread{
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				Thread.currentThread().interrupt();
			}			
		}
		System.out.println(isInterrupted());
	}
	
	public static void main(String[] args) {
		Thread thread = new InterruptWaitingDemo();
		thread.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
		}
		thread.interrupt();
	}

}
