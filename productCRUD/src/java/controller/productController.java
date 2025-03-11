package controller;

import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.product;
import service.productService;

@Path("/products")
public class productController {   
    private final productService ps = new productService();
    
    // create new product
    @Path("/createProduct")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)  
    public Response createProduct(product item){
        try {
            String result = ps.createProduct(item);
            return Response.status(Response.Status.CREATED).entity(result).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error Creating Product").build();
        }
    }
    
    // get all product
    @Path("/getProduct")
    @GET
    @Produces(MediaType.APPLICATION_JSON)  
    public Response getProduct(){
        try {
            List<product> items = ps.getALLProducts();
            
            return Response.ok(items, MediaType.APPLICATION_JSON).build();
            
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error Loading Product").build();
        }
    }
    
    // delete product
    @Path("deleteProduct/{id}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)  
    public Response deleteProduct(@PathParam("id")int id){
        try {
            String result = ps.deleteProduct(id);
            return Response.status(Response.Status.OK).entity(result).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error Deleting Product").build();
        }
    }
    
    // update product
    @Path("updateProduct/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    public Response updateProduct(@PathParam("id")int id, product item){
        try {
            String result = ps.updateProduct(id, item);
            return Response.status(Response.Status.OK).entity(result).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error Updating Product").build();
        }
    }
}
