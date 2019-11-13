import java.util.NoSuchElementException;

public class MedListRefBased implements List<Medication> {

	private MedicationNode head;
	private MedicationNode tail;
	private int count;

	public MedListRefBased() {
		head = null;
		tail = null;
		count = 0;
	}
	
	public void add(Medication k,int index) {
			MedicationNode addNode= new MedicationNode(k);
			if(count==0){		//first element
				head= addNode;
				tail= addNode;
			} else if(count == index){
				tail.next = addNode;
				addNode.prev=tail;
				tail=tail.next;
			} else{
				
			}
			count++;
			
			//insert at end
	}

	public Medication get(int index) {
		return null;
	}

	public boolean isEmpty() {
		return false;
	}

	public int size() {
		return 0;
	}

	public int indexOf(Medication item) {
		return 0;
	}

	public void remove(int index) {	
	}

	public void remove(Medication item) {	
	}

	public void removeAll() {	
	}

	public String toString() {
		String details= "";
		MedicationNode temp= head;
		while(temp!=null){
			details= details + " " + temp.item;
			temp=temp.next;
		}
		return details;
	}

	public static void main(String[] args) {
		Medication a= new Medication("tyenol", 500);
		Medication b= new Medication("advil", 200);
		Medication c= new Medication("med3", 200);
		
		MedListRefBased test= new MedListRefBased();
		
		test.add(a,0);
		test.add(b,1);
		
//		test.add(c,1);
		System.out.println(test);
		
	}
}
