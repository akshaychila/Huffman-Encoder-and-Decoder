import java.util.*;
import java.io.*;

public class FrequencyTable {

	private long [] frequency = new long[1000000];
	
	public FrequencyTable(String s){
		
		try{
		File f = new File(s);
		Scanner scanner = new Scanner(f);
	//	BufferedReader br = new BufferedReader(new FileReader(f));
		String str = null;
		while(scanner.hasNextInt()){
             //System.out.println(scanner.nextInt());      
			frequency[scanner.nextInt()]++;
		}
		scanner.close();
		}
		catch(NumberFormatException nfe){
			System.out.println(nfe);
		}
		catch(IOException ex){
			System.out.println(ex);
		}
	
	}
	
	public long[] getFreq(){
		return frequency;
	}
}
