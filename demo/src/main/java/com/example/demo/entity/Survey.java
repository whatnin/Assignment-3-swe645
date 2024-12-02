package com.example.demo.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "surveys")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "visit_date")
    private LocalDate visitDate;

    @Column(name = "feedback", length = 500)
    private String feedback;

    @Column(name = "recommendation")
    private boolean recommendation;

    // Constructors
    public Survey() {}

    public Survey(String name, String email, LocalDate visitDate, String feedback, boolean recommendation) {
        this.name = name;
        this.email = email;
        this.visitDate = visitDate;
        this.feedback = feedback;
        this.recommendation = recommendation;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public boolean isRecommendation() {
        return recommendation;
    }

    public void setRecommendation(boolean recommendation) {
        this.recommendation = recommendation;
    }
}
