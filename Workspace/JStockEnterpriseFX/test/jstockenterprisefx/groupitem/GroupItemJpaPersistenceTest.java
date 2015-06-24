package jstockenterprisefx.groupitem;

import static org.junit.Assert.assertEquals;
import jstockenterprisefx.base.jpa.BaseJpaPersistenceTest;
import jstockenterprisefx.base.jpa.JpaEntityManager;

import org.junit.Test;

public class GroupItemJpaPersistenceTest extends
		BaseJpaPersistenceTest<GroupItem, Short> {

	public GroupItemJpaPersistenceTest() {
		dao.set(new GroupItemDao(JpaEntityManager.getEntityManager()));
	}

	@Test
	public void testGroupItemCrud() {
		GroupItem newGroup = new GroupItem(GroupType.SERVICE,
				"Implanta��o de Software",
				"O processo implanta��o � previsto para 12 meses");

		GroupItem insertedGroup = testCreate(newGroup);

		assertEquals("Insert fail", insertedGroup.getName(),
				"Implanta��o de Software");
		assertEquals("Insert fail", insertedGroup.getGroupType(),
				GroupType.SERVICE);
		assertEquals("Insert fail",
				"O processo implanta��o � previsto para 12 meses",
				insertedGroup.getObservation());

		insertedGroup.setName("Resistores");
		insertedGroup.setGroupType(GroupType.PRODUCT);

		GroupItem updatedGroup = testUpdate(insertedGroup);

		assertEquals("Update fail", insertedGroup.getName(), "Resistores");
		assertEquals("Update fail", insertedGroup.getGroupType(),
				GroupType.PRODUCT);

		testList(1);

		testDelete(updatedGroup);
	}
}
