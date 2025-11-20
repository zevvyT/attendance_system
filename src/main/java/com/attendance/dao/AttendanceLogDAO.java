package com.attendance.dao;

import com.attendance.model.AttendanceLog;
import com.attendance.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

public class AttendanceLogDAO {
    
    public AttendanceLog findByEmployeeAndDate(Long employeeId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<AttendanceLog> query = session.createQuery(
                "FROM AttendanceLog WHERE employee.employeeId = :employeeId AND date = :date", 
                AttendanceLog.class);
            query.setParameter("employeeId", employeeId);
            query.setParameter("date", date);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<AttendanceLog> findByDateRange(Long employeeId, LocalDate startDate, LocalDate endDate) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<AttendanceLog> query = session.createQuery(
                "FROM AttendanceLog WHERE employee.employeeId = :employeeId " +
                "AND date BETWEEN :startDate AND :endDate ORDER BY date DESC", 
                AttendanceLog.class);
            query.setParameter("employeeId", employeeId);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public List<AttendanceLog> findAllByDate(LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<AttendanceLog> query = session.createQuery(
                "FROM AttendanceLog WHERE date = :date", AttendanceLog.class);
            query.setParameter("date", date);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean save(AttendanceLog log) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(log);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(AttendanceLog log) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(log);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
}
