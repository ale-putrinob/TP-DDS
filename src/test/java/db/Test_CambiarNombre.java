package db;

import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import dominio.item.Item;
import junit.framework.Assert;

public class Test_CambiarNombre extends AbstractPersistenceTest implements WithGlobalEntityManager {
	Item item1=new Item(100, "carne", null);
	Item item2=new Item(50, "polenta",null);
	@SuppressWarnings("deprecation")
	@Test 
	public void ItemTest() {
		entityManager().persist(item1);
		entityManager().persist(item2); 
		
		assertNotEquals(item1.getId(), item2.getId());
	}
}
