package service;

import dao.productDAO;
import java.sql.SQLException;
import java.util.List;
import model.product;

public class productService {
    
    private productDAO productdAO;
    
    public productService(){
        productdAO = new productDAO();
    }
    
    public String createProduct(product product) throws SQLException{
        String result = productdAO.createProduct(product);
        return result;
    }
    
    public List<product> getALLProducts() throws SQLException{
        return productdAO.getALLProduct();
    }
    
    public String deleteProduct(int id) throws SQLException{
        String result = productdAO.deleteProduct(id);
        return result;
    }
    
    public String updateProduct(int id,product item) throws SQLException{
        String result = productdAO.updateProduct(id, item);
        return result;
    }
    
}
