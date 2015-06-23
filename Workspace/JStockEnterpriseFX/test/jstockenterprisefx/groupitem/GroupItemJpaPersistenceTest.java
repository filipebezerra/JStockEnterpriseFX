package jstockenterprisefx.groupitem;

import static org.junit.Assert.assertEquals;
import jstockenterprisefx.base.jpa.BaseJpaPersistenceTest;
import jstockenterprisefx.base.jpa.JpaEntityManager;

import org.junit.Test;

public class GroupItemJpaPersistenceTest extends BaseJpaPersistenceTest<GroupItem, Short> {

	public GroupItemJpaPersistenceTest() {
		dao = new GroupItemDao(JpaEntityManager.getEntityManager());
	}

	@Test
	public void testGroupItemCrud() {
		GroupItem newGroup = new GroupItem(GroupType.SERVICE,
				"Implantação de Software");

		final Short newId = testCreate(newGroup);

		GroupItem groupInserted = dao.read(newId);

		assertEquals(groupInserted.getId(), newId);
		assertEquals("Insert fail", groupInserted.getName(),
				"Implantação de Software");
		assertEquals("Insert fail", groupInserted.getGroupType(),
				GroupType.SERVICE);

		groupInserted.setName("Resistores");
		groupInserted.setGroupType(GroupType.PRODUCT);

		JpaEntityManager.beginTransaction();
		dao.update(groupInserted);
		JpaEntityManager.commit();

		GroupItem groupUpdated = dao.read(newId);

		assertEquals(groupUpdated.getId(), newId);
		assertEquals("Update fail", groupInserted.getName(), "Resistores");
		assertEquals("Update fail", groupInserted.getGroupType(),
				GroupType.PRODUCT);

		testList(1);

		testDelete(groupUpdated);
	}
}
