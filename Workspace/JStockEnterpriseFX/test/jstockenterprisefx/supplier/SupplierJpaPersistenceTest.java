package jstockenterprisefx.supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import jstockenterprisefx.base.jpa.JpaEntityManager;

import org.junit.BeforeClass;
import org.junit.Test;

public class SupplierJpaPersistenceTest {
	private static JpaEntityManager jpa = new JpaEntityManager();
	private static SupplierDao dao;

	@BeforeClass
	public static void beforeClass() {
		dao = new SupplierDao(jpa.getEntityManager());
	}

	@Test
	public void testSupplierCrud() {
		Supplier newSupplier = new Supplier("The Creative Shop",
				"The Creative Shop S.A.", "14877648000155", "21393054");

		jpa.beginTransaction();
		dao.create(newSupplier);
		jpa.commit();

		assertNotNull("Created entity returns null ID", newSupplier.getId());
		final Integer newId = newSupplier.getId();

		Supplier supplierInserted = dao.read(newId);

		assertEquals(supplierInserted.getId(), newId);
		assertEquals("Insert fail", supplierInserted.getCompanyName(),
				"The Creative Shop");
		assertEquals("Insert fail", supplierInserted.getTradingName(),
				"The Creative Shop S.A.");
		assertEquals("Insert fail", supplierInserted.getCnpj(),
				"14877648000155");
		assertEquals("Insert fail", supplierInserted.getCep(), "21393054");

		supplierInserted.setCompanyName("APN Publicidade");
		supplierInserted.setTradingName("APN Publicidade Lda");
		supplierInserted.setCnpj("25183681000100");
		supplierInserted.setCep("12148608");

		jpa.beginTransaction();
		dao.update(supplierInserted);
		jpa.commit();

		Supplier supplierUpdated = dao.read(newId);

		assertEquals(supplierInserted.getId(), newId);
		assertEquals("Update fail", supplierInserted.getCompanyName(),
				"APN Publicidade");
		assertEquals("Update fail", supplierInserted.getTradingName(),
				"APN Publicidade Lda");
		assertEquals("Update fail", supplierInserted.getCnpj(),
				"25183681000100");
		assertEquals("Update fail", supplierInserted.getCep(), "12148608");

		jpa.beginTransaction();
		dao.delete(supplierUpdated);
		jpa.commit();

		Supplier supplierDeleted = dao.read(newId);
		assertNull("Delete fail", supplierDeleted);
	}
}
