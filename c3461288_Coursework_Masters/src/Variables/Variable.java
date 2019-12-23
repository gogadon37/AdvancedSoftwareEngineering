package Variables;

public class Variable {
	
	
	String key;
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getPair() {
		return pair;
	}

	public void setPair(int pair) {
		this.pair = pair;
	}

	int pair;
	
	public Variable(String name, int value) {
		// TODO Auto-generated constructor stub
		
		key = name;
		pair = value;
	}
	
	
	

}
