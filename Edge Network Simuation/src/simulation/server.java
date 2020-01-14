package simulation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class server {
	//TODO: Create list of packets
	public static List<packet> packetCreator() {
		int size = 0;
		int weight = 0;
		Random rand = new Random();
		
		PrintWriter log = null;
		try {
			log = new PrintWriter("packetCreator.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<packet> mylist = new ArrayList<packet>(); 
		
		for(int i = 0; i<10000; i++) {
			size = rand.nextInt(199)+1;
//			size = 100;
			weight = rand.nextInt(10);
			mylist.add(new packet(size,weight));
			log.println(mylist.get(i).weight);
//			System.out.println(mylist.get(i).weight);
		}
		log.close();
		return mylist;
	}

	public static List<packet> packetCreator2(){
		int size = 0;
		int weight = 0;
		Random rand = new Random();
	
		List<packet> mylist = new ArrayList<packet>(); 
		
		for(int i = 0; i<20000; i++) {
			
				size =(int) rand.nextInt(199)+1 & Integer.MAX_VALUE;
				weight = (int) Math.log(i)& Integer.MAX_VALUE;
				mylist.add(new packet(size,weight));	
			
		}
		return mylist;
		
	}
	
	//TODO: Create instance of processor
	

	public static void main(String[] args) throws IOException {
		// TODO Create normal distribution of packets
//		List<packet> myList = packetCreator();
		List<packet> myList = packetCreator2();
		
		PQueue PQ = new PQueue();
		processor pr = new processor();
		
		Thread t1 = new Thread() {
			@Override
			public void run() {
				for(int i=0;i<myList.size();i++) {
					PQ.enqueue(myList.get(i));
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				try {
					Thread.sleep(10);
					pr.process(PQ);
				} catch (IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		t1.start();		
		PQ.printAll();
		t2.start();
		
		
	}

}
