package com.github.truongbb.hibernate.bus.driver.management.repository;

import com.github.truongbb.hibernate.bus.driver.management.entity.BusLine;
import com.github.truongbb.hibernate.bus.driver.management.util.DataUtil;
import com.github.truongbb.hibernate.bus.driver.management.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class BusLineRepository {

    public List<BusLine> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from BusLine", BusLine.class).list();
    }

    public void saveAll(List<BusLine> busLines) {
        if (DataUtil.isEmptyCollection(busLines)) {
            return;
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        try {
            for (BusLine busLine : busLines) {
                session.save(busLine);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
    }

    public BusLine findById(int busLineId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("from BusLine where id = :p_id", BusLine.class)
                .setParameter("p_id", busLineId)
                .getSingleResult();

    }
}
