package com.github.truongbb.hibernate.bus.driver.management.repository;

import com.github.truongbb.hibernate.bus.driver.management.entity.Driver;
import com.github.truongbb.hibernate.bus.driver.management.util.DataUtil;
import com.github.truongbb.hibernate.bus.driver.management.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class DriverRepository {

    public List<Driver> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from Driver", Driver.class).list();
    }

    public void saveAll(List<Driver> drivers) {
        if (DataUtil.isEmptyCollection(drivers)) {
            return;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            for (Driver driver : drivers) {
                session.save(driver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
    }

    public Driver findById(int driverId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from Driver where id = :p_id", Driver.class)
                .setParameter("p_id", (long) driverId)
                .getSingleResult();
    }
}
