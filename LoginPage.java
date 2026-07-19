import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;


/**
 * ============================================
 * LOGINPAGE CLASS
 * ============================================
 * Professional login interface with modern design
 * Handles user authentication
 */
public class LoginPage implements ActionListener {
	
	// ===== FRAME AT PANEL =====
	// Ginagamit ang frame para sa buong login window.
	private JFrame frame;
	
	// ===== LABELS =====
	// Ginagamit ang mga label para sa title at message sa login form.
	private JLabel titleLabel;
	private JLabel messageLabel;
	
	// ===== INPUT FIELDS =====
	// Dito kinukuha ang email at password na ipinasok ng user.
	private JTextField emailField;
	private JPasswordField passwordField;
	private JTextField visiblePasswordField;
	
	// ===== BUTTONS =====
	// Ang mga button ang nagko-control sa login, reset, at pag-toggle ng password.
	private JButton loginButton;
	private JButton resetButton;
	private JButton togglePasswordButton;
	
	// ===== DATA =====
	// Nag-iimbak ng valid na email at password para sa authentication.
	private HashMap<String, String> logininfo;
	private boolean passwordVisible = false;
	
	// ===== COLORS =====
	// Pinapaganda ang UI gamit ang mga custom na kulay.
	private final Color PRIMARY_COLOR = new Color(66, 133, 244);
	private final Color SECONDARY_COLOR = new Color(52, 168, 224);
	private final Color SUCCESS_COLOR = new Color(52, 211, 153);
	private final Color ERROR_COLOR = new Color(239, 68, 68);
	private final Color BACKGROUND_COLOR = new Color(15, 23, 42);
	private final Color PANEL_COLOR = new Color(30, 41, 59);
	private final Color TEXT_COLOR = new Color(226, 232, 240);
	private final Color SUBTLE_COLOR = new Color(148, 163, 184);
	
	/**
	 * Constructor
	 * Gumagawa ng login window at inilalagay ang valid na credentials.
	 *
	 * @param loginInfoOriginal - Listahan ng valid na email at password
	 */
	public LoginPage(HashMap<String, String> loginInfoOriginal) {
		
		this.logininfo = loginInfoOriginal;
		
		frame = new JFrame();
		frame.setTitle("POS Login System");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 700);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		initializeComponents();
		frame.setVisible(true);
	}
	
	/**
	 * Initialize Components
	 * Nagsasalang sa lahat ng UI element tulad ng fields, buttons, at panel.
	 */
	private void initializeComponents() {
		
		// Main background panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 500, 700);
		mainPanel.setBackground(BACKGROUND_COLOR);
		mainPanel.setLayout(null);
		frame.add(mainPanel);
		
		// Login container panel
		JPanel loginPanel = new JPanel();
		loginPanel.setBounds(50, 80, 400, 550);
		loginPanel.setBackground(PANEL_COLOR);
		loginPanel.setLayout(null);
		loginPanel.setBorder(new RoundedBorder(15, PRIMARY_COLOR, 2));
		mainPanel.add(loginPanel);
		
		// Title
		titleLabel = new JLabel("POS Login");
		titleLabel.setBounds(30, 30, 340, 45);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
		titleLabel.setForeground(PRIMARY_COLOR);
		loginPanel.add(titleLabel);
		
		// Subtitle
		JLabel subtitleLabel = new JLabel("Welcome back! Please login to your account");
		subtitleLabel.setBounds(30, 75, 340, 20);
		subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		subtitleLabel.setForeground(SUBTLE_COLOR);
		loginPanel.add(subtitleLabel);
		
		// Email label
		JLabel emailLabel = new JLabel("📧 Email Address");
		emailLabel.setBounds(30, 120, 340, 20);
		emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		emailLabel.setForeground(TEXT_COLOR);
		loginPanel.add(emailLabel);
		
		// Email input field
		emailField = new JTextField();
		emailField.setBounds(30, 145, 340, 45);
		emailField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		emailField.setBackground(new Color(51, 65, 85));
		emailField.setForeground(TEXT_COLOR);
		emailField.setCaretColor(PRIMARY_COLOR);
		emailField.setBorder(new RoundedBorder(8, PRIMARY_COLOR, 1));
		emailField.setMargin(new Insets(5, 15, 5, 15));
		loginPanel.add(emailField);
		
		// Password label
		JLabel passwordLabel = new JLabel("🔐 Password");
		passwordLabel.setBounds(30, 210, 340, 20);
		passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		passwordLabel.setForeground(TEXT_COLOR);
		loginPanel.add(passwordLabel);
		
		// Hidden password field
		passwordField = new JPasswordField();
		passwordField.setBounds(30, 235, 295, 45);
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		passwordField.setBackground(new Color(51, 65, 85));
		passwordField.setForeground(TEXT_COLOR);
		passwordField.setCaretColor(PRIMARY_COLOR);
		passwordField.setBorder(new RoundedBorder(8, PRIMARY_COLOR, 1));
		passwordField.setMargin(new Insets(5, 15, 5, 15));
		loginPanel.add(passwordField);
		
		// Visible password field
		visiblePasswordField = new JTextField();
		visiblePasswordField.setBounds(30, 235, 295, 45);
		visiblePasswordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		visiblePasswordField.setBackground(new Color(51, 65, 85));
		visiblePasswordField.setForeground(TEXT_COLOR);
		visiblePasswordField.setCaretColor(PRIMARY_COLOR);
		visiblePasswordField.setBorder(new RoundedBorder(8, PRIMARY_COLOR, 1));
		visiblePasswordField.setMargin(new Insets(5, 15, 5, 15));
		visiblePasswordField.setVisible(false);
		loginPanel.add(visiblePasswordField);
		
		// Toggle password button (eye icon)
		togglePasswordButton = new JButton("👁️");
		togglePasswordButton.setBounds(335, 235, 45, 45);
		togglePasswordButton.setFont(new Font("Arial", Font.BOLD, 18));
		togglePasswordButton.setBackground(new Color(51, 65, 85));
		togglePasswordButton.setForeground(PRIMARY_COLOR);
		togglePasswordButton.setFocusable(false);
		togglePasswordButton.setBorder(new RoundedBorder(8, PRIMARY_COLOR, 1));
		togglePasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		togglePasswordButton.addActionListener(this);
		loginPanel.add(togglePasswordButton);
		
		// Message label for errors/success
		messageLabel = new JLabel("");
		messageLabel.setBounds(30, 295, 340, 30);
		messageLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		messageLabel.setHorizontalAlignment(JLabel.CENTER);
		loginPanel.add(messageLabel);
		
		// Login button
		loginButton = new JButton("Sign In");
		loginButton.setBounds(30, 340, 340, 50);
		loginButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		loginButton.setBackground(PRIMARY_COLOR);
		loginButton.setForeground(Color.WHITE);
		loginButton.setFocusable(false);
		loginButton.setBorder(new RoundedBorder(8, PRIMARY_COLOR, 0));
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginButton.addActionListener(this);
		loginButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				loginButton.setBackground(SECONDARY_COLOR);
			}
			public void mouseExited(MouseEvent e) {
				loginButton.setBackground(PRIMARY_COLOR);
			}
		});
		loginPanel.add(loginButton);
		
		// Reset button
		resetButton = new JButton("Clear");
		resetButton.setBounds(30, 400, 340, 50);
		resetButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		resetButton.setBackground(new Color(71, 85, 105));
		resetButton.setForeground(TEXT_COLOR);
		resetButton.setFocusable(false);
		resetButton.setBorder(new RoundedBorder(8, new Color(71, 85, 105), 0));
		resetButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		resetButton.addActionListener(this);
		resetButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				resetButton.setBackground(new Color(90, 110, 130));
			}
			public void mouseExited(MouseEvent e) {
				resetButton.setBackground(new Color(71, 85, 105));
			}
		});
		loginPanel.add(resetButton);
		
		// Demo info label
		JLabel infoLabel = new JLabel("Demo: user@gmail.com / pizza123");
		infoLabel.setBounds(30, 465, 340, 15);
		infoLabel.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		infoLabel.setForeground(SUBTLE_COLOR);
		infoLabel.setHorizontalAlignment(JLabel.CENTER);
		loginPanel.add(infoLabel);
	}
	
	/**
	 * Toggle Password Visibility
	 * Pinapakita o tinatago ang password para sa mas madaling pag-input.
	 */
	private void handleTogglePasswordButton() {
		if (!passwordVisible) {
			String password = String.valueOf(passwordField.getPassword());
			visiblePasswordField.setText(password);
			passwordField.setVisible(false);
			visiblePasswordField.setVisible(true);
			togglePasswordButton.setText("👁️‍🗨️");
			togglePasswordButton.setBackground(SUCCESS_COLOR);
			passwordVisible = true;
		} else {
			String password = visiblePasswordField.getText();
			passwordField.setText(password);
			visiblePasswordField.setVisible(false);
			passwordField.setVisible(true);
			togglePasswordButton.setText("👁️");
			togglePasswordButton.setBackground(new Color(51, 65, 85));
			passwordVisible = false;
		}
	}
	
	/**
	 * Reset Form
	 * Binubura ang mga field para sa bagong login attempt.
	 */
	private void handleResetButton() {
		emailField.setText("");
		passwordField.setText("");
		visiblePasswordField.setText("");
		messageLabel.setText("");
		
		if (passwordVisible) {
			passwordField.setVisible(true);
			visiblePasswordField.setVisible(false);
			togglePasswordButton.setText("👁️");
			togglePasswordButton.setBackground(new Color(51, 65, 85));
			passwordVisible = false;
		}
	}
	
	/**
	 * Handle Login
	 * Tinitingnan kung tama ang email at password, at kung okay ay bubuksan ang POS screen.
	 */
	private void handleLoginButton() {
		String email = emailField.getText();
		String password;
		
		if (passwordVisible) {
			password = visiblePasswordField.getText();
		} else {
			password = String.valueOf(passwordField.getPassword());
		}
		
		// Validate inputs
		if (email.isEmpty() || password.isEmpty()) {
			displayErrorMessage("⚠️ Email at password ay required");
			return;
		}
		
		// Check if email exists
		if (!logininfo.containsKey(email)) {
			displayErrorMessage("❌ Email not found");
			return;
		}
		
		// Check if password is correct
		String correctPassword = logininfo.get(email);
		
		if (correctPassword.equals(password)) {
			displaySuccessMessage("✅ Login successful!");
			
			try {
				Thread.sleep(1500);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			
			frame.dispose();
			new POSSystemFrame(email);
			
		} else {
			displayErrorMessage("❌ Wrong password");
		}
	}
	
	/**
	 * Show Success Message
	 * Ipinapakita ang message kapag matagumpay ang login.
	 */
	private void displaySuccessMessage(String message) {
		messageLabel.setForeground(SUCCESS_COLOR);
		messageLabel.setText(message);
	}
	
	/**
	 * Show Error Message
	 * Ipinapakita ang message kapag may mali sa credentials.
	 */
	private void displayErrorMessage(String message) {
		messageLabel.setForeground(ERROR_COLOR);
		messageLabel.setText(message);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == togglePasswordButton) {
			handleTogglePasswordButton();
		}
		if (e.getSource() == resetButton) {
			handleResetButton();
		}
		if (e.getSource() == loginButton) {
			handleLoginButton();
		}
	}
}