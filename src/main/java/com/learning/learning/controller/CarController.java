package com.learning.learning.controller;



import com.learning.learning.model.Car;
import com.learning.learning.model.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CarController {

    public void saveCar(Car car) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(car);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void updateCar(Car car) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(car);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteCar(Car car) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(car);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public ObservableList<Car> getAllCars() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Car> cars = session.createQuery("from Car", Car.class).list();
            return FXCollections.observableArrayList(cars);
        }
    }

    public ObservableList<Car> filterCarsByModel(String model) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Car> cars = session.createQuery("from Car where model = :model", Car.class)
                    .setParameter("model", model)
                    .list();
            return FXCollections.observableArrayList(cars);
        }
    }
}
