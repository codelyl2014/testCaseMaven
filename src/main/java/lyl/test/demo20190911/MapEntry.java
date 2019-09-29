package lyl.test.demo20190911;

import java.util.HashMap;
import java.util.Map;

public class MapEntry {
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aa", "aa");
		map.put("bb", "bb");
		map.entrySet();
		System.out.println(map);
		System.out.println(map.entrySet().isEmpty());
	}

}
