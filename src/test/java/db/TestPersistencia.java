package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.item.Item;
import dominio.organizacion.Entidad;
import dominio.organizacion.EntidadBase;
import dominio.organizacion.EntidadJuridica;
import junit.framework.Assert;

public class TestPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {
	Item item1;
	Item item2;
	EntidadJuridica entidadJuridica;
	//EntidadBase entidadBase;
	//CategoriaEntidad categoria;
	
	@Before
	public void init() {
		item1=new Item(100, "carne", null);
		item2=new Item(50, "polenta",null);
		entidadJuridica=new EntidadJuridica("MySKL", 31,91218,"1425");
		//entidadBase = new EntidadBase("DDS","Curso de 3º anio de Ing. en Sistemas",entidadJuridica,categoria);
	}
	//EntidadBase entidadBase=new EntidadBase("DDS","Curso de 3º anio de Ing. en Sistemas",entidadJuridica,null);
	@Test 
	public void TestItem() {
		entityManager().persist(item1);
		entityManager().persist(item2);
		
		assertNotEquals(item1.getId(), item2.getId());
	}
	@Test
	public void TestEntidad() {
		//entidadBase=new EntidadBase("DDS","Curso de 3º anio de Ing. en Sistemas",entidadJuridica,null);
		//entityManager().persist(entidadBase);
		entityManager().persist(entidadJuridica);
		Entidad entidadBD=entityManager().find(Entidad.class, new Long(1));
		assertEquals(entidadBD, entidadJuridica);
		assertNotNull(entidadBD);
	}
}
