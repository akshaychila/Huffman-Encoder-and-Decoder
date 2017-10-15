import java.util.*;


public class BinaryHeap {

    private BinaryHeapNode[] heap;
    private BinaryHeapNode hroot;
    int size;


    public BinaryHeap(FrequencyTable ft){
    	 this.hroot = build_tree_using_binary_heap(ft);
    }
    public BinaryHeap(){
   }

	public static class BinaryHeapNode{
	private int data;
	private long freq;
    BinaryHeapNode left;
    BinaryHeapNode right;

	public BinaryHeapNode(int data, long freq){
		this.data=data;
		this.freq = freq;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	

	}

	public void minheap(int index){
		int small = index;
		int left = (2*index) +1;
		int right = (2*index) +2;

		if((left<size) &&(heap[left].freq<heap[small].freq)){
			small=left;
		}
		if((right<size)&&(heap[right].freq<heap[small].freq)){
			small=right;
		}
		if(small!=index){
			BinaryHeapNode temp = heap[small];
			heap[small]=heap[index];
			heap[index]=temp;
			minheap(small);
		}

	}

	public BinaryHeapNode removeMin(){
		BinaryHeapNode min = heap[0];
		heap[0]=heap[size-1];
		size--;
		minheap(0);
		return min;
	}

	public void insert(BinaryHeapNode b){
		size++;
		int i=size-1;
		while((i>=0) && (b.freq<heap[(i-1)/2].freq)){
			heap[i] = heap[(i-1)/2];
			i=(i-1)/2;
		}
		heap[i] = b;
	}




	public BinaryHeapNode build_tree_using_binary_heap(FrequencyTable ft){
		long freq[] = ft.getFreq();
		BinaryHeapNode parent;
		BinaryHeapNode left;
		BinaryHeapNode right;
		heap= new BinaryHeapNode[freq.length];
        int j = 0;
		for(int i=0;i<freq.length;i++){
			if(freq[i]!=0)  {

				heap[j++]= new BinaryHeapNode(i,freq[i]);
			}
		}
		size = j;

		for(int i=(size-2)/2;i>=0;i--){
			minheap(i);
		}

		while(size!=1){
			left = removeMin();
			right = removeMin();

			parent = new BinaryHeapNode(-1,left.freq+right.freq);
			parent.left = left;
			parent.right = right;
			insert(parent);
		}
		return removeMin();

	}

	StringBuilder s=new StringBuilder();
	
	
	
	public void generateCode(){
		generateCode(hroot,"");
	}

	public void generateCode(BinaryHeapNode root,String st){
		if(root==null){
			return;
		}
		if(root.getData()!=-1){
			s.append(root.getData() +" "+st +"\n");
		}
		generateCode(root.left,st+"0");
		generateCode(root.right,st+"1");

	}

}
