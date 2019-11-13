//array based

public void removeFront(){
	for(int i=0; i<count-1;i++){
		storage[i]=storage[i+1];
	}
	count--;
}

//list based

public void removeFront(){
	head=head.next;
	head.prev=null;
	size--;
}

