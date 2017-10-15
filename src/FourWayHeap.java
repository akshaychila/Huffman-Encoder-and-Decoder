import java.util.*;


public class FourWayHeap {

private FourWayHeapNode []heap;
private FourWayHeapNode hroot;
int size;

    public FourWayHeap(FrequencyTable ft){
    	  hroot = build_tree_using_4way_heap(ft);
    }

	public static class FourWayHeapNode{
	private int data;
	private long freq;
	FourWayHeapNode[] child = new FourWayHeapNode[4];
	FourWayHeapNode hleft;
	FourWayHeapNode hright;


	public FourWayHeapNode(int data, long freq){
		this.data = data;
		this.freq = freq;
	}

	}
	
	

	public void minheap(int index){
		int small = index;
		int []child = new int[5];
		for(int i=1;i<5;i++){
			child[i]= (4*(index-3)) +i+3;
		}

		for(int i=1;i<5;i++){
			if((child[i]<(size+3))&&(heap[child[i]].freq<heap[small].freq)){
				small = child[i];
			}
		}
		if(small!=index){
			FourWayHeapNode temp = heap[small];
			heap[small]=heap[index];
			heap[index]=temp;
			minheap(small);
		}
	}

	  public FourWayHeapNode removeMin(){
			FourWayHeapNode min = heap[3];
			heap[3]=heap[(size+3)-1];
			size--;
			minheap(3);
			return min;
		}

	  public void insert(FourWayHeapNode b){
			size++;
			int i=size-1+3;
			while((i>=3) && (b.freq<heap[((i-4)/4)+3].freq)){
				heap[i] = heap[((i-4)/4)+3];
				i=((i-4)/4)+3;
			}
			heap[i] = b;
		}

	public FourWayHeapNode build_tree_using_4way_heap(FrequencyTable ft){
		long freq[] = ft.getFreq();
		FourWayHeapNode parent;
        FourWayHeapNode left;
        FourWayHeapNode right;
		heap=new FourWayHeapNode[freq.length];
		int j = 3;

		for(int i=0;i<freq.length;i++){
			if(freq[i]!=0)  {

				heap[j++]= new FourWayHeapNode(i,freq[i]);
			}
		}
		size = j-3;


		for(int i=((j-5)/4)+3;i>=3;i--){
			minheap(i);
		}


		while(size!=1){
			left = removeMin();
			right = removeMin();

			parent = new FourWayHeapNode(-1,left.freq+right.freq);
			parent.hleft = left;
			parent.hright = right;
			insert(parent);
		}
		return removeMin();

	}



	
	public void printheap(){
		for(int i=3;i<size+3;i++){
			System.out.println(heap[i].data +" "+heap[i].freq);
		}
		
	}


}
