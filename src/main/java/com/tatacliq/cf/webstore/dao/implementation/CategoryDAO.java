package com.tatacliq.cf.webstore.dao.implementation;

import com.tatacliq.cf.webstore.dao.interfaces.ICategoryDAO;
import com.tatacliq.cf.webstore.dao.interfaces.IProductDAO;
import com.tatacliq.cf.webstore.domain.entities.Category;
import com.tatacliq.cf.webstore.domain.entities.Product;
import com.tatacliq.cf.webstore.helper.PostgresConnHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static java.sql.Types.NULL;

public class CategoryDAO implements ICategoryDAO {
    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResourceBundle resourceBundle;

    public CategoryDAO() throws SQLException {
        conn = PostgresConnHelper.getConnection();
        conn.setAutoCommit(false);
        resourceBundle = ResourceBundle.getBundle("db");
    }

    public void addNewCategory(Category category) throws SQLException {
        String query = resourceBundle.getString("addCategory");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, category.getCategoryId());
        preparedStatement.setString(2, category.getName());
        preparedStatement.setString(3, category.getDescription());
        preparedStatement.setLong(4, category.getSubCategory() != null ? category.getSubCategory().getCategoryId() : NULL);
        preparedStatement.executeUpdate();
        conn.commit();
    }

    private void addNewProduct(Product product) throws SQLException {
        String query = resourceBundle.getString("addProduct");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, product.getProductId());
        preparedStatement.setString(2, product.getName());
        preparedStatement.setString(3, product.getDescription());
        preparedStatement.setDouble(4, product.getCost());
        preparedStatement.setLong(5, product.getUnits());
        preparedStatement.executeUpdate();
        conn.commit();
    }

    @Override
    public void addProduct(long category_id, Product product) throws SQLException {
        addNewProduct(product);

        String query = resourceBundle.getString("addCategoryProduct");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, category_id);
        preparedStatement.setLong(2, product.getProductId());
        preparedStatement.executeUpdate();
        conn.commit();
    }

    @Override
    public void addSubCategory(long category_id, Category category) throws SQLException {
        addNewCategory(category);

        String query = resourceBundle.getString("addSubCategory");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, category.getSubCategory().getCategoryId());
        preparedStatement.setLong(2, category_id);
        preparedStatement.executeUpdate();
        conn.commit();
    }

    @Override
    public Category getCategory(long categoryId) throws SQLException {
        ResultSet resultSet;
        Category category = null;
        String query = resourceBundle.getString("getCategory");
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, categoryId);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            category = new Category();
            category.setCategoryId(resultSet.getLong(1));
            category.setName(resultSet.getString(2));
            category.setDescription(resultSet.getString(3));
            category.setSubCategory(getCategory(resultSet.getLong(4)));
        }
        return category;
    }

    @Override
    public List<Product> getProducts(long category_id) throws SQLException {
        ResultSet resultSet;

        IProductDAO productDAO = new ProductDAO();
        Product product;
        List<Product> products = new ArrayList<>();
        String query = resourceBundle.getString("getCategoryProducts");
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, category_id);
        resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {
            product = productDAO.getProduct(resultSet.getLong(2));
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Category> getSubCategories(long category_id) {
        return null;
    }

    @Override
    public boolean hasProducts(long categoryId) throws SQLException {
        ResultSet resultSet;
        String query = resourceBundle.getString("hasProducts");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, categoryId);
        resultSet = preparedStatement.executeQuery();
        long productCount = 0;
        while(resultSet.next()) {
            productCount = resultSet.getInt(1);
        }
        return productCount > 0;
    }

    @Override
    public boolean hasSubCategories() throws SQLException {
        ResultSet resultSet;
        String query = resourceBundle.getString("hasSubCategories");

        preparedStatement = conn.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        long productCount = 0;
        while(resultSet.next()) {
            productCount = resultSet.getInt(1);
        }
        return productCount > 0;
    }

    private void deleteProduct(long product_id) throws SQLException {
        String query = resourceBundle.getString("removeProduct");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, product_id);
        preparedStatement.executeUpdate();
        conn.commit();
    }

    @Override
    public void removeProduct(long product_id) throws SQLException {
        deleteProduct(product_id);
        String query = resourceBundle.getString("removeCategoryProduct");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setLong(1, product_id);
        preparedStatement.executeUpdate();
        conn.commit();
    }

    @Override
    public void updateCategory(Category category) throws SQLException {
        String query = resourceBundle.getString("updateCategory");

        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, category.getName());
        preparedStatement.setString(2, category.getDescription());
        preparedStatement.setLong(3, category.getSubCategory() != null ? category.getSubCategory().getCategoryId() : NULL);
        preparedStatement.setLong(4, category.getCategoryId());
        preparedStatement.executeUpdate();
        conn.commit();
    }
}
