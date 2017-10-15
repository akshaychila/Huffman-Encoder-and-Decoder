import java.io.*;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class decoder {

	public static void main(String[] args){
	Scanner in = new Scanner(System.in);	
	
	String s = args[0];
	String s2 = args[1];
	//String[] st = s.trim().split("\\s+");
	Map<String, Integer> hmap = new HashMap<String, Integer>();
	try{
	BufferedReader reader = new BufferedReader(new FileReader(new File(s2)));
    String line = null;
    while ((line = reader.readLine()) != null) {
        if (line.contains(" ")) {
            String[] strings = line.trim().split("\\s");
            hmap.put(strings[1],Integer.parseInt(strings[0]));
        }
    }
	}catch(Exception e){
		System.out.println(e);
	}
	
	//for(String str:hmap.keySet()){
	//System.out.println(str);
	//}
	
	byte[] bytes = null;
	try{	
		  
		  File inFile = new File(s);
		  bytes = new byte[(int)inFile.length()];
		   InputStream input = new BufferedInputStream(new FileInputStream(inFile));
		    int i=0;
		    while(i< bytes.length){
		    	int j = input.read(bytes, i, bytes.length-i);
		    	if(j>0){
		    		i = i+j;
		    	}
		    }
		    input.close();
		}
		catch(Exception ex){
			
		}
	
	//for(byte bt:bytes){
	//	System.out.println(bt);
	//}
	
	String str = "";
	for(byte b:bytes){
	 str += b;
	}
	
	
	
	BinaryHeap.BinaryHeapNode hroot = new BinaryHeap.BinaryHeapNode(-1,0);
	
	BinaryHeap.BinaryHeapNode curr;
	for(String string: hmap.keySet()){
		curr = hroot;
		for(int i=0;i<string.length();i++){
			if(string.charAt(i)=='0'){
				if(curr.left==null){
					curr.left= new BinaryHeap.BinaryHeapNode(-1,0);
				}
				curr = curr.left;
			}
			else{
				if(curr.right==null){
					curr.right = new BinaryHeap.BinaryHeapNode(-1,0);
				}
				curr = curr.right;
			}
		}
		curr.setData(hmap.get(string));

	}
	
	
	
	/* StringBuilder binaryString = new StringBuilder();
    for(byte b:bytes){
	for(int i = 0; i < 8; i++)
        binaryString.append(((0x80 >>> i) & b) == 0? '0':'1');
    }
    */
    
    //String bstring = binaryString.toString();
    
    //System.out.println(bstring);
    
    
	BinaryHeap.BinaryHeapNode curr1 = hroot;
    
    
    try{
        BufferedWriter out = new BufferedWriter(new FileWriter("decoded.txt"));

        
    	
        for(byte b:bytes){
        	for(int i = 0; i < 8; i++){
        		char c = ((0x80 >>> i) & b) == 0? '0':'1';
        	
        		if(c=='0'){
        			curr1 = curr1.left;
        			if(curr1.getData()!=-1){
        				out.write(curr1.getData() + "\n");
        				curr1 = hroot;
        			}
        		}
        		else{
        			curr1 = curr1.right;
        			if(curr1.getData()!=-1){
        				out.write(curr1.getData() + "\n");
        				curr1 = hroot;
        			}
        		}
        	}
        }
        out.close();
    	}
        
    	catch(Exception e){
    		
    	}
    
}
}
