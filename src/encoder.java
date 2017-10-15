import java.util.*;


import java.time.Clock;
import  java.io.*;

public class encoder {


public static void main(String [] args){
	
	//Scanner in = new Scanner(System.in);
	//System.out.println("Enter file name:");
	String s = args[0];
    FrequencyTable freq_table = new FrequencyTable(s);

	long freq[] = freq_table.getFreq();

	//for(int i =0;i<freq.length; i++){
	// if(freq[i]!=0)System.out.println(i+" "+freq[i]);
	//}


	/*long starttime;

	starttime = System.currentTimeMillis();

	for(int i=0;i<10;i++){
	BinaryHeap bh = new BinaryHeap(freq_table);
	}
	System.out.println("Time using BinaryHeap (milliseconds) = " +((System.currentTimeMillis()-starttime)/10) );
	BinaryHeap bh = new BinaryHeap(freq_table);
	//bh.generateCode();

    long startime = System.currentTimeMillis();

	for(int i=0;i<10;i++){
	FourWayHeap fh = new FourWayHeap(freq_table);
	}
	System.out.println("Time using FourWayHeap (milliseconds) = " +((System.currentTimeMillis()-startime)/10));

	
   	//FourWayHeap fh = new FourWayHeap(freq_table);
   	//fh.printheap();
   	 
   	
   	
   	long startime1 = System.currentTimeMillis();

	for(int i=0;i<10;i++){
	PairingHeap ph = new PairingHeap(freq_table);
	}
	System.out.println("Time using PairingHeap (milliseconds) = " +((System.currentTimeMillis()-startime1)/10));

	
   	PairingHeap ph = new PairingHeap(freq_table);
    ph.generateCode();
    System.out.println(ph.s.toString());
	*/
	
	BinaryHeap bh = new BinaryHeap(freq_table);
	
	try {
		bh.generateCode();
        BufferedWriter out = new BufferedWriter(new FileWriter("code_table.txt"));
        out.write(bh.s.toString());
        out.close();
    }
    catch (IOException e)
    {
        System.out.println("Exception ");       
    }
	
	
	Map<Integer,String> hmap = new HashMap<Integer, String>();
	try{
	BufferedReader reader = new BufferedReader(new FileReader(new File("code_table.txt")));
    String line = null;
    while ((line = reader.readLine()) != null) {
        if (line.contains(" ")) {
            String[] strings = line.trim().split("\\s");
            hmap.put(Integer.parseInt(strings[0]),strings[1]);
        }
    }
	}catch(Exception e){
		System.out.println(e);
	}
	
	StringBuilder binary= new StringBuilder("");
	try{
		File f = new File(s);
		Scanner scanner = new Scanner(f);
		
		while(scanner.hasNextInt()){
			binary.append(hmap.get(scanner.nextInt()));
		}
		scanner.close();
		
		 byte[] bytes = new byte[binary.length()/8];
		    for(int i = 0; i < binary.length()/8; i++)
		    {
		          bytes[i] = (byte) Short.parseShort(binary.substring(8*i,8*(i+1)),2);
		    }
		    OutputStream os = new BufferedOutputStream(new FileOutputStream("encoded.bin"));
		    os.write(bytes);
		    os.close();

		}
		catch(NumberFormatException nfe){
			System.out.println(nfe);
		}
		catch(IOException ex){
			System.out.println(ex);
		}
	//System.out.println(binary.toString());
	
	}


}
