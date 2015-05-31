package model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class HisUser {
	private User user;
	private Calendar dateChange;
	private String address;
	private String phone;

	public HisUser() {
	}

	@Id
	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Id
	@Column(nullable = false)
	public Calendar getDateChange() {
		return dateChange;
	}

	public void setDateChange(Calendar dateChange) {
		this.dateChange = dateChange;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
