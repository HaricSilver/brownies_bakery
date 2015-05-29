package dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import model.Account;

import org.hibernate.Query;
import org.hibernate.Session;

@ManagedBean
@SessionScoped
public class AccountDAO extends AbsDAO {

	@SuppressWarnings("unchecked")
	public String verify(Account acc) {
		Session session = beginTransaction();

		Query query = session
				.createQuery("from Account a where a.name=:aname and a.password=:apass");
		query.setParameter("aname", acc.getName());
		query.setParameter("apass", encryptMD5(acc.getPassword()));

		List<Account> list = query.list();
		if (!list.isEmpty())
			acc = list.get(0);

		commitTransaction(session);
		return (acc.getId() != 0) ? "index?faces-redirect=true" : "";
	}

	public String register(Account acc) {
		Session session = beginTransaction();

		acc.setPassword(encryptMD5(acc.getPassword()));
		session.save(acc);

		commitTransaction(session);
		return (acc.getId() != 0) ? "index?faces-redirect=true" : "";
	}

	@SuppressWarnings("unchecked")
	public static boolean checkUsername(String username) {
		Session session = beginTransaction();

		Query query = session
				.createQuery("from Account a where lower(a.name)=:aname");
		query.setParameter("aname", username.trim().toLowerCase());

		List<Account> list = query.list();

		commitTransaction(session);
		return (!list.isEmpty());
	}

	public String logout() {
		FacesContext.getCurrentInstance().getViewRoot().getViewMap()
				.remove("account");
		return "";
	}

	public static String encryptMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
