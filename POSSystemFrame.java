import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * ============================================
 * POSSYSTEMFRAME CLASS
 * ============================================
 * Main POS system interface
 * Shows menu with Order Now and Reserve Now options
 */
public class POSSystemFrame {
	
	// ===== COLORS =====
	// Ginagamit ang mga kulay para sa clean at modern na UI.
	private final Color BACKGROUND_COLOR = new Color(15, 23, 42);
	private final Color PANEL_COLOR = new Color(30, 41, 59);
	private final Color PRIMARY_COLOR = new Color(66, 133, 244);
	private final Color SUCCESS_COLOR = new Color(52, 211, 153);
	private final Color DANGER_COLOR = new Color(239, 68, 68);
	private final Color TEXT_COLOR = new Color(226, 232, 240);
	private final Color SUBTLE_COLOR = new Color(148, 163, 184);
	
	// ===== COMPONENTS =====
	// Ang frame at panel ang nagbuo sa main POS screen.
	private JFrame mainFrame;
	private JPanel mainPanel;
	private String loggedInEmail;
	
	/**
	 * Constructor
	 * Gumagawa ng main window pagkatapos ng login.
	 *
	 * @param email - Email ng user na naka-log in
	 */
	public POSSystemFrame(String email) {
		this.loggedInEmail = email;
		
		mainFrame = new JFrame();
		mainFrame.setTitle("POS Inventory System - " + email);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800, 700);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.getContentPane().setLayout(null);
		
		showMainScreen();
		mainFrame.setVisible(true);
	}
	
	/**
	 * Show Main Screen
	 * Ipinapakita ang landing page kung saan may Order at Reserve buttons.
	 */
	private void showMainScreen() {
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 800, 700);
		mainPanel.setLayout(null);
		mainPanel.setBackground(BACKGROUND_COLOR);
		
		// Header panel
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 800, 80);
		headerPanel.setBackground(PANEL_COLOR);
		headerPanel.setBorder(new MatteBorder(0, 0, 1, 0, PRIMARY_COLOR));
		headerPanel.setLayout(null);
		
		JLabel headerTitle = new JLabel("🏪 POS Inventory System");
		headerTitle.setBounds(30, 15, 400, 50);
		headerTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
		headerTitle.setForeground(PRIMARY_COLOR);
		headerPanel.add(headerTitle);
		
		JLabel userLabel = new JLabel("👤 " + loggedInEmail);
		userLabel.setBounds(450, 25, 300, 30);
		userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		userLabel.setForeground(SUBTLE_COLOR);
		headerPanel.add(userLabel);
		
		mainPanel.add(headerPanel);
		
		// Welcome label
		JLabel welcomeLabel = new JLabel("Welcome to POS System");
		welcomeLabel.setBounds(50, 120, 700, 50);
		welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
		welcomeLabel.setForeground(TEXT_COLOR);
		mainPanel.add(welcomeLabel);
		
		// Subtitle label
		JLabel subtitleLabel = new JLabel("Choose an option below to continue");
		subtitleLabel.setBounds(50, 170, 700, 25);
		subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		subtitleLabel.setForeground(SUBTLE_COLOR);
		mainPanel.add(subtitleLabel);
		
		// Order Now button
		JButton orderButton = createMainButton(
			"📦 Order Now",
			"Create a new order",
			50, 240, 300, 180,
			SUCCESS_COLOR,
			e -> showOrderScreen()
		);
		mainPanel.add(orderButton);
		
		// Reserve Now button
		JButton reserveButton = createMainButton(
			"📅 Reserve Now",
			"Make a reservation",
			450, 240, 300, 180,
			PRIMARY_COLOR,
			e -> showReserveScreen()
		);
		mainPanel.add(reserveButton);
		
		// Logout button
		JButton logoutButton = new JButton("🚪 Logout");
		logoutButton.setBounds(300, 560, 200, 50);
		logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
		logoutButton.setBackground(DANGER_COLOR);
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setFocusable(false);
		logoutButton.setBorder(new RoundedBorder(8, DANGER_COLOR, 0));
		logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		logoutButton.addActionListener(e -> handleLogout());
		logoutButton.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				logoutButton.setBackground(new Color(220, 50, 50));
			}
			public void mouseExited(MouseEvent e) {
				logoutButton.setBackground(DANGER_COLOR);
			}
		});
		mainPanel.add(logoutButton);
		
		mainFrame.getContentPane().removeAll();
		mainFrame.getContentPane().add(mainPanel);
		mainFrame.revalidate();
		mainFrame.repaint();
	}
	
	/**
	 * Create Main Button
	 * Gumagawa ng styled button para sa Order at Reserve.
	 */
	private JButton createMainButton(String title, String subtitle, 
									 int x, int y, int w, int h, 
							 Color bgColor, java.awt.event.ActionListener action) {
		JButton button = new JButton();
		button.setBounds(x, y, w, h);
		button.setBackground(bgColor);
		button.setOpaque(true);
		button.setForeground(Color.WHITE);
		button.setFocusable(false);
		button.setFocusPainted(false);
		JLabel titleLabel = new JLabel(title);
		titleLabel.setBounds(20, 30, 260, 40);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
		titleLabel.setForeground(Color.WHITE);
		button.add(titleLabel);
		
		JLabel subtitleLabel = new JLabel(subtitle);
		subtitleLabel.setBounds(20, 75, 260, 20);
		subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		subtitleLabel.setForeground(new Color(200, 220, 240));
		button.add(subtitleLabel);
		
		button.addActionListener(action);
		
		button.addMouseListener(new MouseAdapter() {
			private Color original = bgColor;
			public void mouseEntered(MouseEvent e) {
				button.setBackground(bgColor.brighter());
			}
			public void mouseExited(MouseEvent e) {
				button.setBackground(original);
			}
		});
		
		return button;
	}
	
	/**
	 * Show Order Screen
	 * Bubuksan ang order page kapag pinindot ang Order.
	 */
	private void showOrderScreen() {
		new OrderScreen(this);
	}
	
	/**
	 * Show Reserve Screen
	 * Ipinapakita ang reserve page para sa reservation management.
	 */
	private void showReserveScreen() {
		
		mainFrame.getContentPane().removeAll();
		
		JPanel reservePanel = new JPanel();
		reservePanel.setBounds(0, 0, 800, 700);
		reservePanel.setLayout(null);
		reservePanel.setBackground(BACKGROUND_COLOR);
		
		// Header
		JPanel headerPanel = new JPanel();
		headerPanel.setBounds(0, 0, 800, 80);
		headerPanel.setBackground(PANEL_COLOR);
		headerPanel.setBorder(new MatteBorder(0, 0, 1, 0, PRIMARY_COLOR));
		headerPanel.setLayout(null);
		
		JButton backButton = new JButton("← Back");
		backButton.setBounds(20, 20, 100, 40);
		backButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		backButton.setBackground(new Color(71, 85, 105));
		backButton.setForeground(TEXT_COLOR);
		backButton.setFocusable(false);
		backButton.setBorder(new RoundedBorder(6, new Color(71, 85, 105), 0));
		backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backButton.addActionListener(e -> {
			mainFrame.getContentPane().removeAll();
			showMainScreen();
			mainFrame.revalidate();
			mainFrame.repaint();
		});
		headerPanel.add(backButton);
		
		JLabel titleLabel = new JLabel("📅 Reservation Management");
		titleLabel.setBounds(150, 20, 500, 40);
		titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
		titleLabel.setForeground(PRIMARY_COLOR);
		headerPanel.add(titleLabel);
		
		reservePanel.add(headerPanel);
		
		// Content box
		JPanel contentBox = new JPanel();
		contentBox.setBounds(50, 120, 700, 400);
		contentBox.setBackground(PANEL_COLOR);
		contentBox.setBorder(new RoundedBorder(10, PRIMARY_COLOR, 2));
		contentBox.setLayout(null);
		
		JLabel contentLabel = new JLabel("📝 Reservation Form Will Be Listed Here");
		contentLabel.setBounds(30, 30, 640, 30);
		contentLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		contentLabel.setForeground(TEXT_COLOR);
		contentBox.add(contentLabel);
		
		reservePanel.add(contentBox);
		
		mainFrame.getContentPane().add(reservePanel);
		mainFrame.revalidate();
		mainFrame.repaint();
	}
	
	/**
	 * Handle Logout
	 * Pinapakita ang confirmation at ibinabalik ang user sa login page.
	 */
	private void handleLogout() {
		
		int response = JOptionPane.showConfirmDialog(
			mainFrame,
			"Are you sure you want to logout?",
			"Logout Confirmation",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);
		
		if (response == JOptionPane.YES_OPTION) {
			mainFrame.dispose();
			EmailandPassword emailandPassword = new EmailandPassword();
			new LoginPage(emailandPassword.getLoginInfo());
		}
	}
}