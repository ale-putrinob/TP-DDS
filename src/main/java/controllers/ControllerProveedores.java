package controllers;

import dominio.proveedor.Proveedor;
import dominio.proveedor.RepoProveedores;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControllerProveedores implements WithGlobalEntityManager, TransactionalOps {
    public  ModelAndView obtenerProveedor(Request req, Response res) {
        return new ModelAndView(null, "cargarProveedor.hbs");
    }

    public ModelAndView cargarProveedores(Request req, Response res) {

        String nombreApellido = req.queryParams("nombreYApellido");
        String razonSocial = req.queryParams("razonSocial");
        Integer dni = new Integer(req.queryParams("DNI"));
        Integer cuil = new Integer(req.queryParams("CUIT"));
        String ciudad = req.queryParams("id_ciudad");
        String calle = req.queryParams("calle");
        Integer altura = new Integer(req.queryParams("altura"));
        Integer piso = new Integer(req.queryParams("piso"));
        char departamento = (req.queryParams("departamento")).charAt(0);



        Proveedor proveedor = new Proveedor(nombreApellido, razonSocial, dni, cuil, ciudad, calle, altura, piso, departamento);

        withTransaction(() ->{
            RepoProveedores.getInstance().agregarProveedor(proveedor);
        });

        res.redirect("/home");
        return null;
    }

}