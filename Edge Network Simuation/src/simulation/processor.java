package simulation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class processor {
	private int processor_speed; // bytes per second

	processor() {
		this.processor_speed = 500;

	}

	public void process(PQueue PQ) throws IOException, InterruptedException {
		FileWriter fl = null;
		try {
			fl = new FileWriter(new File("Processor Log.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		packet pkt = null;
		Boolean flag = true;

		while (true) {
			if (!PQ.isEmpty()) {
				pkt = PQ.dequeue();
				if(pkt!=null) {
					pkt.log(fl);
					try {

						Thread.sleep((int) (pkt.size / this.processor_speed) * 1000);
						PQ.requeue();
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(flag) {
					Thread.sleep(1000);
					flag = false;
					continue;
				}else
					break;
				}
					
		}
//		fl.flush();
		fl.close();
	}
	
	

}
