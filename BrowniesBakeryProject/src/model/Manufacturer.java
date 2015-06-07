package model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "manufacturers")
public class Manufacturer implements Serializable {
	private static final long serialVersionUID = 8406693936257602338L;
	private long id;
	private String name;
	private String address;
	private String[] email;
	private String[] phone;

	public Manufacturer() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String[] getEmail() {
		return email;
	}

	public void setEmail(String[] email) {
		this.email = email;
	}

	public String[] getPhone() {
		return phone;
	}

	public void setPhone(String[] phone) {
		this.phone = phone;
	}

}
