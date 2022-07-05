package com.github.truongbb.hibernate.bus.driver.management.repository;

import com.github.truongbb.hibernate.bus.driver.management.entity.DriverBusManagement;
import com.github.truongbb.hibernate.bus.driver.management.util.DataUtil;
import com.github.truongbb.hibernate.bus.driver.management.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class DriverBusManagementRepository {


    public List<DriverBusManagement> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from DriverBusManagement", DriverBusManagement.class).list();
    }

    public void saveAll(List<DriverBusManagement> toEntity) {
        if (DataUtil.isEmptyCollection(toEntity)) {
            return;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            for (DriverBusManagement driverBusManagement : toEntity) {
                session.save(driverBusManagement);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();

    }
}
