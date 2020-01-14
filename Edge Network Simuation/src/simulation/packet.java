package simulation;

import java.io.FileWriter;
import java.io.IOException;
//import java.io.writeWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class packet {

	public int size; 
	public int weight;
	public float priority;
	public packet next;
	long start;
	long Completed;
	
	packet() {
		this.size = 100;
		this.weight = 10;
		this.priority=0;
		this.next = null;
	}
	
	packet(int s, int w) {
		this.size = s;
		this.weight = w;
		this.priority=0;
		this.next = null;
	}
	
	packet(packet newpacket){
		this.size = newpacket.size;
		this.weight = newpacket.weight;
		this.priority=newpacket.priority;
		this.next = newpacket.next;
	}
	
	public void setNext(packet pkt) {
		this.next = pkt;
	}
	
	public packet getNext() {
		return(this.next);
	}
	public void setTime() {
		this.start = System.nanoTime();
	}
	
	public void log(FileWriter log) throws IOException {
		log.write(Integer.toString(size));
		log.write(" ");
		log.write(Integer.toString(weight));
		log.write(" ");
		log.write(Float.toString(priority));
		log.write(" ");
		log.write(Long.toString(start));
		log.write(" ");
		log.write(Long.toString(Completed));		
		log.write(" ");
		log.write(Long.toString(Completed-start));
		log.write("\n");
		
	}
	
}
