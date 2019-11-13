public class Node {
	String item;
	Node next;
	
	public Node(String item) {
		this.item = item;
		this.next = null;
	}
	
	public Node(String item, Node next) {
		this.item = item;
		this.next = next;
	}
	
	

	public static void main(String[] args) {
		Node a = new Node("7a");
		Node b = new Node("dd", a);
		Node c = new Node("a", b);
		Node d = new Node("23", c);
		Node e = new Node("2", d);
		Node head = e;
		Node cur;
		
		for(cur=head; cur != null; cur=cur.next) {
			System.out.println(cur.item + " ");
		}
		
		System.out.println();
		
		Node f = new Node("2");
		cur= head;
		int index= 3;
		for(int i=0; i<index-1; i++){
			cur=cur.next;
		}
		f.next= cur.next;
		cur.next= f;
		
		for(cur= head; cur != null; cur=cur.next) {
			System.out.println(cur.item + " ");
		}
		cur=head;
		index= 2;
		
		for(int i=0; i<index-1; i++){
			cur=cur.next;
		}
		cur.next=cur.next.next;
		
		System.out.println();
		
		for(cur= head; cur != null; cur=cur.next) {
			System.out.println(cur.item + " ");
		}
	}
	
}