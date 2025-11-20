package com.attendance.service;

import com.attendance.dao.AttendanceLogDAO;
import com.attendance.dao.EmployeeDAO;
import com.attendance.model.AttendanceLog;
import com.attendance.model.Employee;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class AttendanceService {
    
    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private AttendanceLogDAO attendanceLogDAO = new AttendanceLogDAO();
    
    public Map<String, Object> signIn(String cardId) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Find employee by card ID
            Employee employee = employeeDAO.findByCardId(cardId);
            
            if (employee == null) {
                response.put("success", false);
                response.put("message", "Invalid Card ID");
                return response;
            }
            
            // Check if already signed in today
            LocalDate today = LocalDate.now();
            AttendanceLog existingLog = attendanceLogDAO.findByEmployeeAndDate(
                employee.getEmployeeId(), today);
            
            if (existingLog != null && existingLog.getSignInTime() != null) {
                response.put("success", false);
                response.put("message", "Already signed in today at " + existingLog.getSignInTime());
                return response;
            }
            
            // Create new attendance log
            AttendanceLog log = new AttendanceLog();
            log.setEmployee(employee);
            log.setDate(today);
            log.setSignInTime(LocalTime.now());
            log.setStatus(AttendanceLog.AttendanceStatus.PRESENT);
            log.setLocation("Office");
            
            boolean saved = attendanceLogDAO.save(log);
            
            if (saved) {
                response.put("success", true);
                response.put("message", "Sign-in successful!");
                response.put("employeeName", employee.getName());
                response.put("time", log.getSignInTime().toString());
            } else {
                response.put("success", false);
                response.put("message", "Failed to record sign-in");
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
            e.printStackTrace();
        }
        
        return response;
    }
    
    public Map<String, Object> signOut(String cardId) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            // Find employee by card ID
            Employee employee = employeeDAO.findByCardId(cardId);
            
            if (employee == null) {
                response.put("success", false);
                response.put("message", "Invalid Card ID");
                return response;
            }
            
            // Find today's attendance log
            LocalDate today = LocalDate.now();
            AttendanceLog log = attendanceLogDAO.findByEmployeeAndDate(
                employee.getEmployeeId(), today);
            
            if (log == null || log.getSignInTime() == null) {
                response.put("success", false);
                response.put("message", "No sign-in record found for today");
                return response;
            }
            
            if (log.getSignOutTime() != null) {
                response.put("success", false);
                response.put("message", "Already signed out at " + log.getSignOutTime());
                return response;
            }
            
            // Update with sign-out time
            LocalTime signOutTime = LocalTime.now();
            log.setSignOutTime(signOutTime);
            
            // Calculate hours worked
            Duration duration = Duration.between(log.getSignInTime(), signOutTime);
            double hoursWorked = duration.toMinutes() / 60.0;
            log.setHoursWorked(hoursWorked);
            
            boolean updated = attendanceLogDAO.update(log);
            
            if (updated) {
                response.put("success", true);
                response.put("message", "Sign-out successful!");
                response.put("employeeName", employee.getName());
                response.put("time", signOutTime.toString());
                response.put("hoursWorked", String.format("%.2f", hoursWorked));
            } else {
                response.put("success", false);
                response.put("message", "Failed to record sign-out");
            }
            
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
            e.printStackTrace();
        }
        
        return response;
    }
}
