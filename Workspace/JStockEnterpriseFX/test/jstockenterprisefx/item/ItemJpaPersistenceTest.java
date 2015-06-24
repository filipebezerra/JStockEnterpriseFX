package jstockenterprisefx.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Query;

import jstockenterprisefx.base.jpa.BaseJpaPersistenceTest;
import jstockenterprisefx.base.jpa.JpaEntityManager;
import jstockenterprisefx.groupitem.GroupItem;
import jstockenterprisefx.groupitem.GroupItemDao;
import jstockenterprisefx.groupitem.GroupType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemJpaPersistenceTest extends BaseJpaPersistenceTest<Item, Long> {
	private GroupItemDao groupDao;

	public ItemJpaPersistenceTest() {
		dao.set(new ItemDao(JpaEntityManager.getEntityManager()));
		groupDao = new GroupItemDao(JpaEntityManager.getEntityManager());
	}

	@Override
	@Before
	public void before() {
		super.before();
		deleteAllGroupItem();
	}

	@Override
	@After
	public void after() {
		super.before();
		deleteAllGroupItem();
	}

	private GroupItem createGroupItem(final GroupItem newGroupItem) {
		JpaEntityManager.beginTransaction();
		groupDao.create(newGroupItem);
		JpaEntityManager.commit();
		return newGroupItem;
	}

	private void deleteAllGroupItem() {
		Class<GroupItem> groupItemClass = groupDao.getEntityClass();

		JpaEntityManager.beginTransaction();
		Query query = dao.get().getEntityManager()
				.createQuery("delete from " + groupItemClass.getSimpleName());
		query.executeUpdate();
		JpaEntityManager.commit();
	}

	@Test
	public void testItemCrud() {
		final GroupItem newGroupItem = new GroupItem(GroupType.PRODUCT,
				"Transistores");
		GroupItem insertedGroupItem = createGroupItem(newGroupItem);

		final Item newItem = new Item("Produto A", new BigDecimal(14.87),
				new BigDecimal(19), 100, insertedGroupItem);

		Item insertedItem = testCreate(newItem);

		final LocalDateTime insertedItemCreatedAt = insertedItem.getCreatedAt();

		assertNotNull("Insert fail", insertedItemCreatedAt);
		assertEquals("Insert fail", insertedItem.getName(), "Produto A");
		assertEquals("Insert fail", insertedItem.getCostPrice(),
				new BigDecimal(14.87));
		assertEquals("Insert fail", insertedItem.getSalePrice(),
				new BigDecimal(19));
		assertEquals("Insert fail", insertedItem.getStockQuantity(), 100);
		assertEquals("Insert fail", insertedItem.getGroupItem(),
				insertedGroupItem);

		final GroupItem newGroupItemForUpdate = new GroupItem(
				GroupType.SERVICE, "Atualização de Software");
		GroupItem insertedGroupItemForUpdate = createGroupItem(newGroupItemForUpdate);

		insertedItem.setName("Produto ABCDE");
		insertedItem.setCostPrice(new BigDecimal(43.9));
		insertedItem.setSalePrice(new BigDecimal(19.9));
		insertedItem.setStockQuantity(60);
		insertedItem.setGroupItem(insertedGroupItemForUpdate);

		Item updatedItem = testUpdate(insertedItem);

		assertEquals("Update fail", updatedItem.getCreatedAt(),
				insertedItemCreatedAt);
		assertEquals("Update fail", updatedItem.getName(), "Produto ABCDE");
		assertEquals("Update fail", updatedItem.getCostPrice(), new BigDecimal(
				43.9));
		assertEquals("Update fail", updatedItem.getSalePrice(), new BigDecimal(
				19.9));
		assertEquals("Update fail", updatedItem.getStockQuantity(), 60);
		assertEquals("Insert fail", insertedItem.getGroupItem(),
				insertedGroupItemForUpdate);

		testList(1);

		testDelete(updatedItem);
	}

}
