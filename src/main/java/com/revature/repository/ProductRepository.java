package com.revature.repository;

import com.revature.model.Product;
import com.revature.util.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository implements DAO<Product, Integer> {
    private static final Logger log = LoggerFactory.getLogger(ProductRepository.class);
    @Override
    public Optional<Product> create(Product product) {
        Optional<Product> optionalProduct = Optional.empty();

        try(Connection connection = ConnectionManager.getConnection()){
            String sql = "insert into products(department_id, name, description, price, quantity) values(?,?,?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, product.getDepartment().ordinal());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getQuantity());

            int affectedRows = stmt.executeUpdate();

            if(affectedRows == 0) throw new SQLException("Create product failed, no rows affected.");

            try(ResultSet generatedKeys = stmt.getGeneratedKeys()){
                if(generatedKeys.next()){
                    optionalProduct = getById(generatedKeys.getInt(1));
                }else{
                    throw new SQLException("Create product failed, no rows affected.");
                }
            }

        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return optionalProduct;
    }

    @Override
    public Optional<Product> getById(Integer id) {
        Optional<Product> optionalProduct = Optional.empty();

        try(Connection connection = ConnectionManager.getConnection()){
            String sql = "select * from products where id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                optionalProduct = Optional.of(new Product()
                        .setId(rs.getInt(1))
                        .setDepartment(Product.Department.values()[rs.getInt(2)])
                        .setName(rs.getString(3))
                        .setDescription(rs.getString(4))
                        .setPrice(rs.getDouble(5))
                        .setQuantity(rs.getInt(6))
                );
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return optionalProduct;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        try(Connection connection = ConnectionManager.getConnection()){
            String sql = "select * from products order by id";
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                products.add(new Product()
                        .setId(rs.getInt(1))
                        .setDepartment(Product.Department.values()[rs.getInt(2)])
                        .setName(rs.getString(3))
                        .setDescription(rs.getString(4))
                        .setPrice(rs.getDouble(5))
                        .setQuantity(rs.getInt(6))
                );
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }

        return products;
    }

    @Override
    public Optional<Product> updateById(Product product, Integer id) {
        Optional<Product> optionalProduct = Optional.empty();

        try(Connection connection = ConnectionManager.getConnection()) {
            String sql = "update products set department_id = ?, name = ?," +
                    "description = ?, price = ?, quantity = ? where id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, product.getDepartment().ordinal());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getQuantity());
            stmt.setInt(6, id);

            int affectedRows = stmt.executeUpdate();

            if(affectedRows == 0) throw new SQLException("Update product failed, no rows affected.");

            optionalProduct = getById(id);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return optionalProduct;
    }

    @Override
    public Optional<Product> deleteById(Integer id) {
        Optional<Product> optionalProduct = getById(id);

        try(Connection connection = ConnectionManager.getConnection()) {
            if(!optionalProduct.isPresent()) return optionalProduct;

            String sql = "delete from products where id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) throw new SQLException("Delete product failed, no rows affected.");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return optionalProduct;
    }
}
