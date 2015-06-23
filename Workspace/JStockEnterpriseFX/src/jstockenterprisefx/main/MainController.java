package jstockenterprisefx.main;

import static jstockenterprisefx.JStockEnterpriseFXApplication.getRoot;
import static jstockenterprisefx.JStockEnterpriseFXApplication.load;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import jstockenterprisefx.department.DepartmentController;
import jstockenterprisefx.groupitem.GroupItemController;
import jstockenterprisefx.item.ItemController;
import jstockenterprisefx.stock.StockController;
import jstockenterprisefx.supplier.SupplierController;

public class MainController {
	private static ObjectProperty<Parent> sGroupItemView = new SimpleObjectProperty<>(null);
	private static ObjectProperty<Parent> sDepartmentView = new SimpleObjectProperty<>(null);
	private static ObjectProperty<Parent> sItemView = new SimpleObjectProperty<>(null);
	private static ObjectProperty<Parent> sSupplierView = new SimpleObjectProperty<>(null);
	private static ObjectProperty<Parent> sStockView = new SimpleObjectProperty<>(null);
	
	private boolean isVisibleInCenter(final ObjectProperty<Parent> view) {
		final Node center = getRoot().getCenter();
		
		if (view.get() == null)
			throw new NullPointerException("View not initialized.");
		
		if (view.get().getId() == null)
			throw new NullPointerException("View id not setted.");
		
		return (center != null && center.getId().equals(view.get().getId()));
	}
	
	private void showInCenter(final ObjectProperty<Parent> view) {
		if (! isVisibleInCenter(view))
    		getRoot().setCenter(view.get());
	}
	
	private void initializeView(final ObjectProperty<Parent> view, final Class<?> viewControllerClass) {
		if (view.get() == null)
			view.set(load(viewControllerClass));
	}
	
	@FXML
	private void showDepartamentView(final ActionEvent event) {
		initializeView(sDepartmentView, DepartmentController.class);
    	showInCenter(sDepartmentView);
    }

    @FXML
    private void showGroupItemView(final ActionEvent event) {
    	initializeView(sGroupItemView, GroupItemController.class);
    	showInCenter(sGroupItemView);
    }

    @FXML
    private void showItemView(final ActionEvent event) {
    	initializeView(sItemView, ItemController.class);
    	showInCenter(sItemView);
    }

    @FXML
    private void showStockView(final ActionEvent event) {
    	initializeView(sStockView, StockController.class);
    	showInCenter(sStockView);
    }

    @FXML
    private void showSupplierView(final ActionEvent event) {
    	initializeView(sSupplierView, SupplierController.class);
    	showInCenter(sSupplierView);
    }
}
