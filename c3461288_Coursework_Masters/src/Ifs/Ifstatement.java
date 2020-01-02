package Ifs;




public class Ifstatement {
	
	int startpostion = -1;  				// start position of the loop
	int timestoloop = 0;
	int hiarachy = 0;
	int condition1 = -1;
	int condition2 = -1;
	String singlestatementcommands = "na";
	
	
	public Ifstatement(int start) {
		// TODO Auto-generated constructor stub
		
		startpostion = start;
		
	}
	
	int endposition = -1;					// end position of the loop
	public int getEndposition() {
		return endposition;
	}
	public void setEndposition(int endposition) {
		this.endposition = endposition;
	}
	public int getStartpostion() {
		return startpostion;
	}
	public void setStartpostion(int startpostion) {
		this.startpostion = startpostion;
	}
	public int getTimestoloop() {
		return timestoloop;
	}
	public void setTimestoloop(int timestoloop) {
		this.timestoloop = timestoloop;
	}
	public int getHiarachy() {
		return hiarachy;
	}
	public void setHiarachy(int hiarachy) {
		this.hiarachy = hiarachy;
	}
	public int getCondition1() {
		return condition1;
	}
	public void setCondition1(int condition1) {
		this.condition1 = condition1;
	}
	public int getCondition2() {
		return condition2;
	}
	public void setCondition2(int condition2) {
		this.condition2 = condition2;
	}

	
	
	
	public String getSinglestatementcommands() {
		return singlestatementcommands;
	}
	public void setSinglestatementcommands(String singlestatementcommands) {
		this.singlestatementcommands = singlestatementcommands;
	}
	
	
	
	
	
}
