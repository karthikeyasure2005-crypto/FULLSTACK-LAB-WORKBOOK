package com.inventory.main;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

public class InventoryApp {

    public static void main(String[] args) {
        createProduct("Laptop", "High-performance gaming laptop", 1200.00, 15);
        createProduct("Smartphone", "Latest model with OLED display", 800.00, 25);
        createProduct("Headphones", "Noise-cancelling wireless headphones", 150.00, 50);

        Product myProduct = getProductById(1);
        if (myProduct != null) {
            System.out.println("Retrieved: " + myProduct.getName());
        }

        updateProduct(1, 1100.00, 12);
        deleteProduct(2);
        displayAllProducts();

        HibernateUtil.shutdown();
    }

    public static void createProduct(String name, String desc, double price, int qty) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(new Product(name, desc, price, qty));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public static Product getProductById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Product.class, id);
        }
    }

    public static void updateProduct(int id, double newPrice, int newQty) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                product.setPrice(newPrice);
                product.setQuantity(newQty);
                session.update(product);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public static void deleteProduct(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);
            if (product != null) {
                session.delete(product);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public static void displayAllProducts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Product> products = session.createQuery("from Product", Product.class).list();
            for (Product p : products) {
                System.out.println(p.getId() + ": " + p.getName() + " - $" + p.getPrice());
            }
        }
    }
}