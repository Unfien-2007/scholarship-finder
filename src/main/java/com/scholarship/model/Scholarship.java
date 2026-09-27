package com.scholarship.model;

import java.time.LocalDate;

public abstract class Scholarship {

    private int id;
    private String name;
    private String provider;
    private String description;
    private String benefits;
    private int minGradeLevel;
    private int maxGradeLevel;
    private double maxFamilyIncome;
    private LocalDate deadline;

    public Scholarship() {
    }

    public abstract boolean isEligible(Student student);

    public abstract String getType();

    protected boolean isGradeInRange(Student student) {
        return student.getGradeLevel() >= minGradeLevel
                && student.getGradeLevel() <= maxGradeLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public int getMinGradeLevel() {
        return minGradeLevel;
    }

    public void setMinGradeLevel(int minGradeLevel) {
        this.minGradeLevel = minGradeLevel;
    }

    public int getMaxGradeLevel() {
        return maxGradeLevel;
    }

    public void setMaxGradeLevel(int maxGradeLevel) {
        this.maxGradeLevel = maxGradeLevel;
    }

    public double getMaxFamilyIncome() {
        return maxFamilyIncome;
    }

    public void setMaxFamilyIncome(double maxFamilyIncome) {
        this.maxFamilyIncome = maxFamilyIncome;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}
