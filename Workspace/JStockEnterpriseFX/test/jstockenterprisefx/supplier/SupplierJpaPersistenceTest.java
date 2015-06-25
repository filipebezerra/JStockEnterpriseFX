package jstockenterprisefx.supplier;

import static org.junit.Assert.assertEquals;
import jstockenterprisefx.base.jpa.BaseJpaPersistenceTest;
import jstockenterprisefx.base.jpa.JpaEntityManager;
import jstockenterprisefx.base.model.Uf;

import org.junit.Test;

public class SupplierJpaPersistenceTest extends
		BaseJpaPersistenceTest<Supplier, Integer> {

	public SupplierJpaPersistenceTest() {
		dao.set(new SupplierDao(JpaEntityManager.getEntityManager()));
	}

	@Test
	public void testSupplierCrud() {
		Supplier newSupplier = new Supplier("The Creative Shop",
				"The Creative Shop S.A.", "14877648000155", "Av. R2",
				"Bairro Conceição", "São Paulo", Uf.SP, "21393054");

		Supplier insertedSupplier = testCreate(newSupplier);

		assertEquals("Insert fail", insertedSupplier.getCompanyName(),
				"The Creative Shop");
		assertEquals("Insert fail", insertedSupplier.getTradingName(),
				"The Creative Shop S.A.");
		assertEquals("Insert fail", insertedSupplier.getCnpj(),
				"14877648000155");
		assertEquals("Insert fail", insertedSupplier.getPublicArea(), "Av. R2");
		assertEquals("Insert fail", insertedSupplier.getDistrict(),
				"Bairro Conceição");
		assertEquals("Insert fail", insertedSupplier.getCity(), "São Paulo");
		assertEquals("Insert fail", insertedSupplier.getUf(), Uf.SP);
		assertEquals("Insert fail", insertedSupplier.getCep(), "21393054");

		insertedSupplier.setCompanyName("APN Publicidade");
		insertedSupplier.setTradingName("APN Publicidade Lda");
		insertedSupplier.setCnpj("25183681000100");
		insertedSupplier.setCep("74691852");
		insertedSupplier.setCity("Goiânia");
		insertedSupplier.setUf(Uf.GO);

		Supplier updatedSupplier = testUpdate(insertedSupplier);

		assertEquals("Update fail", insertedSupplier.getCompanyName(),
				"APN Publicidade");
		assertEquals("Update fail", insertedSupplier.getTradingName(),
				"APN Publicidade Lda");
		assertEquals("Update fail", insertedSupplier.getCnpj(),
				"25183681000100");
		assertEquals("Update fail", insertedSupplier.getCity(), "Goiânia");
		assertEquals("Update fail", insertedSupplier.getUf(), Uf.GO);
		assertEquals("Update fail", insertedSupplier.getCep(), "74691852");

		testList(1);

		testDelete(updatedSupplier);
	}
}
