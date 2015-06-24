package jstockenterprisefx.department;

import static org.junit.Assert.assertEquals;
import jstockenterprisefx.base.jpa.BaseJpaPersistenceTest;
import jstockenterprisefx.base.jpa.JpaEntityManager;

import org.junit.Test;

public class DepartmentJpaPersistenceTest extends
		BaseJpaPersistenceTest<Department, Short> {

	public DepartmentJpaPersistenceTest() {
		dao.set(new DepartmentDao(JpaEntityManager.getEntityManager()));
	}

	@Test
	public void testDepartmentCrud() {
		Department newDepartment = new Department("Suporte", "Vinícius Solo");

		Department insertedDepartment = testCreate(newDepartment);

		assertEquals("Insert fail", insertedDepartment.getName(), "Suporte");
		assertEquals("Insert fail", insertedDepartment.getPersonResponsible(),
				"Vinícius Solo");

		insertedDepartment.setName("Recursos Humanos");
		insertedDepartment.setPersonResponsible("Fernanda Karoline");

		Department updatedDepartment = testUpdate(insertedDepartment);

		assertEquals("Update fail", insertedDepartment.getName(),
				"Recursos Humanos");
		assertEquals("Update fail", insertedDepartment.getPersonResponsible(),
				"Fernanda Karoline");

		testList(1);

		testDelete(updatedDepartment);
	}
}
