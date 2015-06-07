package dao;

import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Account;
import model.Order;
import model.User;

import org.hibernate.Query;
import org.hibernate.Session;

@ManagedBean
@SessionScoped
public class OrderDAO extends AbsDAO {

	@SuppressWarnings("unchecked")
	public List<Order> list() {
		Session session = beginTransaction();
		Query query = session.createQuery("from Bill b order by b.state");
		List<Order> list = query.list();
		commitTransaction(session);
		return list;
	}

	public String confirm(Order bill, User user, Account acc) {
		Session session = beginTransaction();
		if (user.getId() == 0) {
			user.setFullName(acc.getName());
			user.setAccount(acc);
			bill.setUser(user);
			session.save(user);
		}
		bill.setCreateDate(Calendar.getInstance());
		session.save(bill);
		commitTransaction(session);
		
		bill.resetProducts();
		return (bill.getId() != 0) ? "success" : "error-checkout";
	}
}
