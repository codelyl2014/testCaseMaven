package lyl.test.demo201910;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class ThreadTest {
	
	public static void main(String[] args) throws InterruptedException {
//		Thread thread = new MyThread();
//		thread.start();
//		Thread.sleep(100);
//		System.out.println(thread.getState());
//		Thread thread2 = new MyThread();
//		thread2.start();
//		Thread.sleep(1000);
//		System.out.println(thread.getState());
//		System.out.println(thread2.getState());
//		thread2.interrupt();
//		System.out.println(thread2.isInterrupted());
		Map<String, String> map = new HashMap<String, String>(15);
		System.out.println("map size：" + map.size());
		map.put("a", "1");
		map.put("b", "2");
		map.put("c", "3");
		Object object = map.put("a","3");
		System.out.print(object);
		for(Map.Entry<String, String> entry:map.entrySet()) {
			System.out.println(entry.getKey()+entry.getValue());			
		}

		List<String> list = new ArrayList<String>(10);
		System.out.println("list size：" + list.size());
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.sort(new Comparator<String>() {
			@Override
			public int compare(String a,String b) {
				return a.compareTo(b);
			}
		});
		for(int i = list.size() - 1; i >= 0;i--) {
			if(list.get(i).equals("a")) {
				list.remove("a");
			}
		}
		
		Iterator<String> iterable = list.iterator();
		while (iterable.hasNext()) {
			if(iterable.next().equals("b")) {
				iterable.remove();
			}			
		}
		
		System.out.println(new Gson().toJson(list));
	}

}
