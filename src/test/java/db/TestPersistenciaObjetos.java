package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import dominio.item.Item;

public class TestPersistenciaObjetos extends AbstractPersistenceTest implements WithGlobalEntityManager {
	Item item1=new Item(100, "carne", null);
	Item item2=new Item(50, "polenta",null);
	
	@Before
	public void init() {
	entityManager().persist(item1);
	entityManager().persist(item2); 
	}
	
	@Test 
	public void Test() {
		
		Item item1DesdeBD = entityManager().find(Item.class, new Long(1));
		Item item2DesdeBD = entityManager().find(Item.class, new Long(2));
		
		assertEquals(item1.getValorItem(),item1DesdeBD.getValorItem(),0);
		assertTrue(item1.tieneTipo(item1DesdeBD.getTipo()));
		assertEquals(item2.getValorItem(),item2DesdeBD.getValorItem(),0);
		assertTrue(item2.tieneTipo(item2DesdeBD.getTipo()));
	}
}
