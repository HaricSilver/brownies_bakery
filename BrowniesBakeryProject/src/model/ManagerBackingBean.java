package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ManagerBackingBean implements Serializable {
	private static final long serialVersionUID = 3854964306964705422L;
	private List<Product> selectedProductList;

	public ManagerBackingBean() {
		this.selectedProductList = new ArrayList<Product>();
	}

	public List<Product> getSelectedProductList() {
		return selectedProductList;
	}

	public void setSelectedProductList(List<Product> selectedProductList) {
		this.selectedProductList = selectedProductList;
	}

	public synchronized void handleSelectedProduct(Product p) {
		if (this.selectedProductList.contains(p))
			this.selectedProductList.remove(p);
		else
			this.selectedProductList.add(p);
	}

	public void clearSelectedProduct() {
		this.selectedProductList.clear();
	}

	public void handleCheckAll(boolean checkState, List<Product> products) {
		this.selectedProductList.clear();
		if (checkState)
			this.selectedProductList.addAll(products);
	}

}
