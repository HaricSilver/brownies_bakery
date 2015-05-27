package model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class HisProduct implements Serializable {
	private static final long serialVersionUID = -1726472895793404760L;
	private Product product;
	private String name;
	private double price;
	private Calendar dateChange;

	@Id
	@ManyToOne
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Id
	public Calendar getDateChange() {
		return dateChange;
	}

	public void setDateChange(Calendar dateChange) {
		this.dateChange = dateChange;
	}

}
