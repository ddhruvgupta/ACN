package simulation;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

public class PQueue extends Thread{
	//TODO: Create Priority Queue
	//	Priority needs to be calculated for each new entry coming in. 
	// 	Priority = weight density = weight/(processing time)

	

	int n ;
	public packet head;
	public packet tail;
	public int queue_capacity; 
	public float total_density;
	
	public float processing_capacity;
	public float priority;
	
	
	PQueue(){
		this.n = 0;
		this.head = null;
		this.tail = null;
		this.priority = 0;
		this.processing_capacity = 10;
		
		this.queue_capacity = 10000;
		this.total_density = 0;
	}
	
	public void scheduler(packet pkt) {
			
		float procTime = pkt.size/this.processing_capacity;
		float density = pkt.weight / procTime;
		pkt.priority = density;
		
	}
	
	public void scheduler2(packet pkt) {
		
		float procTime = pkt.size/this.processing_capacity;
		long t_dash =  ((System.nanoTime()-pkt.start)/(long)procTime);
		float density = pkt.weight / procTime ;
		pkt.priority = density + (float)t_dash;
		
	}

	
	public void enqueue(packet newpkt) {
		
		
		if (this.n<this.queue_capacity) {
			packet pkt = new packet(newpkt);
			packet curr,prev = null;
			int counter = 1;
			scheduler(pkt);
			
			if(this.n==0) {
				this.head = pkt;
				this.tail = pkt;
				
			}else if(this.n==1){
				curr = this.head;
				if(this.head.priority>=pkt.priority) 
					this.head.setNext(pkt);
				else {
					pkt.setNext(this.head);
					this.head = pkt;
				}				
			}else {
				curr= this.head;
				
				while(curr.priority>=pkt.priority && curr.getNext()!=null) {
					prev = curr;
					curr = curr.getNext();
					counter++;
				}
				
				if(counter==n) {
					this.tail.setNext(pkt);
					this.tail = pkt;
				}else if(counter == 1){
					if(this.head.priority>=pkt.priority) 
						this.head.setNext(pkt);
					else {
						pkt.setNext(this.head);
						this.head = pkt;
					}
				}else{
					prev.setNext(pkt);
					pkt.setNext(curr);
				}
				
				
			}
			pkt.setTime();
			this.n++;
		}else {
			System.out.println("Queue Is Full - Packet Dropped");
		}	
	}
	
public void enqueue2(packet newpkt) {
		
		
		if (this.n<this.queue_capacity) {
			packet pkt = new packet(newpkt);
			packet curr,prev = null;
			int counter = 1;
			scheduler2(pkt);
			
			if(this.n==0) {
				this.head = pkt;
				this.tail = pkt;
				
			}else if(this.n==1){
				curr = this.head;
				if(this.head.priority>=pkt.priority) 
					this.head.setNext(pkt);
				else {
					pkt.setNext(this.head);
					this.head = pkt;
				}				
			}else {
				curr= this.head;
				
				while(curr.priority>=pkt.priority && curr.getNext()!=null) {
					prev = curr;
					curr = curr.getNext();
					counter++;
				}
				
				if(counter==n) {
					this.tail.setNext(pkt);
					this.tail = pkt;
				}else if(counter == 1){
					if(this.head.priority>=pkt.priority) 
						this.head.setNext(pkt);
					else {
						pkt.setNext(this.head);
						this.head = pkt;
					}
				}else{
					prev.setNext(pkt);
					pkt.setNext(curr);
				}
				
				
			}
			pkt.setTime();
			this.n++;
		}else {
			System.out.println("Queue Is Full - Packet Dropped");
		}	
	}
	
	public packet dequeue() {
		if(this.head!=null) {
			this.n--;
			packet result = this.head;
			try {
			packet next = result.getNext();
			this.head = next;
			}catch(Exception e) {
//				System.out.println(e);
			}
			
			result.Completed = System.nanoTime();
			return result;		
		}else {
			System.out.println("Queue is empty");
			return null;
		}
	}
	
	public void printAll() {
		
		PrintWriter log = null;
		try {
			log = new PrintWriter("PQueueTest.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		packet curr = this.head;
		
		while(curr.getNext()!=null) {
			
//			System.out.println(curr.priority);
			log.println(curr.priority);
			curr = curr.getNext();
		}
		
		log.close();
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		
		if(this.n==0)
			return true;
		else
			return false;
	}
	
	public void requeue() {
		PQueue PQ_temp = new PQueue();
		packet curr = this.head;
		while(curr.getNext() != null) {
			PQ_temp.enqueue2(curr);
			curr = curr.getNext();
		}
		
		this.head = PQ_temp.head;
	}
	
}
