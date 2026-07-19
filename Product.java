/**
 * ============================================
 * PRODUCT CLASS
 * ============================================
 * Kumakatawan sa isang produkto tulad ng burger.
 * Dito kinokontrol ang detalye ng produkto at ang quantity na pinipili.
 */
public class Product {
	
	private String productId;      // Unique product ID
	private String productName;    // Product name (e.g., "Classic Burger")
	private String description;    // Short description
	private double price;          // Price per unit
	private int quantity;          // Selected quantity (default 0)
	private String category;       // Product category
	
	/**
	 * CONSTRUCTOR
	 * Creates a new product with given details
	 * 
	 * @param productId - Unique ID for product
	 * @param productName - Name of product
	 * @param description - Product description
	 * @param price - Price per unit
	 * @param category - Product category
	 */
	public Product(String productId, String productName, String description, 
				  double price, String category) {
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.quantity = 0;  // Default quantity
		this.category = category;
	}
	
	// ===== GETTERS =====
	public String getProductId() {
		return productId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public String getCategory() {
		return category;
	}
	
	// ===== QUANTITY MANAGEMENT =====
	
	/**
	 * INCREASE QUANTITY
	 * Adds 1 to the quantity
	 */
	public void increaseQuantity() {
		this.quantity++;
	}
	
	/**
	 * DECREASE QUANTITY
	 * Subtracts 1 from quantity (minimum 0)
	 */
	public void decreaseQuantity() {
		if (this.quantity > 0) {
			this.quantity--;
		}
	}
	
	/**
	 * SET QUANTITY
	 * Sets quantity to specific value
	 * 
	 * @param quantity - The quantity to set
	 */
	public void setQuantity(int quantity) {
		if (quantity >= 0) {
			this.quantity = quantity;
		}
	}
	
	/**
	 * RESET QUANTITY
	 * Resets quantity back to 0
	 */
	public void resetQuantity() {
		this.quantity = 0;
	}
	
	// ===== CALCULATIONS =====
	
	/**
	 * GET SUBTOTAL
	 * Calculates total price (price × quantity)
	 * 
	 * @return Subtotal amount
	 */
	public double getSubtotal() {
		return price * quantity;
	}
}