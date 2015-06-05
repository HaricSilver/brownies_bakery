package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
@ManagedBean
@SessionScoped
public class Order implements Serializable {
	private static final long serialVersionUID = -2960246219647228971L;
	private long id;
	private int state;
	private User user;
	private String recipient;
	private Calendar createDate;
	private Calendar deliveryDate;
	private double totalCost;
	private List<OrderDetail> products;

	public Order() {
		this.products = new LinkedList<OrderDetail>();
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<OrderDetail> getProducts() {
		return products;
	}

	public void setProducts(List<OrderDetail> products) {
		this.products = products;
	}

	public boolean checkEmptyCart() {
		return (this.products.isEmpty());
	}

	public String addProduct(Product p) {
		if (products.contains(p)) {
			products.get(products.indexOf(p)).increaseQuantity();
		} else {
			products.add(new OrderDetail(p, 1));
		}
		return "";
	}

	public String removeProduct(Product p) {
		this.products.remove(p);
		return "";
	}

	public int computeAmount() {
		int amount = 0;
		for (OrderDetail billDetail : products)
			amount += billDetail.getAmount();
		return amount;
	}

	public double updateCost() {
		this.totalCost = 0;
		for (OrderDetail billDetail : products)
			this.totalCost += billDetail.getProduct().getPrice();
		return this.totalCost;
	}

	@Override
	public String toString() {
		return "Bill [id=" + id + ", state=" + state + ", user=" + user
				+ ", recipient=" + recipient + ", createDate=" + createDate
				+ ", deliveryDate=" + deliveryDate + ", totalCost=" + totalCost
				+ ", products=" + products + "]";
	}

	public void resetProducts() {
		this.products.clear();
	}

}
