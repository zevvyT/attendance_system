package com.attendance.listener;

import com.attendance.util.HibernateUtil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application lifecycle listener to initialize Hibernate on startup
 */
@WebListener
public class HibernateInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("=== Initializing Hibernate SessionFactory ===");
        try {
            // This will trigger the static block in HibernateUtil
            // which creates the SessionFactory and initializes the database schema
            HibernateUtil.getSessionFactory();
            System.out.println("=== Hibernate SessionFactory initialized successfully ===");
            System.out.println("=== Database tables should now be created ===");
        } catch (Exception e) {
            System.err.println("=== Failed to initialize Hibernate SessionFactory ===");
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("=== Shutting down Hibernate SessionFactory ===");
        HibernateUtil.shutdown();
        System.out.println("=== Hibernate SessionFactory shut down successfully ===");
    }
}
