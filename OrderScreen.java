import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Clean order screen with one featured burger and multiple customization options.
 */
public class OrderScreen {

    // Ginagamit ang mga kulay para sa clean at modern na order interface.
    // Ginagamit ang mga kulay para sa modern at clean na order interface.
    private final Color BACKGROUND_COLOR = new Color(15, 23, 42);
    private final Color PANEL_COLOR = new Color(30, 41, 59);
    private final Color PRIMARY_COLOR = new Color(66, 133, 244);
    private final Color SUCCESS_COLOR = new Color(52, 211, 153);
    private final Color DANGER_COLOR = new Color(239, 68, 68);
    private final Color TEXT_COLOR = new Color(226, 232, 240);
    private final Color SUBTLE_COLOR = new Color(148, 163, 184);

    // Ang frame ang nagho-hold sa buong order window.
    private JFrame frame;
    private Product burger;
    private int quantity = 1;
    private JLabel quantityLabel;
    private JLabel subtotalLabel;
    private JLabel styleLabel;
    private JLabel sauceLabel;
    private JLabel sideLabel;
    private String selectedStyle = "Classic";
    private String selectedSauce = "Classic Sauce";
    private String selectedSide = "Fries";
    private double basePrice = 180.00;

    // Constructor
    // Gumagawa ng order window at inilalagay ang featured burger na puwedeng i-customize.
    public OrderScreen(POSSystemFrame posSystem) {
        burger = new Product("burger_001", "Signature Burger", "Premium beef burger with fresh toppings", 180.00, "Burgers");

        frame = new JFrame();
        frame.setTitle("Order Management");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1080, 760);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        createHeaderPanel();
        createContentPanel();
        frame.setVisible(true);
    }

    // Gumagawa ng header na may back button at title ng order page.
    private void createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1080, 90);
        headerPanel.setBackground(PANEL_COLOR);
        headerPanel.setBorder(new MatteBorder(0, 0, 2, 0, PRIMARY_COLOR));
        headerPanel.setLayout(null);

        JButton backButton = new JButton("← Back");
        backButton.setBounds(25, 25, 100, 40);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        backButton.setBackground(new Color(71, 85, 105));
        backButton.setForeground(TEXT_COLOR);
        backButton.setFocusable(false);
        backButton.setBorder(new RoundedBorder(8, new Color(71, 85, 105), 0));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> frame.dispose());
        headerPanel.add(backButton);

        JLabel titleLabel = new JLabel("📦 Order your burger");
        titleLabel.setBounds(155, 20, 700, 40);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
        titleLabel.setForeground(SUCCESS_COLOR);
        headerPanel.add(titleLabel);

        frame.add(headerPanel);
    }

    // Nagsasalang sa main content ng order page kasama ang product card at order summary.
    private void createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setBounds(0, 90, 1080, 670);
        contentPanel.setLayout(null);
        contentPanel.setBackground(BACKGROUND_COLOR);

        JPanel productCard = new JPanel();
        productCard.setBounds(40, 28, 480, 560);
        productCard.setBackground(PANEL_COLOR);
        productCard.setBorder(new RoundedBorder(24, PRIMARY_COLOR, 2));
        productCard.setLayout(null);

        JPanel imageArea = new JPanel();
        imageArea.setBounds(30, 30, 420, 220);
        imageArea.setBackground(new Color(20, 30, 45));
        imageArea.setBorder(new RoundedBorder(18, PRIMARY_COLOR, 1));
        imageArea.setLayout(null);

        JLabel imageLabel = new JLabel("🍔");
        imageLabel.setBounds(0, 0, 420, 220);
        imageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 84));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageArea.add(imageLabel);
        productCard.add(imageArea);

        JLabel nameLabel = new JLabel(burger.getProductName());
        nameLabel.setBounds(30, 270, 300, 30);
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        nameLabel.setForeground(TEXT_COLOR);
        productCard.add(nameLabel);

        JLabel descLabel = new JLabel(burger.getDescription());
        descLabel.setBounds(30, 305, 400, 28);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descLabel.setForeground(SUBTLE_COLOR);
        productCard.add(descLabel);

        JLabel priceLabel = new JLabel("₱ " + String.format("%.2f", basePrice));
        priceLabel.setBounds(30, 340, 180, 24);
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        priceLabel.setForeground(SUCCESS_COLOR);
        productCard.add(priceLabel);

        JLabel styleTitle = new JLabel("Style");
        styleTitle.setBounds(30, 380, 100, 22);
        styleTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        styleTitle.setForeground(TEXT_COLOR);
        productCard.add(styleTitle);

        JPanel styleOptions = new JPanel();
        styleOptions.setBounds(30, 405, 420, 40);
        styleOptions.setOpaque(false);
        styleOptions.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
        addOptionButton(styleOptions, "Classic", true, e -> updateSelection("style", "Classic"));
        addOptionButton(styleOptions, "Cheese", false, e -> updateSelection("style", "Cheese"));
        addOptionButton(styleOptions, "Spicy", false, e -> updateSelection("style", "Spicy"));
        productCard.add(styleOptions);

        JLabel sauceTitle = new JLabel("Sauce");
        sauceTitle.setBounds(30, 455, 100, 22);
        sauceTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        sauceTitle.setForeground(TEXT_COLOR);
        productCard.add(sauceTitle);

        JPanel sauceOptions = new JPanel();
        sauceOptions.setBounds(30, 480, 420, 40);
        sauceOptions.setOpaque(false);
        sauceOptions.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
        addOptionButton(sauceOptions, "Classic Sauce", true, e -> updateSelection("sauce", "Classic Sauce"));
        addOptionButton(sauceOptions, "BBQ", false, e -> updateSelection("sauce", "BBQ"));
        addOptionButton(sauceOptions, "Garlic", false, e -> updateSelection("sauce", "Garlic"));
        productCard.add(sauceOptions);

        JPanel summaryCard = new JPanel();
        summaryCard.setBounds(550, 28, 480, 560);
        summaryCard.setBackground(new Color(16, 24, 39));
        summaryCard.setBorder(new RoundedBorder(24, SUCCESS_COLOR, 2));
        summaryCard.setLayout(null);

        JLabel summaryTitle = new JLabel("Your order");
        summaryTitle.setBounds(30, 30, 200, 30);
        summaryTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        summaryTitle.setForeground(TEXT_COLOR);
        summaryCard.add(summaryTitle);

        styleLabel = new JLabel("Style: Classic");
        styleLabel.setBounds(30, 85, 420, 24);
        styleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        styleLabel.setForeground(TEXT_COLOR);
        summaryCard.add(styleLabel);

        sauceLabel = new JLabel("Sauce: Classic Sauce");
        sauceLabel.setBounds(30, 118, 420, 24);
        sauceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        sauceLabel.setForeground(TEXT_COLOR);
        summaryCard.add(sauceLabel);

        sideLabel = new JLabel("Side: Fries");
        sideLabel.setBounds(30, 151, 420, 24);
        sideLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        sideLabel.setForeground(TEXT_COLOR);
        summaryCard.add(sideLabel);

        JPanel quantityPanel = new JPanel();
        quantityPanel.setBounds(30, 205, 420, 60);
        quantityPanel.setOpaque(false);
        quantityPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

        JButton minusButton = new JButton("−");
        minusButton.setPreferredSize(new Dimension(48, 48));
        minusButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        minusButton.setBackground(DANGER_COLOR);
        minusButton.setForeground(Color.WHITE);
        minusButton.setFocusable(false);
        minusButton.setBorder(new RoundedBorder(12, DANGER_COLOR, 0));
        minusButton.addActionListener(e -> updateQuantity(-1));
        quantityPanel.add(minusButton);

        quantityLabel = new JLabel(String.valueOf(quantity));
        quantityLabel.setPreferredSize(new Dimension(64, 48));
        quantityLabel.setHorizontalAlignment(JLabel.CENTER);
        quantityLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        quantityLabel.setForeground(TEXT_COLOR);
        quantityLabel.setBorder(new RoundedBorder(12, new Color(71, 85, 105), 1));
        quantityPanel.add(quantityLabel);

        JButton plusButton = new JButton("+");
        plusButton.setPreferredSize(new Dimension(48, 48));
        plusButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        plusButton.setBackground(PRIMARY_COLOR);
        plusButton.setForeground(Color.WHITE);
        plusButton.setFocusable(false);
        plusButton.setBorder(new RoundedBorder(12, PRIMARY_COLOR, 0));
        plusButton.addActionListener(e -> updateQuantity(1));
        quantityPanel.add(plusButton);
        summaryCard.add(quantityPanel);

        JLabel subtotalTitle = new JLabel("Subtotal");
        subtotalTitle.setBounds(30, 295, 180, 24);
        subtotalTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        subtotalTitle.setForeground(SUBTLE_COLOR);
        summaryCard.add(subtotalTitle);

        subtotalLabel = new JLabel("₱ 180.00");
        subtotalLabel.setBounds(30, 325, 300, 36);
        subtotalLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        subtotalLabel.setForeground(SUCCESS_COLOR);
        summaryCard.add(subtotalLabel);

        JButton orderButton = new JButton("Place Order");
        orderButton.setBounds(30, 430, 420, 54);
        orderButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        orderButton.setBackground(SUCCESS_COLOR);
        orderButton.setForeground(Color.WHITE);
        orderButton.setFocusable(false);
        orderButton.setBorder(new RoundedBorder(14, SUCCESS_COLOR, 0));
        orderButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Order placed successfully!"));
        summaryCard.add(orderButton);

        contentPanel.add(productCard);
        contentPanel.add(summaryCard);
        frame.add(contentPanel);
        updateSummary();
    }

    // Gumagawa ng button para sa mga option gaya ng style at sauce.
    private void addOptionButton(JPanel panel, String text, boolean selected, ActionListener action) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setForeground(TEXT_COLOR);
        button.setBackground(new Color(51, 65, 85));
        button.setBorder(new RoundedBorder(999, new Color(71, 85, 105), 1));
        button.setPreferredSize(new Dimension(95, 32));
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.addActionListener(action);
        if (selected) {
            button.setBackground(PRIMARY_COLOR);
            button.setForeground(Color.WHITE);
        }
        panel.add(button);
    }

    // Ina-update ang napiling style, sauce, at side kapag pinindot ang option button.
    private void updateSelection(String type, String value) {
        if (type.equals("style")) {
            selectedStyle = value;
            styleLabel.setText("Style: " + value);
        } else if (type.equals("sauce")) {
            selectedSauce = value;
            sauceLabel.setText("Sauce: " + value);
        } else if (type.equals("side")) {
            selectedSide = value;
            sideLabel.setText("Side: " + value);
        }
        updateSummary();
    }

    // Pinapagana ang plus/minus quantity control.
    private void updateQuantity(int delta) {
        quantity = Math.max(1, quantity + delta);
        quantityLabel.setText(String.valueOf(quantity));
        updateSummary();
    }

    // Ina-update ang subtotal at ang order details na ipinapakita sa summary card.
    private void updateSummary() {
        double total = basePrice * quantity;
        subtotalLabel.setText("₱ " + String.format("%.2f", total));
        styleLabel.setText("Style: " + selectedStyle);
        sauceLabel.setText("Sauce: " + selectedSauce);
        sideLabel.setText("Side: " + selectedSide);
    }
}
