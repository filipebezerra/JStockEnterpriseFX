package jstockenterprisefx.stock;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jstockenterprisefx.base.jpa.BaseJpaPersistenceTest;
import jstockenterprisefx.base.jpa.JpaEntityManager;
import jstockenterprisefx.department.Department;
import jstockenterprisefx.department.DepartmentDao;
import jstockenterprisefx.groupitem.GroupItem;
import jstockenterprisefx.groupitem.GroupItemDao;
import jstockenterprisefx.groupitem.GroupType;
import jstockenterprisefx.item.Item;
import jstockenterprisefx.item.ItemDao;
import jstockenterprisefx.stock.issue.StockIssue;
import jstockenterprisefx.stock.issue.StockIssueDao;
import jstockenterprisefx.stock.issue.StockIssueItem;
import static org.junit.Assert.*;

public class StockIssueJpaPersistenceTest extends
		BaseJpaPersistenceTest<StockIssue, Long> {

	private DepartmentDao departmentDao;

	private ItemDao itemDao;

	private GroupItemDao groupDao;

	public StockIssueJpaPersistenceTest() {
		dao.set(new StockIssueDao(JpaEntityManager.getEntityManager()));
		departmentDao = new DepartmentDao(JpaEntityManager.getEntityManager());
		itemDao = new ItemDao(JpaEntityManager.getEntityManager());
		groupDao = new GroupItemDao(JpaEntityManager.getEntityManager());
	}

	@Override
	@Before
	public void before() {
		deleteAllStockIssues();
		deleteAllDepartments();
		deleteAllItems();
		deleteAllGroupItems();
	}

	@Override
	@After
	public void after() {
		deleteAllStockIssues();
		deleteAllDepartments();
		deleteAllItems();
		deleteAllGroupItems();
	}

	private void deleteAllStockIssues() {
		Class<StockIssue> stockIssueClass = dao.get().getEntityClass();

		JpaEntityManager.beginTransaction();
		dao.get().getEntityManager().createQuery("delete from StockIssueItem")
				.executeUpdate();
		JpaEntityManager.commit();

		JpaEntityManager.beginTransaction();
		Query query = dao.get().getEntityManager()
				.createQuery("delete from " + stockIssueClass.getSimpleName());
		query.executeUpdate();
		JpaEntityManager.commit();
	}

	private void deleteAllDepartments() {
		Class<Department> departmentClass = departmentDao.getEntityClass();

		JpaEntityManager.beginTransaction();
		Query query = dao.get().getEntityManager()
				.createQuery("delete from " + departmentClass.getSimpleName());
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

	private Department createDepartment(final Department department) {
		JpaEntityManager.beginTransaction();
		departmentDao.create(department);
		JpaEntityManager.commit();
		return department;
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
		final Department newDepartment = new Department("Suporte",
				"Vinícius Solo");

		Department insertedSupplier = createDepartment(newDepartment);

		assertNotNull(newDepartment);
		assertNotNull(newDepartment.getId());

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

		StockIssue newStockIssue = new StockIssue(LocalDate.now(),
				insertedSupplier);

		StockIssueItem stockReceiptItem = new StockIssueItem();
		stockReceiptItem.setItem(insertedItem);
		stockReceiptItem.setQuantity((short) 10);

		newStockIssue.addStockIssueItem(stockReceiptItem);

		testCreate(newStockIssue);

	}
}
