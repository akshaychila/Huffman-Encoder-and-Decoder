import java.util.*;



public class PairingHeap {
 
	private PairHeapNode root;
	private PairHeapNode[] heap;
	private PairHeapNode hroot;
	int size;
	
	
	public PairingHeap(FrequencyTable ft){
		this.hroot = build_tree_using_pairing_heap(ft);
	}
	
	public static class PairHeapNode{
		private int data;
		private long freq;
		PairHeapNode child;
		PairHeapNode right;
		PairHeapNode left;
		PairHeapNode hright;
		PairHeapNode hleft;
	
		public PairHeapNode(int data, long freq){
			this.data = data;
			this.freq = freq;
		}
	}
	
	public PairHeapNode insert(PairHeapNode p){
		
		if(root==null) {
			root = p;
		}
		else root = meld(root,p);
		size++;
		return p;
	}
	
	public PairHeapNode meld(PairHeapNode a, PairHeapNode b){
		if(b==null){
		  return a;}
		  
		  
		if(b.freq<a.freq) {
			b.left = a.left;
			a.left=b;
			a.right = b.child;
			if(a.right!=null){
				a.right.left = a;
			}
			b.child=a;
			return b;
		}
		else{
			b.left = a;
			a.right = b.right;
			if(a.right!=null){
				a.right.left = a;
			}
			b.right = a.child;
			if(b.right!=null){
				b.right.left = b;
			}
			a.child = b;
			return a;
		}
		
	}
	public boolean isEmpty(){
		return root==null;
	}
	
	public PairHeapNode combine(PairHeapNode p){
		
		if(p.right == null){
			return p;
		}
		int j=0;
		
		while(p!=null){
			heap[j++] = p;
			p.left.right = null;
			p = p.right;
		}
		
		heap[j] = null;
		
		int i;
		for(i=0;i+1<j;i+=2){
			heap[i] = meld(heap[i],heap[i+1]);
		}
		int k = i-2;
		if(k==j-3){
			heap[k] = meld(heap[k],heap[k+2]);
				
			}
		
		while(k>1){
			heap[k-2]= meld(heap[k-2],heap[k]);
		}
		return heap[0];
		
	}
	
	public PairHeapNode removeMin(){
		if(heap[0]==null){
			return null;
		}
		PairHeapNode min = heap[0];
		if(heap[0].child==null){
			heap[0] = null;
		}
		else root = combine(root.child);
		size--;
		return min;
	}
	
	public PairHeapNode build_tree_using_pairing_heap(FrequencyTable ft){
		long freq[] = ft.getFreq();
		PairHeapNode parent;
        PairHeapNode hleft;
        PairHeapNode hright;
		
		heap= new PairHeapNode[freq.length];
        int j = 0;
		for(int i=0;i<freq.length;i++){
			if(freq[i]!=0)  {
                
				insert(new PairHeapNode(i,freq[i]));
				
			}
		}
		size=j;
		
		
		
		while(!isEmpty()){
			hleft = removeMin();
			hright = removeMin();
            if(hright==null) return hleft;
			
			if(hleft!=null&&hright!=null){
			parent = new PairHeapNode(-1,hleft.freq+hright.freq);
			parent.hleft = hleft;
			parent.hright = hright;
			hroot = insert(parent);}
		}
		return hroot;
		

	}
	public void printheap(){
		printheap(root);
	}
	
	public void printheap(PairHeapNode r){
		if(r!=null){
			System.out.println(r.data +" "+r.freq);
			printheap(r.child);
		   printheap(r.right);
		}
	}
	
	public void generateCode(){
		generateCode(hroot,"");
	}

	StringBuilder s=new StringBuilder();

	
	public void generateCode(PairHeapNode root,String st){
		if(root==null){
			return;
		}
		if(root.data!=-1){
			s.append(root.data +" "+st +"\n");
		}
		generateCode(root.left,st+"0");
		generateCode(root.right,st+"1"); 

	}
	

	
}
