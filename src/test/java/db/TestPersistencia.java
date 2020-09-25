package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.item.Item;
import dominio.organizacion.Entidad;
import dominio.organizacion.EntidadBase;
import dominio.organizacion.EntidadJuridica;
import dominio.organizacion.Tipo;

public class TestPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {
	Item item1;
	Item item2;
	EntidadJuridica entidadJuridica;
	EntidadBase entidadBase;
	CategoriaEntidad categoria;
	
	@Before
	public void init() {
		item1=new Item(100, "carne", null);
		item2=new Item(50, "polenta",null);
		categoria = new CategoriaEntidad("BlackFreedom");
		entidadJuridica=new EntidadJuridica("MySKL", 31,91218,"1425",1, Tipo.EmpresaMedianaTramo1, categoria);
		entidadBase = new EntidadBase("DDS","Curso de 3º anio de Ing. en Sistemas",entidadJuridica,categoria);
	
		entityManager().persist(item1);
		entityManager().persist(item2);
	
		entityManager().persist(entidadJuridica);
		entityManager().persist(entidadBase);
	}
	//EntidadBase entidadBase=new EntidadBase("DDS","Curso de 3º anio de Ing. en Sistemas",entidadJuridica,null);
	
	@Test 
	public void TestItem() {
		
		assertNotEquals(item1.getId(), item2.getId());
	}
	@Test
	public void TestEntidad() {
		//entidadBase=new EntidadBase("DDS","Curso de 3º anio de Ing. en Sistemas",entidadJuridica,null);
		//entityManager().persist(entidadBase);
		Entidad entidadBD=entityManager().find(Entidad.class, new Long(1));
		assertEquals(entidadBD, entidadJuridica);
		assertNotNull(entidadBD);
	}
	
	@Test
	public void TestEntidadBase() {
		//entidadBase=new EntidadBase("DDS","Curso de 3º anio de Ing. en Sistemas",entidadJuridica,null);
		//entityManager().persist(entidadBase);
		Entidad entidadBD=entityManager().find(Entidad.class, new Long(2));
		assertEquals(entidadBD, entidadBase);
		assertNotNull(entidadBD);
	}
}
