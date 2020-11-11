package db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import dominio.categoriaEntidad.CategoriaEntidad;
import dominio.organizacion.EntidadBase;
import dominio.organizacion.EntidadJuridica;
import dominio.organizacion.Tipo;

public class TestPersistenciaEntidades extends AbstractPersistenceTest implements WithGlobalEntityManager {
	EntidadJuridica entidadJuridica;
	EntidadBase entidadBase;
	CategoriaEntidad categoria;
	
	@Before
	public void init() {
		categoria = new CategoriaEntidad("BlackFreedom");
		entidadJuridica = new EntidadJuridica("MySKL", "Boca",91218,"1425",1, Tipo.EmpresaMedianaTramo1, categoria);
		entidadBase = new EntidadBase("DDS","Curso de 3er anio de Ing. en Sistemas",entidadJuridica,categoria);
		
		entityManager().persist(entidadJuridica);
		entityManager().persist(entidadBase);
	}
	
	@After
	public void cleanup() {
		entityManager().remove(entidadJuridica);
		entityManager().remove(entidadBase);
	}
	
	@Test
	public void TestEntidades() {

		EntidadJuridica entidadBD1 = entityManager().find(EntidadJuridica.class, new Long(1));
		assertEquals(entidadJuridica, entidadBD1);
		assertNotNull(entidadBD1);
		
		EntidadBase entidadBD2 = entityManager().find(EntidadBase.class, new Long(2));
		assertEquals(entidadBase, entidadBD2);
		assertNotNull(entidadBD2);
	}
}
