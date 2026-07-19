import java.awt.*;
import javax.swing.*;
 
public class WelcomePage {
	
	// ===== FRAME COMPONENTS =====
	private JFrame frame;
	
	// ===== LABELS =====
	private JLabel welcomeLabel;
	private JLabel emailDisplayLabel;
	
	/**
	 * CONSTRUCTOR
	 * Nag-setup ng welcome screen
	 * 
	 * @param email - Email ng user na nag-login
	 */
	public WelcomePage(String email) {
		
		// Gumawa ng frame
		frame = new JFrame();
		frame.setTitle("Welcome");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 400);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null); // I-center ang window
		
		// ===== INITIALIZE COMPONENTS =====
		initializeComponents(email);
		addComponentsToFrame();
		
		// Ipakita ang frame
		frame.setVisible(true);
	}
	
	/**
	 * INITIALIZE COMPONENTS
	 * Gumawa at nag-setup ng lahat ng UI elements
	 * 
	 * @param email - Email ng logged in user
	 */
	private void initializeComponents(String email) {
		
		// Welcome label (greeting)
		welcomeLabel = new JLabel("Welcome!");
		welcomeLabel.setBounds(50, 50, 400, 50);
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 36));
		welcomeLabel.setForeground(new Color(76, 175, 80)); // Green
		
		// Email display label
		emailDisplayLabel = new JLabel("Logged in as: " + email);
		emailDisplayLabel.setBounds(50, 120, 400, 30);
		emailDisplayLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		emailDisplayLabel.setForeground(new Color(100, 100, 100)); // Gray
	}
	
	/**
	 * ADD COMPONENTS TO FRAME
	 * I-add ang lahat ng components sa frame
	 */
	private void addComponentsToFrame() {
		frame.add(welcomeLabel);
		frame.add(emailDisplayLabel);
	}
}
