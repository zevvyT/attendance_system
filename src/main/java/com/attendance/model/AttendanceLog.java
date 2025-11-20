package com.attendance.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "attendance_log")
public class AttendanceLog implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    @Column(name = "date", nullable = false)
    private LocalDate date;
    
    @Column(name = "sign_in_time")
    private LocalTime signInTime;
    
    @Column(name = "sign_out_time")
    private LocalTime signOutTime;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private AttendanceStatus status;
    
    @Column(name = "location", length = 100)
    private String location;
    
    @Column(name = "hours_worked")
    private Double hoursWorked;
    
    // Enum for Attendance Status
    public enum AttendanceStatus {
        PRESENT,
        ABSENT,
        ON_LEAVE,
        OFF_SITE,
        INCOMPLETE
    }
    
    // Constructors
    public AttendanceLog() {
    }
    
    public AttendanceLog(Employee employee, LocalDate date, AttendanceStatus status) {
        this.employee = employee;
        this.date = date;
        this.status = status;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Employee getEmployee() {
        return employee;
    }
    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public LocalTime getSignInTime() {
        return signInTime;
    }
    
    public void setSignInTime(LocalTime signInTime) {
        this.signInTime = signInTime;
    }
    
    public LocalTime getSignOutTime() {
        return signOutTime;
    }
    
    public void setSignOutTime(LocalTime signOutTime) {
        this.signOutTime = signOutTime;
    }
    
    public AttendanceStatus getStatus() {
        return status;
    }
    
    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public Double getHoursWorked() {
        return hoursWorked;
    }
    
    public void setHoursWorked(Double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }
    
    @Override
    public String toString() {
        return "AttendanceLog{" +
                "id=" + id +
                ", date=" + date +
                ", signInTime=" + signInTime +
                ", signOutTime=" + signOutTime +
                ", status=" + status +
                ", location='" + location + '\'' +
                '}';
    }
}
