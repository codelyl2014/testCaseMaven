package lyl.test.demo201910;

import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PointsTest {

	public static void points() {
		Point[] points = new Point[8];
		points[0] = new Point(1, 1);
		points[1] = new Point(2, 2);
		points[2] = new Point(3, 3);
		points[3] = new Point(4, 4);
		points[4] = new Point(2, 3);
		points[5] = new Point(3, 1);
		points[6] = new Point(3, 2);
		points[7] = new Point(1, 2);

		HashMap<Point, Integer> map = new HashMap<>();
		// 在坐标系上划出来，明显是最多4个点
		// k : 斜率
		for (int i = 0; i < points.length; i++) {
			Point current = points[i];
			HashMap<Float, Integer> mapKofPoint = new HashMap<>();
			int vertical = 0;// 垂直的，无k
			for (int j = 0; j < points.length; j++) {
				Point next = points[j];

				if (current.x == next.x && current.y == next.y) {
					// self
				} else if (current.x == next.x) {
					// 无k
					vertical++;
				} else {
					float newK = (1f * (next.y - current.y)) / (next.x - current.x);
					if (mapKofPoint.containsKey(newK)) {
						mapKofPoint.put(newK, mapKofPoint.get(newK) + 1);
					} else {
						mapKofPoint.put(newK, 1);
					}
				}
			}
			int result = 0;
			Iterator<Map.Entry<Float, Integer>> entryIterator = mapKofPoint.entrySet().iterator();
			while (entryIterator.hasNext()) {
				Map.Entry<Float, Integer> entry = entryIterator.next();
				if (entry.getValue() > result) {
					result = entry.getValue();
				}

			}
			// 加1是因为需要算上自己这个点
			result = Math.max(result, vertical) + 1;
			map.put(current, result);
		}
		Iterator<Map.Entry<Point, Integer>> entryIterator = map.entrySet().iterator();
		while (entryIterator.hasNext()) {
			Map.Entry<Point, Integer> entry = entryIterator.next();
			System.out.println("testPoints--key=" + entry.getKey() + "--num=" + entry.getValue());
		}
	}
	
	public static void main(String[] args) {
		points();
	}

}
