package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
@ManagedBean
@SessionScoped
public class Bill implements Serializable {
	private static final long serialVersionUID = -2960246219647228971L;
	private long id;
	private int state;
	private User user;
	private String recipient;
	private Calendar createDate;
	private Calendar deliveryDate;
	private double totalCost;
	private Map<Product, Integer> products;

	public Bill() {
		this.products = new HashMap<Product, Integer>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@ManyToOne
	@JoinColumn(nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(nullable = false)
	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	@Column(nullable = false)
	public Calendar getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Calendar createDate) {
		this.createDate = createDate;
	}

	@Column(nullable = false)
	public Calendar getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Calendar deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name = "bill_detail")
	@Column(nullable = false)
	public Map<Product, Integer> getProducts() {
		return products;
	}

	public void setProducts(Map<Product, Integer> products) {
		this.products = products;
	}

	public boolean checkEmptyCart() {
		return (this.products.isEmpty());
	}

	public String addProduct(Product p) {
		try {
			for (Product product : this.products.keySet()) {
				if (product.equals(p)) {
					System.out.println("replace");
					int previousValue = this.products.get(product).intValue();
					this.products
							.replace(product, new Integer(++previousValue));
					return "";
				}
			}

			System.out.println("put");
			this.products.put(p, new Integer(1));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String removeProduct(Product p) {
		this.products.remove(p);
		return "";
	}

	public int computeAmount() {
		int amount = 0;
		for (Integer ite : this.products.values())
			amount += ite.intValue();

		return amount;
	}

	public double updateCost() {
		this.totalCost = 0;
		for (Product product : this.products.keySet()) {
			this.totalCost += (this.products.get(product).intValue() * product
					.getPrice());
		}
		return this.totalCost;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", state=" + state + ", user=" + user
				+ ", recipient=" + recipient + ", createDate=" + createDate
				+ ", deliveryDate=" + deliveryDate + ", totalCost=" + totalCost
				+ ", products=" + products + "]";
	}

}
