package Loops;

public class Loop implements Comparable<Loop> {
	

	int endposition = -1;					// end position of the loop
	
	int startpostion = -1;  				// start position of the loop
	int timestoloop = 0;
	int name = 0;
	int hiarachy = 0;
	
	
	public int getHiarachy() {
		return hiarachy;
	}



	public void setHiarachy(int hiarachy) {
		this.hiarachy = hiarachy;
	}



	public boolean isPartent() {
		return partent;
	}



	public void setPartent(boolean partent) {
		this.partent = partent;
	}



	public boolean isChild() {
		return child;
	}



	public void setChild(boolean child) {
		this.child = child;
	}


	boolean partent = false;
	boolean child = false;
	
	
	public int getName() {
		return name;
	}



	public void setName(int name) {
		this.name = name;
	}



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



	

	
	public Loop(int startpostionn, int namee) {
		// TODO Auto-generated constructor stub
		
		startpostion = startpostionn;
		name = namee;
	}



	@Override
	public int compareTo(Loop o) {
		// TODO Auto-generated method stub
		if(hiarachy - o.hiarachy == 0) {
			
			// if same hiarachy then sort by name descending
			return o.name - name;
			
		}else {
			
			return hiarachy - o.hiarachy;
			
		}
	}
	

	
	
	

}
