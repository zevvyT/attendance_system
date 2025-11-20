package com.attendance.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "offsite_work")
public class OffSiteWork implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    @Column(name = "date", nullable = false)
    private LocalDate date;
    
    @Column(name = "location", nullable = false, length = 200)
    private String location;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    private OffSiteStatus status = OffSiteStatus.APPROVED;
    
    @Column(name = "reason", length = 500)
    private String reason;
    
    @Column(name = "registered_date")
    private LocalDate registeredDate;
    
    // Enum for Off-site Status
    public enum OffSiteStatus {
        PENDING,
        APPROVED,
        REJECTED
    }
    
    // Constructors
    public OffSiteWork() {
    }
    
    public OffSiteWork(Employee employee, LocalDate date, String location, String reason) {
        this.employee = employee;
        this.date = date;
        this.location = location;
        this.reason = reason;
        this.registeredDate = LocalDate.now();
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
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public OffSiteStatus getStatus() {
        return status;
    }
    
    public void setStatus(OffSiteStatus status) {
        this.status = status;
    }
    
    public String getReason() {
        return reason;
    }
    
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public LocalDate getRegisteredDate() {
        return registeredDate;
    }
    
    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }
    
    @Override
    public String toString() {
        return "OffSiteWork{" +
                "id=" + id +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", status=" + status +
                '}';
    }
}
