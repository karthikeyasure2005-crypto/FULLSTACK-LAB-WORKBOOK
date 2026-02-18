package com.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;package com.hql;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.hql.model.Product;
import com.hql.util.HibernateUtil;

public class MainApp {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // Insert 6 Products
        session.persist(new Product("Laptop", "Electronics", 70000, 10));
        session.persist(new Product("Mouse", "Electronics", 500, 50));
        session.persist(new Product("Chair", "Furniture", 4000, 20));
        session.persist(new Product("Table", "Furniture", 8000, 5));
        session.persist(new Product("Pen", "Stationery", 20, 200));
        session.persist(new Product("Notebook", "Stationery", 60, 150));

        tx.commit();

        System.out.println("===== SORT BY PRICE ASC =====");
        Query<Product> sortAsc = session.createQuery(
                "FROM Product p ORDER BY p.price ASC", Product.class);

        sortAsc.getResultList()
               .forEach(p -> System.out.println(p.getName() + " - " + p.getPrice()));

        System.out.println("===== PAGINATION (FIRST 3) =====");
        Query<Product> pagination = session.createQuery(
                "FROM Product", Product.class);
        pagination.setFirstResult(0);
        pagination.setMaxResults(3);

        pagination.getResultList()
                  .forEach(p -> System.out.println(p.getName()));

        System.out.println("===== COUNT PRODUCTS =====");
        Long count = session.createQuery(
                "SELECT COUNT(p) FROM Product p", Long.class)
                .getSingleResult();

        System.out.println("Total Products: " + count);

        System.out.println("===== PRICE BETWEEN 1000 AND 10000 =====");
        Query<Product> range = session.createQuery(
                "FROM Product p WHERE p.price BETWEEN :min AND :max",
                Product.class);

        range.setParameter("min", 1000.0);
        range.setParameter("max", 10000.0);

        range.getResultList()
             .forEach(p -> System.out.println(p.getName()));

        System.out.println("===== NAME STARTS WITH 'L' =====");
        Query<Product> likeQuery = session.createQuery(
                "FROM Product p WHERE p.name LIKE 'L%'", Product.class);

        likeQuery.getResultList()
                 .forEach(p -> System.out.println(p.getName()));

        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}

import org.hibernate.query.Query;

import com.hql.model.Product;
import com.hql.util.HibernateUtil;

public class MainApp {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // Insert 6 Products
        session.persist(new Product("Laptop", "Electronics", 70000, 10));
        session.persist(new Product("Mouse", "Electronics", 500, 50));
        session.persist(new Product("Chair", "Furniture", 4000, 20));
        session.persist(new Product("Table", "Furniture", 8000, 5));
        session.persist(new Product("Pen", "Stationery", 20, 200));
        session.persist(new Product("Notebook", "Stationery", 60, 150));

        tx.commit();

        System.out.println("===== SORT BY PRICE ASC =====");
        Query<Product> sortAsc = session.createQuery(
                "FROM Product p ORDER BY p.price ASC", Product.class);

        sortAsc.getResultList()
               .forEach(p -> System.out.println(p.getName() + " - " + p.getPrice()));

        System.out.println("===== PAGINATION (FIRST 3) =====");
        Query<Product> pagination = session.createQuery(
                "FROM Product", Product.class);
        pagination.setFirstResult(0);
        pagination.setMaxResults(3);

        pagination.getResultList()
                  .forEach(p -> System.out.println(p.getName()));

        System.out.println("===== COUNT PRODUCTS =====");
        Long count = session.createQuery(
                "SELECT COUNT(p) FROM Product p", Long.class)
                .getSingleResult();

        System.out.println("Total Products: " + count);

        System.out.println("===== PRICE BETWEEN 1000 AND 10000 =====");
        Query<Product> range = session.createQuery(
                "FROM Product p WHERE p.price BETWEEN :min AND :max",
                Product.class);

        range.setParameter("min", 1000.0);
        range.setParameter("max", 10000.0);

        range.getResultList()
             .forEach(p -> System.out.println(p.getName()));

        System.out.println("===== NAME STARTS WITH 'L' =====");
        Query<Product> likeQuery = session.createQuery(
                "FROM Product p WHERE p.name LIKE 'L%'", Product.class);

        likeQuery.getResultList()
                 .forEach(p -> System.out.println(p.getName()));

        session.close();
        HibernateUtil.getSessionFactory().close();
    }
}
