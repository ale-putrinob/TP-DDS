package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import dominio.item.Item;

public class TestPersistenciaItems extends AbstractPersistenceTest implements WithGlobalEntityManager {
	Item item1;
	Item item2;

	@Before
	public void init() {
		item1 = new Item(100, "carne", null);
		item2 = new Item(50, "polenta",null);
		entityManager().persist(item1);
		entityManager().persist(item2); 
	}
	
	@After
	public void cleanup() {
		entityManager().remove(item1);
		entityManager().remove(item2);
	}

	@Test 
	public void TestItems() {

		Item item1DesdeBD = entityManager().find(Item.class, new Long(1));
		Item item2DesdeBD = entityManager().find(Item.class, new Long(2));

		assertEquals(item1.getValorItem(),item1DesdeBD.getValorItem(),0);
		assertTrue(item1.tieneTipo(item1DesdeBD.getTipo()));
		assertEquals(item2.getValorItem(),item2DesdeBD.getValorItem(),0);
		assertTrue(item2.tieneTipo(item2DesdeBD.getTipo()));
		
		assertEquals(item1,item1DesdeBD);
		assertEquals(item2,item2DesdeBD);
		assertNotNull(item1DesdeBD);
		assertNotNull(item2DesdeBD);
	}
}