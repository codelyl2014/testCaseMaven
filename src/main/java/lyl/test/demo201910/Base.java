package lyl.test.demo201910;

public class Base {
	
	private String baseName = "base";
	
	public Base() {
		callName();
	}

	public void callName() {
		System.out.println(baseName);
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		Thread threadA = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("线程A");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread threadB = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("执行线程B。。。。。");
				
			}
		});
		threadA.start();
		threadA.join();
		threadB.start();
//		threadB.join();


	}
}
