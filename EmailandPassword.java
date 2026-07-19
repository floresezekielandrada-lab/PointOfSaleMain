import java.util.HashMap;

/**
 * ============================================
 * EMAILANDPASSWORD CLASS
 * ============================================
 * Stores all valid email and password combinations
 * Using HashMap for efficient lookup
 */
public class EmailandPassword {
	
	// HashMap to store email (key) and password (value)
	private HashMap<String, String> logininfo;
	
	/**
	 * CONSTRUCTOR
	 * Initializes the HashMap and adds sample data
	 */
	public EmailandPassword() {
		
		logininfo = new HashMap<String, String>();
		
		// Add sample credentials
		logininfo.put("user@gmail.com", "pizza123");
		logininfo.put("admin@gmail.com", "PASSWORD123");
		logininfo.put("bro@gmail.com", "abc123");
	}
	
	/**
	 * GET LOGIN INFO
	 * Returns the HashMap containing all credentials
	 * 
	 * @return HashMap with email as key and password as value
	 */
	public HashMap<String, String> getLoginInfo() {
		return logininfo;
	}
}