package dao;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.ManagerBackingBean;
import model.Product;
import model.Uploader;

@ManagedBean
@SessionScoped
public class ProductDAO extends AbsDAO implements Serializable {
	private static final long serialVersionUID = -1522920608554312313L;

	public ProductDAO() {
	}

	@SuppressWarnings("unchecked")
	public List<Product> getListProducts() {
		Session session = beginTransaction();
		List<Product> list = session.createCriteria(Product.class).add(Restrictions.ne("deleted", true)).list();
		commitTransaction(session);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Product> listSale(int amount) {
		Session session = beginTransaction();
		Query query = session.createQuery("from Product p where p.sale=1 and p.deleted=0");
		query.setMaxResults(amount);
		List<Product> list = query.list();
		commitTransaction(session);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Product> listNew(int amount) {
		Session session = beginTransaction();
		Query query = session.createQuery("from Product p where p.deleted=0 order by p.id desc");
		query.setMaxResults(amount);
		List<Product> list = query.list();
		commitTransaction(session);
		return list;
	}

	@SuppressWarnings("unchecked")
	public double getOldPrice(long id) {
		Session session = beginTransaction();
		Query query = session
				.createQuery("select p.price from HisProduct p where p.product.id = :pid order by p.dateChange desc");
		query.setParameter("pid", id);
		query.setMaxResults(1);

		List<Double> list = query.list();

		commitTransaction(session);
		return (list.isEmpty()) ? -1 : list.get(0).doubleValue();
	}

	public void addProduct(Product p, Uploader uploader) {
		Session session = beginTransaction();

		uploader.handleFileUpload();
		if (uploader.getUploadedFile() == null) {
			System.out.println("upload file faile...........");
			throw new FaceletException("chua upload file");
		} else {
			p.setMainImage(uploader.getFileName());
			System.out.println(uploader.getFileName());
			session.save(p);
			commitTransaction(session);
			if (p.getId() != 0)
				try {
					System.out.println("insert successfull");
					FacesContext.getCurrentInstance().getExternalContext().redirect("index.faces");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public String removeProduct(ManagerBackingBean mbb) {
		if (mbb.getSelectedProductList().isEmpty()) {
			System.out.println("Empty list");
			return "";
		}

		Session session = beginTransaction();

		List<Long> productIds = new ArrayList<>();
		for (Product product : mbb.getSelectedProductList()) {
			productIds.add(product.getId());
		}

		Query query = session.createQuery("update Product p set p.deleted=1 where p.id in (:list)");
		query.setParameterList("list", productIds);
		query.executeUpdate();

		mbb.clearSelectedProduct();

		commitTransaction(session);
		return "";
	}
}
