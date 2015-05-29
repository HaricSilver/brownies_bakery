package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;

public abstract class AbsDAO {

	protected static Session beginTransaction() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		return session;
	}

	protected static void commitTransaction(Session session) {
		Transaction transaction = session.getTransaction();
		if (transaction != null && !transaction.wasCommitted()
				&& !transaction.wasRolledBack() && transaction.isActive()) {
			transaction.commit();
		} else if (transaction.wasRolledBack()) {
			transaction.rollback();
		}
	}
}
