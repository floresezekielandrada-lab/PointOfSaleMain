/**
 * ============================================
 * MAIN CLASS - ENTRY POINT
 * ============================================
 * Ito ang unang class na tatakbo kapag binuksan ang app.
 * Dito nagsisimula ang buong POS system.
 */
public class Main {
	
	public static void main(String[] args) {
		// Gumagawa ng manager para sa login credentials.
		EmailandPassword emailandPassword = new EmailandPassword();
		
		// Binubuksan ang login page gamit ang mga saved credentials.
		new LoginPage(emailandPassword.getLoginInfo());
	}
}