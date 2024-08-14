package com.site.autosite.report;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table
public class Report {

    @Id
    @SequenceGenerator(
        name = "report_sequence",
        sequenceName = "report_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "report_sequence"
    )
    private Long id;
    private String email;
    private String message;

    
    
    public Report() {
    }
    public Report(String email, String message) {
        this.email = email;
        this.message = message;
    }
    public Report(Long id, String email, String message) {
        this.id = id;
        this.email = email;
        this.message = message;
    }
    @Override
    public String toString() {
        return "Report [id=" + id + ", email=" + email + ", message=" + message + "]";
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    
}
