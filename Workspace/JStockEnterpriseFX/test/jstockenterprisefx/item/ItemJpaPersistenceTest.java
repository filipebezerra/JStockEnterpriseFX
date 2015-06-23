package jstockenterprisefx.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jstockenterprisefx.base.jpa.JpaEntityManager;
import jstockenterprisefx.groupitem.GroupItem;
import jstockenterprisefx.groupitem.GroupItemDao;
import jstockenterprisefx.groupitem.GroupType;

import org.junit.BeforeClass;
import org.junit.Test;

public class ItemJpaPersistenceTest {
	private static JpaEntityManager jpa = new JpaEntityManager();
	private static ItemDao dao;
	private static GroupItemDao groupDao;

	@BeforeClass
	public static void beforeClass() {
		dao = new ItemDao(jpa.getEntityManager());
		groupDao = new GroupItemDao(jpa.getEntityManager());
	}
	
	@Test
	public void testItemCrud() {
		final GroupItem newGroupItem = new GroupItem(GroupType.PRODUCT, "Transistores");
		jpa.beginTransaction();
		groupDao.create(newGroupItem);
		jpa.commit();
		
		Item newItem = new Item("Produto A", new BigDecimal(14.87),
				new BigDecimal(19), 100, newGroupItem);

		jpa.beginTransaction();
		dao.create(newItem);
		jpa.commit();
		
		final LocalDateTime createdAt = newItem.getCreateAt();

		assertNotNull("Created entity returns null ID", newItem.getId());
		final Long newId = newItem.getId();

		Item itemInserted = dao.read(newId);

		assertEquals(itemInserted.getId(), newId);
		assertNotNull("Insert fail", createdAt);
		assertEquals("Insert fail", itemInserted.getDescription(), "Produto A");
		assertEquals("Insert fail", itemInserted.getCostPrice(), new BigDecimal(14.87));
		assertEquals("Insert fail", itemInserted.getSalePrice(), new BigDecimal(19));
		assertEquals("Insert fail", itemInserted.getStockQuantity(), 100);
		assertEquals("Insert fail", itemInserted.getGroupItem(), newGroupItem);

		final GroupItem updateGroupItem = new GroupItem(GroupType.SERVICE, "Atualização de Software");
		jpa.beginTransaction();
		groupDao.create(updateGroupItem);
		jpa.commit();
		
		itemInserted.setDescription("Produto ABCDE");
		itemInserted.setCostPrice(new BigDecimal(43.9));
		itemInserted.setSalePrice(new BigDecimal(19.9));
		itemInserted.setStockQuantity(60);
		itemInserted.setGroupItem(updateGroupItem);

		jpa.beginTransaction();
		dao.update(itemInserted);
		jpa.commit();

		Item itemUpdated = dao.read(newId);

		assertEquals(itemInserted.getId(), newId);
		assertEquals("Update fail", itemUpdated.getCreateAt(), createdAt);
		assertEquals("Update fail", itemUpdated.getDescription(), "Produto ABCDE");
		assertEquals("Update fail", itemUpdated.getCostPrice(), new BigDecimal(43.9));
		assertEquals("Update fail", itemUpdated.getSalePrice(), new BigDecimal(19.9));
		assertEquals("Update fail", itemUpdated.getStockQuantity(), 60);
		assertEquals("Insert fail", itemInserted.getGroupItem(), updateGroupItem);

		jpa.beginTransaction();
		dao.delete(itemUpdated);
		jpa.commit();

		Item ItemDeleted = dao.read(newId);
		assertNull("Delete fail", ItemDeleted);
	}
}
