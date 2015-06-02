package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
@ManagedBean
@SessionScoped
public class User implements Serializable {
	private static final long serialVersionUID = -7351729135012380019L;
	private long id;
	private Account account;
	private String fullName;
	private String email;
	private String address;
	private String phone;
	private List<Bill> bills;

	public User() {
		this.bills = new LinkedList<Bill>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@OneToOne
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(nullable = false)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", fullName="
				+ fullName + ", email=" + email + ", address=" + address
				+ ", phone=" + phone + "]";
	}

}
