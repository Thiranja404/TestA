package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.product;

public class productDAO {
    
    private Connection getConnection () throws SQLException{
                   
            try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://N7\\SQLEXPRESS2022;databaseName=ProductDB;encrypt=true;trustServerCertificate=true;";
            String uname = "sa";
            String pass = "1234";    
            
            return DriverManager.getConnection(url,uname,pass);
        } catch (ClassNotFoundException | SQLException e) {
        }
        return null;
        
}

    public String createProduct(product product) throws SQLException {
        productDAO dao = new productDAO(); 
        String result = "Product Created";
        try {
            String query = "insert into product (name,price,quantity) values(?,?,?)";
            PreparedStatement prd = dao.getConnection().prepareStatement(query);
            prd.setString(1, product.getName());
            prd.setDouble(2, product.getPrice());
            prd.setInt(3, product.getQuantity());
            
            int executeUpdate = prd.executeUpdate();
            if(executeUpdate > 0){
                result = "Product Created Successfully";
            }
            
        } catch (SQLException e) {
            result = "Product Create Error";
        }
        dao.getConnection().close();
        return result;
        
    }

    public List<product> getALLProduct() {
        productDAO dao = new productDAO();
        List<product> productList = new ArrayList<>();
        
        
        try {
            String query = "select * from product";
        
        PreparedStatement psd = dao.getConnection().prepareStatement(query);
        
         ResultSet rs = psd.executeQuery();
        
         while(rs.next()){
             product items = new product();
             items.setId(rs.getInt("id"));
             items.setName(rs.getString("name"));
             items.setPrice(rs.getDouble("price"));
             items.setQuantity(rs.getInt("quantity"));
             productList.add(items);
         }
         
         dao.getConnection().close();
         return productList;
        
        } catch (SQLException e) {
        }
        
        return null;
                
    }

    public String deleteProduct(int id) throws SQLException {
    productDAO dAO = new productDAO();
    String deleteMessage = "delete successful";
    
    try {
        PreparedStatement prs = dAO.getConnection().prepareStatement("DELETE FROM product WHERE id = ?");
        prs.setInt(1, id);
        
        int result = prs.executeUpdate();
        if (result <= 0) {
            deleteMessage = "Error deleting product. Product not found.";
        }
    } catch (SQLException e) {
        deleteMessage = "Product ID Not Found";
    }
    dAO.getConnection().close();
    return deleteMessage;
}

    
    public String updateProduct(int id,product item) throws SQLException {
        productDAO dAO = new productDAO();
        String updateMessage = "Product Updated Successfully";
        try {
            
            String query = "UPDATE product SET [name] = ?, [price] = ?, [quantity] = ? WHERE [id] = ?";
            PreparedStatement prs = dAO.getConnection().prepareStatement(query);
            prs.setString(1, item.getName());
            prs.setDouble(2, item.getPrice());
            prs.setInt(3, item.getQuantity());
            prs.setInt(4, id);
            
            int result = prs.executeUpdate();
            if (result <= 0) {
            updateMessage = "Error Updating product. Product not found.";
        }
    } catch (SQLException e) {
            System.out.println(e.getMessage());
        updateMessage = "Product ID Not Found";
    }
    dAO.getConnection().close();
    return updateMessage;  
    }
    
}