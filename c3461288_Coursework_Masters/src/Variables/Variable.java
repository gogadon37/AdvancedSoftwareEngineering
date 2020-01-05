package Variables;
/**
 * The class is the variable object and contains all the methods to interact with it.
 * 
 * @author George Lloyd-Anderson
 * @version 1.0
 * @since 04/01/20
 * 

 */
public class Variable {
	
	int pair;
	String key;
	
	/**
	 * The getKey command can be called to retrive the name of the variable.
	 * @return returns the key (Variable Name)
	 */
	public String getKey() {
		return key;
	}

	/**
	 * This method allows the calling class to change the name of the variable.
	 * 
	 * @param key the key the user wants to change the key to.
	 */
	public void setKey(String key) {
		this.key = key;
	}

	
	/**
	 * This method returns the value associated with the variable to the calling class.
	 * @return returns the value of the variable.
	 */
	public int getPair() {
		return pair;
	}

	
	/**
	 * The setPair method allows the calling class to change the value of the variable.
	 * @param pair The value the user wants to associate with the variable.
	 */
	
	public void setPair(int pair) {
		this.pair = pair;
	}

	
	
	/**
	 * The Constructor takes two values and sets them to the properties of the class
	 * 
	 * @param name The name of the variable
	 * @param value The value of the variable
	 */
	public Variable(String name, int value) {
		// TODO Auto-generated constructor stub
		
		key = name;
		pair = value;
	}
	
	
	

}
