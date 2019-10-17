package lyl.test.demo201910;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

public class Sub extends Base{
	
	private String baseName = "sub";
	
	public void callName() {
		System.out.print(baseName);
	}
	
	public static void main(String[] args) {
		Base base = new Sub();
		String message = " name=wanggerxiao age=24 classNo=070497";
		stringSpilt(message);
		System.out.print(findFirstNoRepeatChar("googlele"));
		
		String test1 = "abc";
		StringBuffer test2 = new StringBuffer("cba");
		test2.append(test1);
		test1 = test2.toString();
		String aString = test1.concat("bac");
		System.out.print(aString);
	}
	
	
	public static void stringSpilt(String message) {
		String[] tem = message.split("=");
		message = tem[1]+tem[2]+tem[3];	
		tem = message.split(" ");
		System.out.println(tem[0] + " " + tem[1].substring(3) + " " + tem[2].substring(7) );
	}
	
	public static char findFirstNoRepeatChar(String str) {
		char[] array = str.toCharArray();
		Map<Character,Integer> map = new HashMap<Character, Integer>();
		for(char c:array) {
			if(map.containsKey(c)) {
				map.put(c, map.get(c)+1);
			} else {
				map.put(c, 1);
			}
		}
		for(char c:array) {
			if(map.get(c) == 1) {
				return c;
			} 	
		}
		return '#';
	}

}
