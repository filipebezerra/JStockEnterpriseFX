package jstockenterprisefx.stock;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jstockenterprisefx.base.jpa.BaseJpaPersistenceTest;
import jstockenterprisefx.base.jpa.JpaEntityManager;
import jstockenterprisefx.base.model.Uf;
import jstockenterprisefx.groupitem.GroupItem;
import jstockenterprisefx.groupitem.GroupItemDao;
import jstockenterprisefx.groupitem.GroupType;
import jstockenterprisefx.item.Item;
import jstockenterprisefx.item.ItemDao;
import jstockenterprisefx.stock.receipt.StockReceipt;
import jstockenterprisefx.stock.receipt.StockReceiptDao;
import jstockenterprisefx.stock.receipt.StockReceiptItem;
import jstockenterprisefx.supplier.Supplier;
import jstockenterprisefx.supplier.SupplierDao;
import static org.junit.Assert.*;

public class StockReceiptJpaPersistenceTest extends
		BaseJpaPersistenceTest<StockReceipt, Long> {

	private SupplierDao supplierDao;

	private ItemDao itemDao;

	private GroupItemDao groupDao;

	public StockReceiptJpaPersistenceTest() {
		dao.set(new StockReceiptDao(JpaEntityManager.getEntityManager()));
		supplierDao = new SupplierDao(JpaEntityManager.getEntityManager());
		itemDao = new ItemDao(JpaEntityManager.getEntityManager());
		groupDao = new GroupItemDao(JpaEntityManager.getEntityManager());
	}

	@Override
	@Before
	public void before() {
		deleteAllStockReceipts();
		deleteAllSuppliers();
		deleteAllItems();
		deleteAllGroupItems();
	}

	@Override
	@After
	public void after() {
		deleteAllStockReceipts();
		deleteAllSuppliers();
		deleteAllItems();
		deleteAllGroupItems();
	}

	private void deleteAllStockReceipts() {
		Class<StockReceipt> stockReceiptClass = dao.get().getEntityClass();

		JpaEntityManager.beginTransaction();
		dao.get().getEntityManager()
				.createQuery("delete from StockReceiptItem").executeUpdate();
		JpaEntityManager.commit();

		JpaEntityManager.beginTransaction();
		Query query = dao
				.get()
				.getEntityManager()
				.createQuery("delete from " + stockReceiptClass.getSimpleName());
		query.executeUpdate();
		JpaEntityManager.commit();
	}

	private void deleteAllSuppliers() {
		Class<Supplier> supplierClass = supplierDao.getEntityClass();

		JpaEntityManager.beginTransaction();
		Query query = dao.get().getEntityManager()
				.createQuery("delete from " + supplierClass.getSimpleName());
		query.executeUpdate();
		JpaEntityManager.commit();
	}

	private void deleteAllItems() {
		Class<Item> itemClass = itemDao.getEntityClass();

		JpaEntityManager.beginTransaction();
		Query query = dao.get().getEntityManager()
				.createQuery("delete from " + itemClass.getSimpleName());
		query.executeUpdate();
		JpaEntityManager.commit();
	}

	private void deleteAllGroupItems() {
		Class<GroupItem> groupItemClass = groupDao.getEntityClass();

		JpaEntityManager.beginTransaction();
		Query query = dao.get().getEntityManager()
				.createQuery("delete from " + groupItemClass.getSimpleName());
		query.executeUpdate();
		JpaEntityManager.commit();
	}

	private Supplier createSupplier(final Supplier supplier) {
		JpaEntityManager.beginTransaction();
		supplierDao.create(supplier);
		JpaEntityManager.commit();
		return supplier;
	}

	private Item createItem(final Item item) {
		JpaEntityManager.beginTransaction();
		itemDao.create(item);
		JpaEntityManager.commit();
		return item;
	}

	private GroupItem createGroupItem(final GroupItem newGroupItem) {
		JpaEntityManager.beginTransaction();
		groupDao.create(newGroupItem);
		JpaEntityManager.commit();
		return newGroupItem;
	}

	@Test
	public void testDepartmentCrud() {
		final Supplier newSupplier = new Supplier("The Creative Shop",
				"The Creative Shop S.A.", "14877648000155", "Av. R2",
				"Bairro Conceição", "São Paulo", Uf.SP, "21393054");

		Supplier insertedSupplier = createSupplier(newSupplier);

		assertNotNull(newSupplier);
		assertNotNull(newSupplier.getId());

		final GroupItem newGroupItem = new GroupItem(GroupType.PRODUCT,
				"Transistores");
		GroupItem insertedGroupItem = createGroupItem(newGroupItem);

		assertNotNull(insertedGroupItem);
		assertNotNull(insertedGroupItem.getId());

		final Item newItem = new Item("Produto A", new BigDecimal(14.87),
				new BigDecimal(19), 100, insertedGroupItem);

		Item insertedItem = createItem(newItem);

		assertNotNull(insertedItem);
		assertNotNull(insertedItem.getId());

		StockReceipt newStockReceipt = new StockReceipt(LocalDate.now(),
				insertedSupplier);

		StockReceiptItem stockReceiptItem = new StockReceiptItem();
		stockReceiptItem.setItem(insertedItem);
		stockReceiptItem.setQuantity((short) 10);

		newStockReceipt.addStockReceiptItem(stockReceiptItem);

		testCreate(newStockReceipt);

	}
}
