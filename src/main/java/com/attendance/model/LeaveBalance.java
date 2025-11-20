package com.attendance.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "leave_balance")
public class LeaveBalance implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false, unique = true)
    private Employee employee;
    
    @Column(name = "medical_leave")
    private Integer medicalLeave = 14;
    
    @Column(name = "annual_leave")
    private Integer annualLeave = 14;
    
    @Column(name = "year")
    private Integer year;
    
    // Constructors
    public LeaveBalance() {
    }
    
    public LeaveBalance(Employee employee, Integer medicalLeave, Integer annualLeave, Integer year) {
        this.employee = employee;
        this.medicalLeave = medicalLeave;
        this.annualLeave = annualLeave;
        this.year = year;
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
    
    public Integer getMedicalLeave() {
        return medicalLeave;
    }
    
    public void setMedicalLeave(Integer medicalLeave) {
        this.medicalLeave = medicalLeave;
    }
    
    public Integer getAnnualLeave() {
        return annualLeave;
    }
    
    public void setAnnualLeave(Integer annualLeave) {
        this.annualLeave = annualLeave;
    }
    
    public Integer getYear() {
        return year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }
    
    @Override
    public String toString() {
        return "LeaveBalance{" +
                "id=" + id +
                ", medicalLeave=" + medicalLeave +
                ", annualLeave=" + annualLeave +
                ", year=" + year +
                '}';
    }
}
