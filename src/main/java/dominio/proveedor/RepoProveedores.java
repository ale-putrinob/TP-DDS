package dominio.proveedor;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepoProveedores implements WithGlobalEntityManager{
	private final static RepoProveedores INSTANCE = new RepoProveedores();
	 
	public static RepoProveedores getInstance() {
		return INSTANCE;
	}	
	
	@SuppressWarnings("unchecked")
	public List<Proveedor> getProveedor(){
		return entityManager().createQuery("from Proveedor").getResultList();
	}
	
	@SuppressWarnings("deprecation")
	public Proveedor findProveedor(String id) {
		return entityManager().find(Proveedor.class,new Long(id));
	}

	public void agregarProveedor(Proveedor proveedor) {
		entityManager().persist(proveedor);
	}
}
