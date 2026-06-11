/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.equipo1.servlets;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
/**
 *
 * @author XPxTBxLLX
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[AppContextListener] Aplicación iniciada.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 1. Detener el thread de limpieza de conexiones abandonadas de MySQL
        try {
            AbandonedConnectionCleanupThread.checkedShutdown();
            System.out.println("[AppContextListener] AbandonedConnectionCleanupThread detenido.");
        } catch (Exception e) {
            System.out.println("[AppContextListener] Error deteniendo cleanup thread: " + e.getMessage());
        }

        // 2. Desregistrar drivers JDBC para evitar memory leak
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("[AppContextListener] Driver desregistrado: " + driver);
            } catch (SQLException e) {
                System.out.println("[AppContextListener] Error desregistrando driver: " + e.getMessage());
            }
        }

        System.out.println("[AppContextListener] Aplicación detenida limpiamente.");
    }
}
