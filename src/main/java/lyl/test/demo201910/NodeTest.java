package lyl.test.demo201910;

public class NodeTest {
	
	static class Node{
		String name;
		int index;
		Node next;
		public Node(String name,int index) {
			super();
			this.name = name;
			this.index = index;
		}
	}
	
	public static void main(String[] args) {
		Node a = new Node("a", 1);
		Node b = new Node("b", 2);
		Node c = new Node("c", 3);
		Node d = new Node("d", 4);
		Node e = new Node("e", 5);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		
		// **********************
		Node pre = null;
		Node head = a;
		Node next = head.next;
		
		while (next != null) {
			head.next = pre;
			pre = head;
			head = next;
			next = next.next;
		}
		head.next = pre;
		// **********************
		while (head != next) {
			System.out.println(head.name);
			head = head.next;
			
		}
	}

}
