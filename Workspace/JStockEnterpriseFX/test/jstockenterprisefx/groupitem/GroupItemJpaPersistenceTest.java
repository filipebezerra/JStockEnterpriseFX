package jstockenterprisefx.groupitem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import jstockenterprisefx.base.jpa.JpaEntityManager;
import jstockenterprisefx.groupitem.GroupItem;
import jstockenterprisefx.groupitem.GroupItemDao;
import jstockenterprisefx.groupitem.GroupType;

import org.junit.BeforeClass;
import org.junit.Test;

public class GroupItemJpaPersistenceTest {
	private static JpaEntityManager jpa = new JpaEntityManager();
	private static GroupItemDao dao;

	@BeforeClass
	public static void beforeClass() {
		dao = new GroupItemDao(jpa.getEntityManager());
	}
	
	@Test
	public void testGroupItemCrud() {
		GroupItem newGroup = new GroupItem(GroupType.SERVICE,
				"Implantação de Software");

		jpa.beginTransaction();
		dao.create(newGroup);
		jpa.commit();

		assertNotNull("Created entity returns null ID", newGroup.getId());
		final Short newId = newGroup.getId();

		GroupItem groupInserted = dao.read(newId);

		assertEquals(groupInserted.getId(), newId);
		assertEquals("Insert fail", groupInserted.getName(),
				"Implantação de Software");
		assertEquals("Insert fail", groupInserted.getGroupType(),
				GroupType.SERVICE);

		groupInserted.setName("Resistores");
		groupInserted.setGroupType(GroupType.PRODUCT);

		jpa.beginTransaction();
		dao.update(groupInserted);
		jpa.commit();

		GroupItem groupUpdated = dao.read(newId);

		assertEquals(groupUpdated.getId(), newId);
		assertEquals("Update fail", groupInserted.getName(), "Resistores");
		assertEquals("Update fail", groupInserted.getGroupType(),
				GroupType.PRODUCT);

		jpa.beginTransaction();
		dao.delete(groupUpdated);
		jpa.commit();

		GroupItem groupDeleted = dao.read(newId);
		assertNull("Delete fail", groupDeleted);
	}
}
