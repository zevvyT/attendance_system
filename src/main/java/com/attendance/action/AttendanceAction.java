package com.attendance.action;

import com.attendance.service.AttendanceService;
import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

public class AttendanceAction extends ActionSupport {
    
    private String cardId;
    private Map<String, Object> result;
    
    private AttendanceService attendanceService = new AttendanceService();
    
    // Sign In Action
    public String signIn() {
        result = attendanceService.signIn(cardId);
        return SUCCESS;
    }
    
    // Sign Out Action
    public String signOut() {
        result = attendanceService.signOut(cardId);
        return SUCCESS;
    }
    
    // Getters and Setters
    public String getCardId() {
        return cardId;
    }
    
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    
    public Map<String, Object> getResult() {
        return result;
    }
    
    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
