package lyl.test.demo201910;

public class InterruptRunnableDemo extends Thread{
	
	@Override
	public void run() {
		while (!Thread.interrupted()) {
			System.out.println("run ....");
		}
		System.out.println("Exit normal");
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new InterruptRunnableDemo();
		thread.start();
		Thread.sleep(1000);
		thread.interrupt();
	}
	

}
