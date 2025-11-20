package com.attendance.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "department", length = 100)
    private String department;
    
    @Column(name = "role", length = 100)
    private String role;
    
    @Column(name = "card_id", unique = true, nullable = false, length = 50)
    private String cardId;
    
    @Column(name = "email", length = 100)
    private String email;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    // One-to-One relationship with LeaveBalance
    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private LeaveBalance leaveBalance;
    
    // One-to-Many relationship with AttendanceLog
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AttendanceLog> attendanceLogs;
    
    // One-to-Many relationship with LeaveRequest
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LeaveRequest> leaveRequests;
    
    // Constructors
    public Employee() {
    }
    
    public Employee(String name, String department, String role, String cardId) {
        this.name = name;
        this.department = department;
        this.role = role;
        this.cardId = cardId;
    }
    
    // Getters and Setters
    public Long getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getCardId() {
        return cardId;
    }
    
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public LeaveBalance getLeaveBalance() {
        return leaveBalance;
    }
    
    public void setLeaveBalance(LeaveBalance leaveBalance) {
        this.leaveBalance = leaveBalance;
    }
    
    public List<AttendanceLog> getAttendanceLogs() {
        return attendanceLogs;
    }
    
    public void setAttendanceLogs(List<AttendanceLog> attendanceLogs) {
        this.attendanceLogs = attendanceLogs;
    }
    
    public List<LeaveRequest> getLeaveRequests() {
        return leaveRequests;
    }
    
    public void setLeaveRequests(List<LeaveRequest> leaveRequests) {
        this.leaveRequests = leaveRequests;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", role='" + role + '\'' +
                ", cardId='" + cardId + '\'' +
                '}';
    }
}
