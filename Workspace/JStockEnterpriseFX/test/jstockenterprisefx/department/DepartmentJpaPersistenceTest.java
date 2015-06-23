package jstockenterprisefx.department;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import jstockenterprisefx.base.jpa.JpaEntityManager;

import org.junit.BeforeClass;
import org.junit.Test;

public class DepartmentJpaPersistenceTest {
	private static JpaEntityManager jpa = new JpaEntityManager();
	private static DepartmentDao dao;

	@BeforeClass
	public static void beforeClass() {
		dao = new DepartmentDao(jpa.getEntityManager());
	}

	@Test
	public void testDepartmentCrud() {
		Department newDepartment = new Department("Suporte", "Vinícius Solo");

		jpa.beginTransaction();
		dao.create(newDepartment);
		jpa.commit();

		assertNotNull("Created entity returns null ID", newDepartment.getId());
		final Short newId = newDepartment.getId();

		Department departmentInserted = dao.read(newId);

		assertEquals(departmentInserted.getId(), newId);
		assertEquals("Insert fail", departmentInserted.getName(), "Suporte");
		assertEquals("Insert fail", departmentInserted.getPersonResponsible(),
				"Vinícius Solo");

		departmentInserted.setName("Recursos Humanos");
		departmentInserted.setPersonResponsible("Fernanda Karoline");

		jpa.beginTransaction();
		dao.update(departmentInserted);
		jpa.commit();

		Department departmentUpdated = dao.read(newId);

		assertEquals(departmentUpdated.getId(), newId);
		assertEquals("Update fail", departmentInserted.getName(), "Recursos Humanos");
		assertEquals("Update fail", departmentInserted.getPersonResponsible(),
				"Fernanda Karoline");

		jpa.beginTransaction();
		dao.delete(departmentUpdated);
		jpa.commit();

		Department groupDeleted = dao.read(newId);
		assertNull("Delete fail", groupDeleted);
	}
}
