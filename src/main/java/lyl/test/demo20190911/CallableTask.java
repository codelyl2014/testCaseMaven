package lyl.test.demo20190911;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableTask {
	
//	static class CallableTaskA implements Callable<String>{
//		@Override
//		public String call() {
//			// method
//			return "返回值";
//		}
//		
//		public static CallableTaskA function() {
//			return new CallableTaskA();
//		}
//		
//	}
	


	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Callable<String> myCallable = new Callable<String>() {
			@Override
			public String call() throws InterruptedException {
//				Thread.sleep(1000);
				return "call 返回值";
			}
		};
		
		new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
		};
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		Future<String> future = executor.submit(myCallable);
		while (future.isDone()) {
			System.out.println(future.get());
			
		}				
		
	}

}
