package Loops;

public class Loop {
	

	int endposition = -1;					// end position of the loop
	boolean complete = false;				// defines whether the endloop has been completed
	int startpostion = -1;  				// start position of the loop
	int timestoloop = 0;
	
	
	public int getTimestoloop() {
		return timestoloop;
	}



	public void setTimestoloop(int timestoloop) {
		this.timestoloop = timestoloop;
	}



	public int getStartpostion() {
		return startpostion;
	}



	public void setStartpostion(int startpostion) {
		this.startpostion = startpostion;
	}



	public int getEndposition() {
		return endposition;
	}



	public void setEndposition(int endposition) {
		this.endposition = endposition;
	}



	public boolean isComplete() {
		return complete;
	}



	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	
	public Loop(int startpostionn) {
		// TODO Auto-generated constructor stub
		
		startpostion = startpostionn;
		
	}
	

	
	
	

}
