package dao;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.Query;
import org.hibernate.Session;

import model.Bill;

@ManagedBean
@SessionScoped
public class BillDAO extends AbsDAO {

	@SuppressWarnings("unchecked")
	public List<Bill> list() {
		Session session = beginTransaction();
		Query query = session.createQuery("from Bill b order by b.state");
		List<Bill> list = query.list();
		commitTransaction(session);
		return list;
	}
}
