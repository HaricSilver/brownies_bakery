package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1434842466639842324L;
	private long id;
	private Product product;
	private int amount;

	public OrderDetail() {
	}

	public OrderDetail(Product product, int amount) {
		this.product = product;
		this.amount = amount;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void increaseQuantity() {
		this.amount++;
	}

	public double sumPrice() {
		return (this.getProduct().getPrice() * this.getAmount());
	}

}
